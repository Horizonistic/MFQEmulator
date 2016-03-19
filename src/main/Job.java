package main;

/**
 * Retains all data related to a particular job.
 *
 * @author Horizonistic
 * @version 1.0.2
 */
public class Job
{
    public int pid;
    public int arrivalTime;
    private int timeRequired;
    private int timeRemaining;
    private int currentLevel;

    private int timeBeforeQueue;
    private long responseTime;

    /**
     * Instantiates a job with the given PID, arrival time,
     * and CPU time required.
     *
     * @param pid  The PID of the job
     * @param arrivalTime  The arrival time of the job
     * @param timeRequired  The amount of CPU time require by the job
     */
    public Job(int pid, int arrivalTime, int timeRequired)
    {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.timeRequired = timeRequired;
        this.setTimeRemaining(timeRequired);
        this.currentLevel = 1;
    }

    /**
     * Increases the queue level by 1, up to a maximum of 4.
     */
    public void incrementQueueLevel()
    {
        if (this.currentLevel != 4)
        {
            this.currentLevel++;
        }
    }

    /**
     * Gets the current queue level of the job
     *
     * @return  The current queue level
     */
    public int getCurrentLevel()
    {
        return this.currentLevel;
    }

    /**
     * Sets the amount of time remaining for the job
     * to finish its work.
     *
     * @param time  The amount of time
     */
    public void setTimeRemaining(int time)
    {
        this.timeRemaining = time;
    }

    /**
     * Gets the amount of time remaining for the job
     * to finish its work.
     *
     * @return  The amount of time
     */
    public int getTimeRemaining()
    {
        return this.timeRemaining;
    }

    /**
     * Gets the total CPU time needed by the job to
     * finish its work.
     *
     * @return  The amount of time
     */
    public int getTimeRequired()
    {
        return this.timeRequired;
    }

    /**
     * Stores the time of the CPU before the job
     * goes to wait in a queue.
     *
     * @param time  The timestamp
     */
    public void setTimeBeforeQueue(int time)
    {
        this.timeBeforeQueue = time;
    }

    /**
     * Gets the time of when the job went into
     * waiting.
     *
     * @return  The timestamp
     */
    public int getTimeBeforeQueue()
    {
        return this.timeBeforeQueue;
    }

    /**
     * Sets the response time of the job in nanoseconds
     * (how long it took the system to set it as
     * the new job from the time it recognized it as
     * a new job.
     *
     * @param time  The timestamp
     */
    public void setResponseTime(long time)
    {
        this.responseTime = time;
    }

    /**
     * Gets the response time of the job
     *
     * @return  The timestamp
     */
    public long getResponseTime()
    {
        return this.responseTime;
    }
}
