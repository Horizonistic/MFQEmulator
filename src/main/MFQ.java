package main;

import util.ObjectQueue;
import util.SuperOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class MFQ
{
    SuperOutput so;
    private CPU cpu = new CPU();
    private ObjectQueue jobsHolding = new ObjectQueue();
    private ObjectQueue queue = new ObjectQueue();

    public MFQ(SuperOutput so)
    {
        this.so = so;
    }

    public void readFile()
    {
        String filename = "mfq.txt";
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            this.so.println("File " + filename + " not found or cannot be opened.");
            return;
        }

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
        this.jobsHolding.dump(this.so);
    }

    public void runEmulator()
    {
        while (!this.cpu.flag && this.queue.isEmpty() && this.cpu.job == null)
        {
            int time = this.cpu.tick();
            Job job = (Job) this.queue.query();

            if (job.arrivalTime == time)
            {
                this.queue.insert(this.cpu.setNewJob(job));
            }
        }
    }
}
