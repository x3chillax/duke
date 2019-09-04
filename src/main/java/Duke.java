import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke
{
    public static void main(String[] args)
    {
        LocalDateTime.now();
        String line = "     ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        Scanner input = new Scanner(System.in);
        ArrayList<Task> arlist = new ArrayList<Task>();         //link ArrayList with Task
        String[] number;                                        //number is an array of string[x]
        //int count = 0;
        try
        {
            //FileInputStream fis = new FileInputStream("src\\main\\java\\list.txt");
            FileInputStream fis = new FileInputStream("list.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            arlist = (ArrayList<Task>) ois.readObject();
            ois.close();
        }
        catch(Exception e)
        {
            try
            {
                //FileOutputStream fos = new FileOutputStream("src\\main\\java\\list.txt");
                FileOutputStream fos = new FileOutputStream("list.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(arlist);
                oos.close();
            }
            catch(Exception f)
            {
                System.out.println("Directory specified is not found!");
            }
            System.out.println("File not found in the directory! We have now created one for you");
        }

        while(true)
        {
            try
            {
                String command = input.nextLine();                  //command is a string that takes in input.
                number = command.split(" ", 2);                     //split the command into number[0] and number[1] whenever a space is spotted
                switch (number[0])
                {
                    case "bye":
                    {
                        System.out.print(line);
                        System.out.println("     Bye. Hope to see you again soon!");
                        System.out.println(line);
                        break;
                    }
                    case "find":
                    {
                        //ArrayList<Task> find = new ArrayList<Task>();
                        String output = "";
                        for (int j = 0; j < arlist.size(); j += 1)
                        {
                            if(arlist.get(j).toString().contains(number[1]))
                            {
                                output +=  "     " + (j+1) + arlist.get(j).toString() + "\n" ;
                            }
                        }
                        if(!output.equals(""))
                        {
                            System.out.print(line);
                            System.out.println("\t Here are the matching tasks in your list:");
                            System.out.print(line);
                            System.out.println(output);
                        }
                        else
                        {
                            System.out.print(line);
                            System.out.println("\t Opps, no such word found!");
                        }
                        break;
                    }
                    case "list":
                    {
                        System.out.print(line);
                        System.out.println("\t Here are the tasks in your list:");

                        for (int j = 0; j < arlist.size(); j += 1)
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
                        if(number.length == 1)
                        {
                            try
                            {
                                int placeholder = (Integer.parseInt(number[1]) - 1);
                            }
                            catch (Exception e)
                            {
                                System.out.println(line);
                                System.out.println("Invalid syntax.\t Syntax = todo (name of task)");
                                break;
                            }
                        }
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
                        //System.out.print(number.length);
                        if(number.length == 1)
                        {
                            try
                            {
                                arlist.add(new Todo(number[1]));
                            }
                            catch (Exception e)
                            {
                                System.out.println(line);
                                System.out.println("Invalid syntax.\t Syntax = todo (name of task)");
                                break;
                            }
                        }
                        System.out.print(line);
                        arlist.add(new Todo(number[1]));
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("\t   " + arlist.get(arlist.size() - 1).toString());
                        System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                        System.out.println(line);
                        //count += 1;
                        break;
                    }
                    case "delete":
                    {
                        if(number.length == 1)
                        {
                            try
                            {
                                int placeholder = (Integer.parseInt(number[1]) - 1);
                            }
                            catch (Exception e)
                            {
                                System.out.println(line);
                                System.out.println("Invalid syntax.\t Syntax = delete (insert task number)");
                                break;
                            }
                        }
                        int placeholder = (Integer.parseInt(number[1]) - 1);            //done 1 means arlist[0] so remember to -1
                        System.out.print(line);
                        System.out.println("     Noted. I've removed this task: ");
                        System.out.println("       " + arlist.get(placeholder).toString());
                        System.out.println("\t Now you have " + (arlist.size() - 1) + " tasks in the list.");
                        arlist.remove(placeholder);
                        System.out.println(line);
                        break;
                    }
                    case "deadline":
                    {
                        //command = deadline return book /by Sunday
                        //part[0] = deadline return book, part[1] = by Sunday
                        //forcolon[1] = Sunday, removedeadline[1] = return book
                        String[] part = command.split("/", 2);
                        System.out.print(line);
                        String[] forcolon = part[1].split(" ", 2);
                        String[] removedeadline = part[0].split(" ", 2);
                        arlist.add(new Deadline(removedeadline[1], forcolon[1]));
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("\t   " + arlist.get(arlist.size() - 1).toString());
                        System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                        System.out.println(line);
                        //count += 1;
                        break;
                    }
                    case "event":
                    {
                        //command = event project meeting /at Mon 2-4pm
                        //part[0] = event project meeting, part[1] = at Mon 2-4pm
                        //removeevent[0] = event, number[1] = project meeting at Mon 2-4pm
                        //System.out.println(number.length);
                        String[] part = command.split("/", 2);
                        //System.out.println(part.length);
                        if(part.length == 1)
                        {
                            try
                            {
                                String[] forcolon = part[1].split(" ", 2);      //forcolon[0] = at, forcolon[1] = Mon 2-4pm
                            }
                            catch (Exception e)
                            {
                                System.out.println(line);
                                System.out.println("Invalid syntax.\t Syntax = Please enter in the following syntax: event (EventName) /at YYYY-MM-DD HH:MM");
                                break;
                            }
                        }
                        String[] forcolon = part[1].split(" ", 2);      //forcolon[0] = at, forcolon[1] = Mon 2-4pm
                        String[] removeevent = part[0].split(" ", 2);       //removeevent[0] = event, removeevent[1] = project meeting
                        arlist.add(new Event(removeevent[1], forcolon[1]));
                        System.out.print(line);
                        System.out.println("     Got it. I've added this task: ");
                        System.out.println("\t   " + arlist.get(arlist.size() - 1).toString());
                        System.out.println("\t Now you have " + arlist.size() + " tasks in the list.");
                        System.out.println(line);
                        //count += 1;
                        break;
                    }
                    default:
                    {
                        /*
                        System.out.print(line);
                        System.out.println("     added: " + command);
                        System.out.println(line);
                        arlist.add(new Task(command));
                        count += 1;
                        */
                        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                if (command.equals("bye"))
                {
                    break;
                }
            }
            catch(DukeException e)
            {
                System.out.println(e.getMessage());
            }
        }
        try
        {
            //FileOutputStream fos = new FileOutputStream("src\\main\\java\\list.txt");
            FileOutputStream fos = new FileOutputStream("list.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arlist);
            oos.close();
        }
        catch(Exception e)
        {
            System.out.println("Directory specified is not found!");
        }
        input.close();
    }
}
