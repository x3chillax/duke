import java.util.ArrayList;
/**
 * This class stores the task list and has methods to modify or get information regarding the task list contents
 *
 * @author Joey Goh
 */
public class TaskList
{
    private ArrayList<Task> tasklistobject = new ArrayList<Task>();

    /**
     * method to get task list
     * @return task list
     */
    public ArrayList<Task> getlist()        //returns something back
    {
        return this.tasklistobject;
    }
    /**
     * method to add a task into the task list
     */
    public void addTask(Task task)          //add
    {
        this.tasklistobject.add(task);
    }
    /**
     * method to remove a task from the task list
     */
    public void delete(Integer index)       //delete
    {
        this.tasklistobject.remove(index);
    }
    /**
     * method to get size of task list
     * @return size of the task list
     */
    public ArrayList<Task> getList()        //returns something back
    {
        return this.tasklistobject;
    }

    /**
     * constructor for a TaskList object
     * @param taskList the current list of the tasks
     */
    public void TaskList(ArrayList<Task> taskList)        //readobject
    {
        this.tasklistobject = taskList;
    }
}