import java.util.ArrayList;
import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        String line = "     ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        Scanner input = new Scanner(System.in);
        ArrayList<Task> arlist = new ArrayList<Task>();         //link ArrayList with Task
        String[] number;                                        //number is an array of string[x]
        int count = 0;
        while(true)
        {
            String command = input.nextLine();                  //command is a string that takes in input.
            number = command.split(" ", 2);                     //split the command into number[0] and number[1] whenever a space is spotted
            switch(number[0])
            {
                case "bye":
                {
                    System.out.print(line);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                }
                case "list":
                {
                    System.out.print(line);
                    System.out.println("\t Here are the tasks in your list:");

                    for (int j = 0; j < count; j += 1)
                    {
                        System.out.println("\t " + (j + 1) + "." + arlist.get(j).toString());
                    }
                    /*
                    count = 0;
                    for(Task t : arlist)
                    {
                        count += 1;
                        System.out.println("\t " + count + "." + t.toString());
                    }
                    */
                    System.out.println(line);
                    break;
                }
                case "done":
                {
                    int placeholder = (Integer.parseInt(number[1]) - 1);            //done 1 means arlist[0] so remember to -1
                    arlist.get(placeholder).markAsDone();
                    System.out.print(line);
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + arlist.get(placeholder).toString());
                    System.out.println(line);
                    break;
                }
                case "todo":
                {
                    System.out.print(line);
                    System.out.println("     Got it. I've added this task: ");
                    arlist.add(new Todo(number[1]));
                    System.out.println("\t   " + arlist.get(arlist.size()-1).toString());
                    System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                    System.out.println(line);
                    count += 1;
                    break;
                }
                case "deadline":
                {
                    String[] part = command.split("/", 2);
                    System.out.print(line);
                    System.out.println("     Got it. I've added this task: ");
                    String[] forcolon = part[1].split(" ", 2);
                    String[] removedeadline = part[0].split(" ", 2);
                    arlist.add(new Deadline(removedeadline[1], forcolon[1]));
                    System.out.println("\t   " + arlist.get(arlist.size()-1).toString());
                    System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                    System.out.println(line);
                    count += 1;
                    break;
                }
                case "event":
                {
                    String[] part = command.split("/", 2);
                    //number[0] = event, number[1] = project meeting at Mon 2-4pm
                    //part[0] = event project meeting, part[1] = at Mon 2-4pm
                    String[] forcolon = part[1].split(" ", 2);
                    String[] removeevent = part[0].split(" ", 2);
                    arlist.add(new Event(removeevent[1], forcolon[1]));
                    System.out.print(line);
                    System.out.println("     Got it. I've added this task: ");
                    System.out.println("\t   " + arlist.get(arlist.size()-1).toString());
                    System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                    System.out.println(line);
                    count += 1;
                    break;
                }
                default:
                {
                    System.out.print(line);
                    System.out.println("     added: " + command);
                    System.out.println(line);
                    arlist.add(new Task(command));
                    count += 1;
                }
            }
            if(command.equals("bye"))
            {
                break;
            }
        }
    }
}
