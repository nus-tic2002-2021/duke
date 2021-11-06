package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task{

    protected LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime){
        super(description);
        this.type = "E";
        this.isDone = false;
        this.dateTime = dateTime;
    }

    @Override
    public String getDescription() {return description.substring(6,description.indexOf('/'));}

    public String getDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    public LocalDateTime getDateTime(){return dateTime;}

    @Override
    public String printTask (){
        //print out every element in the array
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(at: " + getDateTimeStr() + ")";
    }

    public String[] getKeyword (){
        return description.split(" ");
    }
}
