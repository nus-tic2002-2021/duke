package duke.parsertest;

import duke.exception.TimeParseException;
import duke.parser.timeHelper.DateTimeParse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DateTimeParseTest {
    private static final String wrongDate = "20211225";
    private static final String wrongDT = "20211225 10PM";
    private static final String rDwT = "2021/12/25 10";
    private static final String correctDate = "2021/12/25";
    private static DateTimeParse timeObject;

    @BeforeAll
    static void init(){
        timeObject = new DateTimeParse();
    }

    @Test
    void dateParse_correctFormattedDate_success(){
        LocalDate sample = LocalDate.of(2021,12,25);
        assertEquals(sample, timeObject.dateParse(correctDate));
    }

    @Test
    void timeParse_integerTimeToConvert_success(){
        LocalTime sample = LocalTime.of(10,0,0);
        assertEquals(sample, timeObject.timeParse(10));
    }


    @Test
    void dateParse_wrongFormattedDate_exceptionThrown() {
        TimeParseException exception = assertThrows(TimeParseException.class, () -> {
            timeObject.dateParse(wrongDate);
        });
        String errorMsg = exception.getMessage();
        assertEquals("Date Format YYYY/MM/DD", errorMsg);
    }

    @Test
    void dateTimeParse_wrongFormattedDateTime_exceptionThrown() {
        TimeParseException exception = assertThrows(TimeParseException.class, () -> {
            timeObject.dateAndTimeParse(wrongDT);
        });
        String errorMsg = exception.getMessage();
        assertEquals("Date Format YYYY/MM/DD", errorMsg);
    }

    @Test
    void dateTimeParse_wrongFormattedTime_exceptionThrown() {
        TimeParseException exception1 = assertThrows(TimeParseException.class, () -> {
            timeObject.dateAndTimeParse(rDwT);
        });
        String errorMsg1 = exception1.getMessage();
        assertEquals("Time Format 1AM/1PM", errorMsg1);
    }
}
