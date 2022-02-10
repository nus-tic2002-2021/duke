package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Events extends Task {
    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;

    /**
     * Creates Event task.
     *
     * @param description - Description of Event.
     * @param start - Date/Time event is held.
     * @param end - Date/Time event ends.
     * @param date - the date event is held.
     */
    public Events(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description, "event", date, start);
        this.date = date;
        this.start = start;
        this.end = end;
    }
    /** Prints task by its own style. */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on:" + date.toString() + "[" + date.getDayOfWeek().toString() + "]     from:" + start.toString() +"    to:" + end.toString() +")";
    }
    /** Encodes function for storage within text file */
    @Override
    public String encodeTask() {
        return "E" + super.encodeTask() + " | " + date.toString() + " | " + start.toString() + " | " + end.toString();
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getTime(){
        return this.start;
    }
}
