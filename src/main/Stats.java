package main;

import java.util.HashMap;


class Stats
{
    private int totalJobs = 0;
    private HashMap<Integer, Integer> totalJobTimes = new HashMap<Integer, Integer>();
    private HashMap<Integer, Long> responseTimes = new HashMap<Integer, Long>();
    private HashMap<Integer, Integer> waitingTimes = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> neededCpuTimes = new HashMap<Integer, Integer>();
    private int totalIdleTime = 0;

    public void incrementTotalJobs()
    {
        this.totalJobs++;
    }

    public int getTotalJobs()
    {
        return this.totalJobs;
    }

    public void addTotalJobTime(int pid, int time)
    {
        this.totalJobTimes.put(pid, time);
    }

    public int getTotalJobTime()
    {
        int total = 0;
        for (int n : this.totalJobTimes.values())
        {
            total += n;
        }
        return total;
    }

    public double averageThroughput()
    {
        return this.totalJobs / Float.valueOf(this.getTotalJobTime());
    }

    public void addResponseTime(int pid, long time)
    {
        this.responseTimes.put(pid, time);
    }

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

    public void addWaitingTime(int pid, int time)
    {
        this.waitingTimes.put(pid, time);
    }

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

    public void addNeededCpuTime(int pid, int time)
    {
        this.neededCpuTimes.put(pid, time);
    }

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

    public void incrementTotalIdleTime()
    {
        this.totalIdleTime++;
    }

    public int totalIdleTime()
    {
        return this.totalIdleTime;
    }
}
