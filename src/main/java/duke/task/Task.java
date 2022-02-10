package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected LocalDate date;
    protected LocalTime time;

    public static final String DONE = "1";
    public static final String UNDONE = "0";

    /**
     * Creates Task base.
     *
     * @param description - the description string.
     */
    public Task(String description, String taskType, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns task's current status.
     *
     * @return - done or not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task's current status for code usage.
     *
     * @return - 0 or 1.
     */
    public String getStatusCode() {
        return (isDone ? DONE : UNDONE); // mark done task with 1
    }

    /**
     * Returns the full description of each task.
     *
     * @return - returns task description.
     */
    public String getTaskInfo(){
        return description;
    }

    public String getTaskType(){
        return taskType;
    }

    /** Gets the task done condition. */
    public boolean isDone(){
        return isDone;
    }

    /** Sets the task to Done. */
    public void setDone(){
        isDone = true;
    }

    /**
     * Prints task on its own syntax.
     *
     * @return tasks [] TASK.
     */
    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }

    /**
     * Encodes task for storage.
     *
     * @return | X | TASK.
     */
    public String encodeTask() {
        return " | " + getStatusCode() + " | " + description;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getTime(){
        return this.time;
    }
}