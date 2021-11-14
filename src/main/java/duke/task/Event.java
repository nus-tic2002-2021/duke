package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    LocalDate on;

    public Event(String description, boolean isDone, LocalDate on) {
        super(description,isDone);
        this.on = on;
    }

    public static Event decode(String s) {
        String[] sentence = s.split(",");
        boolean decodeIsDone = sentence[1].equals("1");
        LocalDate decodeDate = LocalDate.parse(sentence[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return new Event(sentence[2], decodeIsDone, decodeDate);
    }
    @Override
    public String taskEncode () {
        int encodeIsDone = this.getIsDone() ? 1:0;
        return "E" + encodeIsDone + "," + this.getName() + "," + this.getDate();
    }


    public String getDate() {
        return this.on.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    @Override
    public Task complete() {
        return new Event(this.getName(), true, this.on);
    }



    @Override
    public Task setName(String New) {
        return new Event(New, isDone, this.on);
    }
    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[E]" + taskString + " (at: " + this.on + ")";
        return result;
    }


}
