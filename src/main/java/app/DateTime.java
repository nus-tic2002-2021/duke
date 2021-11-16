package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateTime {
    /**
     * To validate user input String date
     * @param date Takes a String input date
     *             verifying if date matches "yyyy-mm-dd" format
     * @return true if matches accurate format
     * @return false if does not match accurate format
     */
    public static boolean isValidDate (String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        boolean valid = false;
        /**
         * parse date with format
         * must be exact with format
         * if true, set
         * @param valid as true
         */
        try {
            sdf.parse(date);
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e){
            valid = false;
        }
        return valid;
    }

    /**
     * To valid user input String time
     * @param time takes a String input time
     *             verifying if time matches "hh:mm" format
     * @return true if matches format
     * @return false if does not match format
     */
    public static boolean isValidTime (String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        boolean valid = false;
        /**
         * parse date with format
         * must be exact with format
         * if true, set
         * @param valid as true
         */
        try {
            sdf.parse(time);
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e){
            valid = false;
        }
        return valid;
    }

    /**
     * Takes user input String date and returns as LocalDate
     * @param date parses to LocalDate
     * @return date as LocalDate
     */
    public static LocalDate toDate(String date){
        LocalDate dt = LocalDate.parse(date);
        return dt;
    }
    /**
     * Takes user input String time and returns as LocalTime
     * @param time parses to LocalTime
     * @return time as LocalTime
     */
    public static LocalTime toTime(String time){
        LocalTime dt = LocalTime.parse(time);
        return dt;
    }
    /**
     * Takes LocalDate date and returns in a new format
     * @param formatter to format date to "dd MMM yyyy"
     * @return new date format
     */
    public static String toNewDateFormat (LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

}
