package task;

import app.Storage;

import java.util.ArrayList;

import static task.TaskCommands.printCount;

/**
 * ArrayList List inheriting from Task as the List stores Tasks
 */
public class List extends Task{
    private static ArrayList<Task> List = new ArrayList<>();

    /**
     * To allow other ArrayList in other classes to set into main ArrayList List
     * @param Other
     */
    public static void setList(ArrayList<Task> Other) {
        List = Other;
    }

    /**
     * To return ArrayList List to other ArrayList in other classes
     * @return ArrayList List
     */
    public static ArrayList<Task> getList(){
        return List;
    }

    /**
     * Method to add Task to list
     * using ArrayList.add method
     * Print line to note task has been added
     * Print number of items in list
     * Saves list into storage
     * @param t - Tasks
     */
    public static void addList(Task t){
        List.add(t);
        System.out.println("\tGot it. I've added this task:");
        printCount();
        Storage.saveList();
    }

    /**
     * Method to return List size in int
     * @return int size of List
     */
    public static int getSize(){
        return List.size();
    }
}

