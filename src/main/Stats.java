package main;

import java.util.HashMap;

/**
 * This class manages the data for the statistics.  It uses
 * stores this data and calculates the appropriate statistics.
 *
 * @author Horizonistic
 * @version 1.1
 */
class Stats
{
    private int totalJobs = 0;
    private HashMap<Integer, Integer> totalJobTimes = new HashMap<Integer, Integer>();
    private HashMap<Integer, Long> responseTimes = new HashMap<Integer, Long>();
    private HashMap<Integer, Integer> waitingTimes = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> neededCpuTimes = new HashMap<Integer, Integer>();
    private int totalIdleTime = 0;

    /**
     * Increments the total amount of jobs.
     */
    public void incrementTotalJobs()
    {
        this.totalJobs++;
    }

    /**
     * Gets the total amount of jobs.
     *
     * @return  The total amount of jobs
     */
    public int getTotalJobs()
    {
        return this.totalJobs;
    }

    /**
     * Adds the PID and amount of time a job was in the
     * system into a private HashMap.
     *
     * @param pid  The PID of the job
     * @param time  The time the job was in the system
     */
    public void addTotalJobTime(int pid, int time)
    {
        this.totalJobTimes.put(pid, time);
    }

    /**
     * Calculates the total amount of time all jobs have
     * been in the system.
     *
     * @return  The total amount of job time
     */
    public int getTotalJobTime()
    {
        int total = 0;
        for (int n : this.totalJobTimes.values())
        {
            total += n;
        }
        return total;
    }

    /**
     * Calculates the average throughput of the system.
     * The throughput is calculated as the total number of
     * jobs divided by the total job time.
     *
     * @return  The average throughput
     */
    public double averageThroughput()
    {
        return this.totalJobs / Float.valueOf(this.getTotalJobTime());
    }

    /**
     * Adds the PID and the response time of a job in
     * the system to a private HashMap.
     *
     * @param pid  The PID of the job
     * @param time The response time of the job
     */
    public void addResponseTime(int pid, long time)
    {
        this.responseTimes.put(pid, time);
    }

    /**
     * Calculates the average response time of all jobs
     * in the system.
     *
     * @return  The average response time in milliseconds
     */
    public double averageResponseTime()
    {
        int total = 0;
        int count = 0;
        for (long n : this.responseTimes.values())
        {
            total += n;
            count++;
        }

        return (total / count) / 100000.0d;
    }

    /**
     * Calculates the average turnaround time of the jobs
     * in the system using the total job times.
     *
     * @return  The average turnaround time
     */
    public double averageTurnaroundTime()
    {
        int total = 0;
        int count = 0;
        for (int n : this.totalJobTimes.values())
        {
            total += n;
            count++;
        }

        return total / count;
    }

    /** Adds the PID and the amount of time a job spent
     * waiting inside a queue to a private HashMap.
     *
     * @param pid  The PID of the job
     * @param time  The waiting time of the job
     */
    public void addWaitingTime(int pid, int time)
    {
        this.waitingTimes.put(pid, time);
    }

    /**
     * Calculates the average waiting time of all the
     * jobs in the system.
     *
     * @return  The average wait time
     */
    public double averageWaitingTime()
    {
        int total = 0;
        int count = 0;
        for (int n : this.waitingTimes.values())
        {
            total += n;
            count++;
        }

        return total / count;
    }

    /**
     * Adds the PID and the amount of CPU time that a job
     * needs to a private HashMap.
     *
     * @param pid  The PID of the jop
     * @param time  The CPU time needed of the job
     */
    public void addNeededCpuTime(int pid, int time)
    {
        this.neededCpuTimes.put(pid, time);
    }

    /**
     * Calculates the average CPU time needed of all the
     * jobs in the system.
     *
     * @return  The average CPU time needed
     */
    public double averageCpuTimeNeeded()
    {
        int total = 0;
        int count = 0;
        for (int n : this.neededCpuTimes.values())
        {
            total += n;
            count++;
        }

        return total / count;
    }

    /**
     * Increments the counter for the total idle time of
     * the CPU.
     */
    public void incrementTotalIdleTime()
    {
        this.totalIdleTime++;
    }

    /**
     * Gets the totalIdleTime of the CPU.
     *
     * @return  The total idle time of the CPU
     */
    public int totalIdleTime()
    {
        return this.totalIdleTime;
    }
}
