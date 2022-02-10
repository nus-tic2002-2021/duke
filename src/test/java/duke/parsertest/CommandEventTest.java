package duke.parsertest;

import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.Events;
import duke.task.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommandEventTest {

    private static Command c;
    private static StorageTaskList storageTaskList;
    private static UI ui;
    private static TaskList taskList;
    private static String sampleCmd = "event project meeting/at 2021/12/25 11AM-2PM";
    Events event = new Events("project meeting", LocalDate.of(2021,12,25), LocalTime.of(11,0), LocalTime.of(14,0));

    @BeforeAll
    static void init(){
        ui = new UI();
        storageTaskList = new StorageTaskList("/eventTest.txt");
        taskList = new TaskList();
    }

    @Test
    void eventCommand_eventTask_successAndFalse(){
        c = CommandParser.parse(sampleCmd);
        c.execute(taskList, ui, storageTaskList);
        assertEquals(event.toString(), taskList.getLastTask().toString());
        assertFalse(c.isExit());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/eventTest.txt");
        if(cleanUP.exists()){
            assertTrue(cleanUP.delete());
        }
    }
}
