package main;

public class Job
{
    public int pid;
    public int arrivalTime;
    private int timeRequired;
    private int timeRemaining;
    private int currentLevel;

    private int timeBeforeQueue;
    private long responseTime;

    public Job(int pid, int arrivalTime, int timeRequired)
    {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.timeRequired = timeRequired;
        this.setTimeRemaining(timeRequired);
        this.currentLevel = 1;
    }

    public void incrementQueueLevel()
    {
        if (this.currentLevel != 4)
        {
            this.currentLevel++;
        }
    }

    public int getCurrentLevel()
    {
        return this.currentLevel;
    }

    public void setTimeRemaining(int time)
    {
        this.timeRemaining = time;
    }

    public int getTimeRemaining()
    {
        return this.timeRemaining;
    }

    public int getTimeRequired()
    {
        return this.timeRequired;
    }

    public void setTimeBeforeQueue(int time)
    {
        this.timeBeforeQueue = time;
    }

    public int getTimeBeforeQueue()
    {
        return this.timeBeforeQueue;
    }

    public void setResponseTime(long time)
    {
        this.responseTime = time;
    }

    public long getResponseTime()
    {
        return this.responseTime;
    }
}
