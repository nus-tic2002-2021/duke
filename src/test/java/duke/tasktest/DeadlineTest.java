package duke.tasktest;


import duke.task.Deadline;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static Deadline deadline;

    @BeforeAll
    static void init(){
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        deadline = new Deadline("return book", sample);
    }
    @Test
    void printDeadlineSyntax_deadlineTaskSample_success(){
        assertEquals("[D][ ] return book(by: 2021-12-25[SATURDAY] 16:00)", deadline.toString());
    }

    @Test
    void deadlineEncodeTest_deadlineTask_success(){
        assertEquals("D | 0 | return book | 2021-12-25 | 16:00", deadline.encodeTask());
    }

}
