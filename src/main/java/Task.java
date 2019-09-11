import java.io.Serializable;
/**
 * This is an abstract class that stores the tasks
 *
 * @author Joey Goh
 */
public class Task implements Serializable
{
    /**
     *  description - Description of given task
     *  isDone - Status of given task
     */
    protected String description;
    protected boolean isDone;
    protected String statusIcon = null;

    public Task(String description)
    {
        this.description = description;
        this.isDone = false;
    }
    /**
     * method to create a new isDone object
     *
     * @param description the name of the task
     */
    public boolean isDone()
    {
        return isDone;
    }


    public String getDescription()
    {
        return this.description;
    }

    public String getStatusIcon()
    {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }
    public void markAsDone()
    {
        this.isDone = true;
    }
    public String toString()
    {
        return getStatusIcon() + getDescription();
    }

    public void setDone(boolean d)
    {
        isDone = d;
        statusIcon = "[\u2713]";
    }
}
