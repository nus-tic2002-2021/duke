package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected LocalDate date;

    /**
     * Creates Deadline task.
     *
     * @param description - the task for the deadline.
     * @param by - the deadline's datetime.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, "deadline", by.toLocalDate(), by.toLocalTime());
        this.by = by;
        this.date = by.toLocalDate();
    }

    /** Prints task by its own style. */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.toLocalDate().toString() + "["+ by.toLocalDate().getDayOfWeek() +"] "+ by.toLocalTime().toString()+")";
    }

    /** Encodes function for storage within text file. */
    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + " | " + by.toLocalDate().toString() + " | " + by.toLocalTime().toString();
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getTime(){
        return this.by.toLocalTime();
    }
}