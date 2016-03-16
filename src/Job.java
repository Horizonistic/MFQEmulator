public class Job
{
    private int pid;
    private int arrivalTime;
    private int timeRequired;
    private int timeRemaining;
    private int currentLevel;

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
        this.currentLevel++;
    }

    public void decrementQueueLevel()
    {
        this.currentLevel--;
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
}