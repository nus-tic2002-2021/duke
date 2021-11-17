package duke.unittest.tasktest;

import static duke.testhelper.help.codeundertest.ParserUnderTest.parseStringAsLocalDate;
import static duke.testhelper.help.codeundertest.ParserUnderTest.parseStringAsLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.TaskComparator;
import duke.task.model.Deadline;
import duke.testhelper.TestStream;

public class ComparisonTest extends TestStream {

    @Test
    public void shouldBeSameDay() throws Exception {
        LocalDateTime dl = parseStringAsLocalDateTime("20200101 00:50");
        Deadline deadline = new Deadline("writing assignment", dl, 0, false);
        LocalDate targetDate = parseStringAsLocalDate("20200101");
        Assertions.assertTrue(TaskComparator.isSameDay(deadline, targetDate));
    }
}

