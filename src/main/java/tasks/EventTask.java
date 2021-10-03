package tasks;

public class EventTask extends Task {

    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getAt() { return at; }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
