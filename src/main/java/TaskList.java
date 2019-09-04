import java.util.ArrayList;

public class TaskList
{
    private ArrayList<Task> tasklistobject = new ArrayList<Task>();

    public ArrayList<Task> getlist()        //returns something back
    {
        return this.tasklistobject;
    }
    public void addTask(Task task)          //add
    {
        this.tasklistobject.add(task);
    }
    public void delete(Integer index)       //delete
    {
        this.tasklistobject.remove(index);
    }
    public Integer size()                   //returns something back
    {
        return this.tasklistobject.size();
    }
    public ArrayList<Task> getList()        //returns something back
    {
        return this.tasklistobject;
    }

    public void TaskList(ArrayList<Task> taskList)        //readobject
    {
        this.tasklistobject = taskList;
    }
}