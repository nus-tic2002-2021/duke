package duke.ui;

import duke.exception.TaskNotFoundException;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;


/** Handles message output. */
public class ReturnMessages {
    public void separator(){
        System.out.println("    ____________________________________________________________");
    }

    /** Feedbacks when marking a task done. */
    public void taskDoneFeedback(){
        separator();
        System.out.println("     Nice! I've marked this task as done:");
    }

    /** Feedbacks when a task is already done. */
    public void taskAlreadyDoneFeedback(){
        separator();
        System.out.println("     This task is already done.");
    }


    /** Feedbacks when adding a task. */
    public void taskAddFeedback(){
        separator();
        System.out.println("     Got it. I've added this task:");
    }

    /** Feedbacks on program startup. */
    public void welcomeFeedback(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        separator();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    Here are my functionality....");
        System.out.println("    Check Existing Tasks:    list");
        System.out.println("    Mark a Task done:        done {taskID}");
        System.out.println("    Delete a Task:           delete {taskID}");
        System.out.println("    Find a Task:             find {keyword}");
        System.out.println("    View Schedule:           view {date by YYYY/MM/DD}");
        System.out.println("    Save Current State:      save");
        System.out.println("    Add a Todo Task:         todo {action}");
        System.out.println("    Add a Event Task:        event xxxx /at {date by YYYY/MM/DD} {startTime}AM/PM-{endTime}AM/PM");
        System.out.println("    Add a Deadline Task:     deadline XXXX /by {date by YYYY/MM/DD} {Time}AM/PM");
        System.out.println("    End the program:         bye");
        separator();
    }

    /** Feedbacks on program exit. */
    public void exitFeedback(){
        separator();
        System.out.println("    Bye. Hope to see you again soon!");
        separator();
    }

    /** Feedbacks when loading file. */
    public void loadingFileFeedback(){
        separator();
        System.out.println("     Loading File...");
        separator();
    }

    /** Feedbacks on file load success. */
    public void loadSuccessFeedback(){
        separator();
        System.out.println("     File Loaded Successfully, use command 'list' to view...");
        separator();
    }

    /** Feedbacks on file save. */
    public void saveSuccessFeedback(){
        separator();
        System.out.println("     Successfully wrote to the file.");
        separator();
    }

    /** Feedbacks on file create. */
    public void createSuccessFeedback(String name){
        separator();
        System.out.println("File created: " + name);
        separator();
    }

    /**
     * Returns full task list with numbering.
     *
     * @param list the full task list.
     */
    public void printFullTaskList(ArrayList<Task> list){
        separator();
        int numbering = 1;
        System.out.println("     Here are the tasks in your list:");
        for(Task t : list){
            System.out.println("    "+numbering+"."+t.toString());
            numbering++;
        }
        separator();
    }

    public void taskSchedulingFeedback(LocalDate datePrint, ArrayList<Task> list){
        separator();
        System.out.println("     Here are the tasks in your list scheduled for: " +datePrint.toString() + ", "+datePrint.getDayOfWeek());
        for(Task t : list){
            System.out.println("    "+t.toString());
        }
        separator();
    }


    /**
     * Returns task found with keyword with its correct numbering.
     *
     * @param key keyword.
     * @param list full task list.
     */
    public void taskFoundFeedback(String key, ArrayList<Task> list){
        assert !list.isEmpty():"Should be caught by emptyTaskListException";
        ArrayList<Task> tempList = new ArrayList<>();
        for(Task t : list){
            if(t.getTaskInfo().contains(key)){
                tempList.add(t);
            }
        }
        if(tempList.isEmpty()){
            throw new TaskNotFoundException("No task found with keyword ["+key+"].");
        }else{
            separator();
            int numbering = 1;
            System.out.println("     Here are the matching tasks in your list:");
            for(Task t : tempList){
                System.out.println("    "+numbering+"."+t);
                numbering++;
            }
            separator();
        }
    }

    /**
     * Feedbacks message on emptyDescription Exception thrown.
     *
     * @param taskType The type of task (To Do, Event, Deadline).
     */
    public void exception_feedback_emptyDescription(String taskType){
        separator();
        System.out.printf("     OOPS!!! The description of a %s cannot be empty.\n", taskType);
        separator();
    }

    /** Feedbacks message on emptyTaskList Exception thrown. */
    public void exception_feedback_emptyTaskList(){
        separator();
        System.out.println("     OOPS!!! The list is empty ^_^");
        separator();
    }

    /**
     * Feedbacks message on unknownSyntax Exception thrown.
     *
     * @param message - user input.
     */
    public void exception_feedback_unknownSyntax(String message){
        separator();
        System.out.printf("     OOPS!!! I'm sorry, but I don't know what [%s] means :-(\n", message);
        separator();
    }

    /**
     * Feedbacks message on task not found Exception thrown.
     *
     * @param message - the task description.
     */
    public void exception_feedback_taskNotFound(String message){
        separator();
        System.out.printf("     OOPS!!! Task was not found [%s]\n", message);
        separator();
    }

    /** Feedbacks message on noTimeConcept Exception thrown. */
    public void exception_feedback_noTimeConcept(){
        separator();
        System.out.println("     HEY!!! Please give a due date for deadlines and events or the syntax could be wrong");
        separator();
    }

    /** Feedbacks message on Time Parse Exception thrown. */
    public void exception_feedback_timeParse(String message){
        separator();
        System.out.printf("     There seems to have problem with your time format [%s]\n", message);
        separator();
    }

    /** Feedbacks message on Saving Exception thrown. */
    public void exception_feedback_cantSave(){
        separator();
        System.out.println("     OOPS!!! Exception caught while saving >_<|||");
        separator();
    }

    /** Feedbacks message on Loading Exception thrown. */
    public void exception_feedback_loadingError(){
        separator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     OOPS!!! Something went wrong while loading");
        separator();
    }

    /** Feedbacks message on an Unknown Exception thrown. */
    public void exception_feedback_unknownError(){
        separator();
        System.out.println("     Sorry, An unknown error occurred");
        separator();
    }
}
