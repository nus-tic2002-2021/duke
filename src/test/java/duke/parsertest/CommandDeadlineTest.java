package duke.parsertest;

import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommandDeadlineTest {

    private static Command c;
    private static StorageTaskList storageTaskList;
    private static UI ui;
    private static TaskList taskList;
    private static String sampleCmd = "deadline submit abc/by 2021/12/25 11PM";
    Deadline deadline = new Deadline("submit abc", LocalDateTime.of(2021,12,25,23,0,0));

    @BeforeAll
    static void init(){
        ui = new UI();
        storageTaskList = new StorageTaskList("/deadlineTest.txt");
        taskList = new TaskList();
    }

    @Test
    void deadlineCommand_deadlineTask_successAndFalse(){
        c = CommandParser.parse(sampleCmd);
        c.execute(taskList, ui, storageTaskList);
        assertEquals(deadline.toString(), taskList.getLastTask().toString());
        assertFalse(c.isExit());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/deadlineTest.txt");
        if(cleanUP.exists()){
            assertTrue(cleanUP.delete());
        }
    }
}
