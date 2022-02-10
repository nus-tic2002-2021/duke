package duke.task;

import duke.exception.EmptyTaskListException;
import duke.exception.TaskNotFoundException;
import duke.ui.ReturnMessages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    protected int listSize;
    private ArrayList<Task> taskList;
    private static ReturnMessages printer = new ReturnMessages();


    /** Creates task list. */
    public TaskList() {taskList = new ArrayList<>();}

    /**
     * Creates Task list with tasks within.
     *
     * @param load - the task list that already contained task.
     */
    public TaskList(TaskList load) {
        this.listSize = load.listSize;
        this.taskList = load.taskList;
    }

    /**
     * Deletes a specific task uniquely identified via taskID.
     *
     * @param taskID - the unique identifier for each task.
     * @throws TaskNotFoundException - when a task is not found.
     * @throws EmptyTaskListException - when the task list is empty.
     */
    public void deleteTask(int taskID) throws TaskNotFoundException, EmptyTaskListException {
        int taskPosition = taskID -1;
        if(taskPosition > this.listSize || taskPosition < 0){
            throw new TaskNotFoundException("task not found");
        }
        System.out.println("     Noted. I've removed this task: " );
        System.out.println("     " + taskList.get(taskPosition).toString());
        this.taskList.remove(taskPosition);
        this.listSize--;
        if(this.listSize == 0){
            throw new EmptyTaskListException("empty task");
        }
        System.out.println("     Now you have " + this.listSize + " tasks in the list.");
        printer.separator();
    }

    /**
     * Adds a task to task list.
     *
     * @param task - the full task information.
     */
    public boolean addTask(Task task){
        if(this.taskList.add(task)){
            this.listSize++;
            return true;
        }
        return false;
    }

    /**
     * Gets a task via its task ID.
     *
     * @param ID - the unique identifier for a task.
     * @return - returns the task.
     */
    public Task getTask(int ID){
        return taskList.get(ID);
    }

    /**
     * Gets the last task added.
     *
     * @return - return the task.
     */
    public Task getLastTask(){
        return taskList.get(listSize-1);
    }

    /**
     * Gets the list size.
     *
     * @return - list size in integer.
     */
    public int getListSize(){
        return this.listSize;
    }



    /**
     * Prints the tasks within the task list.
     *
     * @throws EmptyTaskListException - thrown if task list is empty.
     */
    public void printTaskList() throws EmptyTaskListException {
        if(this.listSize == 0){
            throw new EmptyTaskListException("empty task");
        }
        printer.printFullTaskList(taskList);
    }

    /**
     * Getter for getting the task list.
     *
     * @return - returns entire task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }


    /**
     * Finds tasks containing specific keyword.
     *
     * @param keyword - the keyword to find.
     * @throws EmptyTaskListException - thrown if task list is empty.
     */
    public void printFoundTaskList(String keyword) throws EmptyTaskListException {

        if(this.listSize == 0){
            throw new EmptyTaskListException("empty task");
        }
        printer.taskFoundFeedback(keyword, taskList);
    }

    /**
     * prints schedule order by date
     *
     * @param dateToPrint the date that the schedule is needed
     * @throws EmptyTaskListException if task list is empty, throws exception on empty task list
     */
    public void printAsSchedule(LocalDate dateToPrint) throws EmptyTaskListException{
        if(this.listSize == 0){
            throw new EmptyTaskListException("There's nothing in task list yet.");
        }
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for(Task t : taskList){
            if(!t.getTaskType().equals("todo") && t.getDate().equals(dateToPrint)){
                tempTaskList.add(t);
            }
        }
        sortByTime(tempTaskList);
        printer.taskSchedulingFeedback(dateToPrint, tempTaskList);
    }

    // Comparison done using compareTo function
    protected static void sortByTime(ArrayList<Task> list)
    {
        list.sort(Comparator.comparing(Task::getTime));
    }

}
