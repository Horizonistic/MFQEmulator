package main;

import util.ObjectQueue;
import util.SuperOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The main controller for the CPU.  It manages the queues, processes
 * all the logic to figure out what to pass to the CPU, outputs the
 * table, and manages the statistics using the Stats class.
 *
 * @author Horizonistic
 * @version 1.3.2
 */
public class MFQ
{
    SuperOutput so;
    private CPU cpu;
    private ObjectQueue jobsHolding = new ObjectQueue();
    private ObjectQueue level1 = new ObjectQueue();
    private ObjectQueue level2 = new ObjectQueue();
    private ObjectQueue level3 = new ObjectQueue();
    private ObjectQueue level4 = new ObjectQueue();
    private Stats stats = new Stats();

    /**
     * Sets local SuperOutput and instantiates the CPU.
     *
     * @param so  The SuperOutput to use for prints
     */
    public MFQ(SuperOutput so)
    {
        this.so = so;
        this.cpu = new CPU();
    }

    /**
     * Reads the input file and stores all the data in a
     * temporary holding queue.
     */
    public void readFile()
    {
        String filename = "mfq.txt";
        BufferedReader br;

        // Open file
        try
        {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            this.so.println("File " + filename + " not found or cannot be opened.");
            return;
        }

        // Read line-by-line
        try
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] jobInfo = Pattern.compile("[\\s]+").split(line);
                Job job = new Job(Integer.valueOf(jobInfo[1]), Integer.valueOf(jobInfo[0]), Integer.valueOf(jobInfo[2]));
                this.jobsHolding.insert(job);
            }
        }
        catch (IOException e)
        {
            this.so.println("Error reading " + filename);
            return;
        }
    }

    /**
     * Prints the header for the ASCII table.
     */
    public void printHeader()
    {
        printTableLine();
        this.so.printlnf("| %-10s | %-15s | %-8s | %-20s | %-25s | %-25s |", "Event", "System Time", "PID", "CPU Time Needed", "Total Time In System", "Lowest Level Queue");
        printTableLine();
    }

    /**
     * Calls the appropriate methods to run the entire simulation,
     * such as the ticking of the CPU, the logic, and when its done.
     */
    public void runEmulator()
    {
        while (true)
        {
            this.cpu.tick();
            doLogic();
            if (checkIfDone())
            {
                return;
            }
        }
    }

    /**
     * Does the majority of the problem solving for the MFQ,
     * This method talk to the CPU and determines what jobs go
     * where and when.
     */
    private void doLogic()
    {
        int time = cpu.getCurrentTime();
        Job job;

        if (!this.cpu.isBusy())
        {
            this.stats.incrementTotalIdleTime();
        }

        // Do current job logic
        if (this.cpu.getCurrentJob() != null)
        {
            this.cpu.decrementQuantum();
            ((Job) this.cpu.getCurrentJob()).setTimeRemaining(((Job) this.cpu.getCurrentJob()).getTimeRemaining() - 1);

            // If job is done
            if (((Job) this.cpu.getCurrentJob()).getTimeRemaining() == 0  && this.cpu.getCurrentJob() != null)
            {
                this.cpu.setCpuAsNotBusy();
                Job next = this.getNextJob();
                Job temp;
                if (next != null)
                {
                    temp = this.cpu.setJob(next);
                    this.cpu.setCpuAsBusy();
                }
                else
                {
                    temp = this.cpu.setJob(null);
                    this.cpu.setCpuAsNotBusy();
                }
                this.so.printlnf("| %-10s | %-15d | %-8d | %-20s | %-25s | %-25s |", "Departure", time, temp.pid, "-", time - temp.arrivalTime, temp.getCurrentLevel());
                this.stats.addTotalJobTime(temp.pid, time - temp.arrivalTime);
                printTableLine();
            }
            // If time is up for job
            else if (this.cpu.getQuantum() == 0 && this.cpu.getCurrentJob() != null)
            {
                Job temp = this.cpu.setJob(null);
                temp.incrementQueueLevel();
                temp.setTimeBeforeQueue(time);
                insertJobToQueue(temp);
                Job next = this.getNextJob();
                this.stats.addWaitingTime(next.pid, time - next.getTimeBeforeQueue());

                if (next != null)
                {
                    this.cpu.setJob(next);
                    this.cpu.setCpuAsBusy();
                }
                else
                {
                    this.cpu.setJob(null);
                    this.cpu.setCpuAsNotBusy();
                }
            }
        }

        // Checking for new job
        if (!this.jobsHolding.isEmpty())
        {
            job = (Job) this.jobsHolding.query();
            if (job != null && job.arrivalTime == time)
            {
                job = (Job) this.jobsHolding.remove();
                this.stats.incrementTotalJobs();
                this.stats.addNeededCpuTime(job.pid, job.getTimeRequired());
                job.setResponseTime(System.nanoTime());
                this.so.printlnf("| %-10s | %-15d | %-8d | %-20s | %-25s | %-25s |", "Arrival", time, job.pid, job.getTimeRequired(), "-", "-");
                printTableLine();

                this.stats.addResponseTime(job.pid, System.nanoTime() - job.getResponseTime());
                job = this.cpu.setJob(job);
                if (job != null)
                {
                    job.incrementQueueLevel();
                    job.setTimeRemaining(job.getTimeRemaining());
                    insertJobToQueue(job);
                }
                this.cpu.setCpuAsBusy();
            }
        }
    }

    /**
     * Checks if there really is no more work for the
     * CPU to do, so as to end the simulation.
     *
     * @return  If done or not
     */
    public boolean checkIfDone()
    {
        if (this.jobsHolding.isEmpty())
        {
            if (this.level1.isEmpty())
            {
                if (this.level2.isEmpty())
                {
                    if (this.level3.isEmpty())
                    {
                        if (this.level4.isEmpty())
                        {
                            if (!this.cpu.isBusy())
                            {
                                if (this.cpu.getQuantum() == 0)
                                {
                                    if (this.cpu.getCurrentJob() == null)
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the next appropriate job for the CPU
     * to work on.
     *
     * @return  The next job in line
     */
    public Job getNextJob()
    {
        if (!this.level1.isEmpty())
        {
            return (Job) this.level1.remove();
        }
        else if (!this.level2.isEmpty())
        {
            return (Job) this.level2.remove();
        }
        else if (!this.level3.isEmpty())
        {
            return (Job) this.level3.remove();
        }
        else if (!this.level4.isEmpty())
        {
            return (Job) this.level4.remove();
        }
        else
        {
            return null;
        }
    }

    /**
     * Inserts passed job into the appropriate queue based on
     * its queue level.
     *
     * @param job  The job to insert into the queues
     */
    public void insertJobToQueue(Job job)
    {
        int level = job.getCurrentLevel();

        switch (level)
        {
            case 1:
                this.level1.insert(job);
                break;

            case 2:
                this.level2.insert(job);
                break;

            case 3:
                this.level3.insert(job);
                break;

            case 4:
                this.level4.insert(job);
                break;

            default:
                break;
        }
    }

    /**
     * Interacts with the Stats class in order to display the
     * appropriate statistics.
     */
    public void printStats()
    {
        this.so.println("Total Jobs: ");
        this.so.print(this.stats.getTotalJobs());

        this.so.println("Total Time of Jobs (ticks): ");
        this.so.print(this.stats.getTotalJobTime());

        this.so.println("Response (milliseconds): ");
        this.so.printf("%.2f", this.stats.averageResponseTime());

        this.so.println("Turnaround (ticks): ");
        this.so.print(this.stats.averageTurnaroundTime());

        this.so.println("Waiting (ticks): ");
        this.so.print(this.stats.averageWaitingTime());

        this.so.println("Throughput (jobs/total time): ");
        this.so.printf("%.2f", this.stats.averageThroughput());

        this.so.println("Total CPU Idle Time (ticks): ");
        this.so.print(this.stats.totalIdleTime());

        this.so.println("Average CPU Time Needed (ticks): ");
        this.so.printf("%.2f", this.stats.averageCpuTimeNeeded());
    }

    /**
     * Prints an ASCII horizontal line for use in the table.
     */
    private void printTableLine()
    {
        this.so.printlnf("--------------------------------------------------------------------------------------------------------------------------");
    }
}
