/*
package PACKAGE_NAME;

public class Parser
{
    public <TaskList, Storage> void processUserInput(String userInput, TaskList tasklistobject, Storage storage)
    {
        String[] number = userInput.split(" ", 2);
        switch(number[0])
        {
            case "done":
            {
                if(Integer.parseInt(number[1]) > tasklistobject.size())
                {
                    Ui.response("Task not found");
                }
                else
                {
                    taskList.get((Integer.parseInt(parts[1]) - 1)).setDone(true);
                    Ui.respondToUser("Nice! I've marked this task as done: \n" + taskList.get((Integer.parseInt(parts[1]) - 1)).toString());
                }
                break;
            }
        }
    }
}

 */