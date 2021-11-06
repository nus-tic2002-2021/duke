package duke.tasklist;

import java.time.LocalDateTime;

/**
 * A <code>Task</code> class is the parent class of all types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime dateTime;

    /**
     * Default task
     * @param description parses from user input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns description of the task
     * @return task description in String
     */
    public String getDescription() {return description;}

    //isDone.Setter
    public void setDone(boolean isDone) {this.isDone = isDone;}

    public boolean isDone() { return isDone; }

    //isDone.Getter
    public String getStatusIcon() {
        return (isDone ? "√" : " "); // mark done task with X
    }

    public String getType(){return type;}

    public String getDateTimeStr(){
        return "";
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public String printTask(){return "";}

    public String[] getKeyword(){
        return description.split(" ");
    }

}