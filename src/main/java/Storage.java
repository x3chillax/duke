import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage
{
    public void read(TaskList taskList)
    {
        try
        {
            FileInputStream fis = new FileInputStream("tasks.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            taskList.TaskList((ArrayList<Task>) ois.readObject());
            //this.taskList = (ArrayList<Task>) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {
            try
            {
                FileOutputStream fos = new FileOutputStream("list.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(taskList.getlist());
                oos.close();
            } catch(Exception f)
            {
                Ui.response("Directory specified is not found!");
            }
            System.out.println("File not found in the directory! We have now created one for you");
        }
    }

    public void write(TaskList taskList)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("list.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList.getlist());
            oos.close();
        }
        catch(Exception e)
        {
            Ui.response("Error while saving your tasks :(");
        }
    }
}