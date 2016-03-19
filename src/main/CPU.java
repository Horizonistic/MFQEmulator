package main;

public class CPU
{
    private Job job;
    private int time;
    public int quantumRemaining;
    private boolean busy;

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

    public Object getCurrentJob()
    {
        return this.job;
    }

    public int getCurrentTime()
    {
        return this.time;
    }

    public void decrementQuantum()
    {
        this.quantumRemaining--;
    }

    public void setQuantum(int time)
    {
        this.quantumRemaining = time;
    }

    public int getQuantum()
    {
        return this.quantumRemaining;
    }

    public void setCpuAsBusy()
    {
        this.busy = true;
    }

    public void setCpuAsNotBusy()
    {
        this.busy = false;
    }

    public boolean isBusy()
    {
        return this.busy;
    }
}
