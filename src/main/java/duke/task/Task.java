package duke.task;
/**
 * Represents a task
 */
public abstract class Task {

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected boolean isDone;
    protected String description;

    public boolean getIsDone() {
        return isDone;
    }

    public String getName() {
        return description;
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(getStatusIcon());
    }

    public String getStatusIcon() {

        return ("[" + (this.getIsDone() ? "X" : " ") + "]") + " " + this.getName(); // mark done task with X
    }

    public abstract Task setName(String name);
    public abstract Task complete();

    public abstract String taskEncode();

}
