package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */

public class Deadlines extends Task {
    protected LocalDate by;

    public Deadlines(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
        this.by = by;
    }

    public String getDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static Deadlines decode(String s) {
        String[] sentence = s.split(",");
        boolean decodeIsDone = sentence[1].equals("1");
        LocalDate decodeDate = LocalDate.parse(sentence[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Deadlines(sentence[2], decodeIsDone, decodeDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public Task complete() {
        return new Deadlines(this.getName(),true,by);
    }

    @Override
    public String taskEncode() {
        int encodeIsDone = this.getIsDone() ? 1 : 0;
        return "D," + encodeIsDone + "," + description + "," + this.getDate();
    }


    @Override
    public Task setName(String newName) {
        return new Deadlines(newName, isDone,by);
    }
}


