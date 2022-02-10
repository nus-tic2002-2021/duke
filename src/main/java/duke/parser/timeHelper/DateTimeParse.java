package duke.parser.timeHelper;

import duke.exception.TimeParseException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeParse {

    static final int  dateLength = 3;

    /**
     * Returns parsed time in format  {DD-MM-YYYY}.
     *
     * @param str - the string of datetime.
     * @return - Date in its correct format.
     * @throws TimeParseException thrown when time format is wrong
     */
    public LocalDate dateParse(String str) throws TimeParseException{
        if (!str.contains("/")){
            throw new TimeParseException("Date Format YYYY/MM/DD");
        }
        // gen yyyy,mm,dd ssss
        String[] date = str.split("/", 3);
        if (date.length < dateLength){
            throw new TimeParseException("Date Format YYYY/MM/DD");
        }

        // gen time
        String[] time = date[2].split(" ", 2);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMth = Integer.parseInt(time[0]);

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year,month,dayOfMth);
        } catch (DateTimeException e) {
            throw new TimeParseException(e.getMessage());
        }

        return localDate;
    }


    /**
     * Returns parsed time as format {DD-MM-YYYY}.
     *
     * @param str - the string of datetime.
     * @return - Date in its correct format.
     * @throws TimeParseException thrown when date format is wrong
     */
    public LocalDateTime dateAndTimeParse(String str) throws TimeParseException{
        if (!str.contains("/")){
            throw new TimeParseException("Date Format YYYY/MM/DD");
        }
        // gen yyyy,mm,dd ssss
        String[] date = str.split("/", 3);
        if (date.length < dateLength){
            throw new TimeParseException("Date Format YYYY/MM/DD");
        }

        // gen time
        String[] time = date[2].split(" ", 2);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMth = Integer.parseInt(time[0]);
        int hour = get24HrFormat(time[1]);

        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.of(year,month,dayOfMth,hour,0,0,0);
        } catch (DateTimeException e) {
            throw new TimeParseException(e.getMessage());
        }

        return localDateTime;
    }

    /**
     * Transforms time to 24hour format.
     *
     * @param t string such as [1AM] [10PM].
     * @return integer time such as 1, 22.
     */
    public int get24HrFormat(String t){
        int timeInterval = 12;
        int timeValue;
        if(t.toUpperCase().contains("AM")){
            timeValue = Integer.parseInt(t.replace("AM", "").stripTrailing());
        } else if(t.toUpperCase().contains("PM")){
            timeValue = Integer.parseInt(t.replace("PM", "").stripTrailing());
            timeValue += timeInterval;
        }else{
            throw new TimeParseException("Time Format 1AM/1PM");
        }

        return timeValue;
    }

    /**
     * Returns time in LocalTime format.
     *
     * @param t the integer time [1] [22]
     * @return time in LocalTime [01:00:00] [22:00:00]
     */
    public LocalTime timeParse(int t){
        return LocalTime.of(t,0,0);
    }
}
