package duke.task;

public class ToDos extends Task {
    protected int duration;

    /**
     * Creates To Do task
     *
     * @param description - Description of To Do task.
     */
    public ToDos(String description) {
        this(description,0);

    }
    /**
     * Creates To Do task.
     *
     * @param description - Description of To Do task.
     * @param duration - unscheduled fixed duration.
     */
    public ToDos(String description, int duration) {
        super(description, "todo", null, null);
        this.duration = duration;
    }
    public int getDuration(){
        return this.duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    /** Prints task by its own style. */
    @Override
    public String toString() {
        if(this.duration>0){
            return "[T]" + super.toString()+ " (requires: " + duration + " Hour)";
        }
        return "[T]" + super.toString();
    }

    /** Encodes function for storage within text file. */
    @Override
    public String encodeTask() {
        return "T" + super.encodeTask() + " | " + duration;
    }
}
