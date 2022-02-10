package duke.parsertest;

import duke.exception.EmptyTaskListException;
import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.ui.UI;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommandDoneTest {

    private static Command c;
    private static StorageTaskList storageTaskList;
    private static UI ui;
    private static TaskList taskList;
    private static String sampleCmd = "done 1";
    @BeforeAll
    static void init(){
        ui = new UI();
        storageTaskList = new StorageTaskList("/doneTest.txt");
        taskList = new TaskList();
        ToDos sampleData = new ToDos("sleep");
        taskList.addTask(sampleData);
    }

    @Test
    void doneCommand_doneTask_successAndFalse(){
        c = CommandParser.parse(sampleCmd);
        c.execute(taskList, ui, storageTaskList);
        assertTrue(taskList.getTask(0).isDone());
        assertFalse(c.isExit());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/doneTest.txt");
        if(cleanUP.exists()){
            assertTrue(cleanUP.delete());
        }
    }
}
