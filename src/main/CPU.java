package main;

/**
 * This class keeps track of the time and the current
 * job, as well as the amount of time a job has to try
 * and finish its task.
 *
 * @author  Horizonistic
 * @version  1.3.3
 */
public class CPU
{
    private Job job;
    private int time;
    private int quantumRemaining;
    private boolean busy;

    /**
     * Simulates a cycle.  Adds 1 to the total time.
     */
    public void tick()
    {
        try
        {
            Thread.sleep(10); // For fun!
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        this.time++;
    }

    /**
     * Sets the job for the CPU to work on.  Returns
     * the CPU's old just (the one that got replaced).
     *
     * @param job  The new job
     * @return  The old job
     */
    public Job setJob(Job job)
    {
        if (this.job != null && job != null)
        {
            Job temp = this.job;
            this.job = job;
            setQuantum((int) Math.pow(2, this.job.getCurrentLevel()));
            return temp;
        }
        else if (job == null)
        {
            Job temp = this.job;
            this.job = job;
            setQuantum(0);
            return temp;
        }
        else
        {
            this.job = job;
            setQuantum((int) Math.pow(2, this.job.getCurrentLevel()));
            return null;
        }
    }

    /**
     * Gets the CPU's current job.
     *
     * @return  The current job
     */
    public Object getCurrentJob()
    {
        return this.job;
    }

    /**
     * Gets the current time of the CPU.
     *
     * @return  The current time
     */
    public int getCurrentTime()
    {
        return this.time;
    }

    /**
     * Decreases the quantum remaining for the current job
     * by 1.
     */
    public void decrementQuantum()
    {
        this.quantumRemaining--;
    }

    /**
     * Sets the quantum remaining.
     *
     * @param time  The time to set the quantum to
     */
    public void setQuantum(int time)
    {
        this.quantumRemaining = time;
    }

    /**
     * Gets the current quantum remaining.
     *
     * @return  The current quantum remaining
     */
    public int getQuantum()
    {
        return this.quantumRemaining;
    }

    /**
     * Sets a flag to mark the CPU as busy.
     */
    public void setCpuAsBusy()
    {
        this.busy = true;
    }

    /**
     * Sets a flag to mark the CPU as not busy
     */
    public void setCpuAsNotBusy()
    {
        this.busy = false;
    }

    /**
     * Returns if the CPU currently is busy with a job
     * or not.
     *
     * @return  CPU status
     */
    public boolean isBusy()
    {
        return this.busy;
    }
}
