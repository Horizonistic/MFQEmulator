package main;

public class CPU
{
    public Job job;
    public int time;
    public int quantumRemaining;
    public boolean flag;


    public int tick()
    {

        if (flag)
        {
            this.job.setTimeRemaining(this.job.getTimeRemaining() - 1);
            this.quantumRemaining--;

            if (this.job.getTimeRemaining() == 0)
            {
                this.flag = false;
            }
            else if (this.quantumRemaining == 0)
            {
                this.job.incrementQueueLevel();
            }
        }

        this.time++;
        return this.time;
    }

    public Job setNewJob(Job job)
    {
        Job temp = this.job;
        this.job = job;
        return temp;
    }

    public Object getCurrentJob()
    {
        return this.job;
    }
}