package duke.parsertest;

import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.ui.UI;

import java.io.File;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommandTodoTest {

    private static Command c;
    private static StorageTaskList storageTaskList;
    private static UI ui;
    private static TaskList taskList;
    private static String sampleCmd = "todo eat";
    ToDos todo = new ToDos("eat", 0); //since its testing command with the fixed duration database. assume it does not exist.

    @BeforeAll
    static void init(){
        ui = new UI();
        storageTaskList = new StorageTaskList("/todoTest.txt");
        taskList = new TaskList();
    }

    @Test
    void todoCommand_todoTask_successAndFalse(){
        c = CommandParser.parse(sampleCmd);
        c.execute(taskList, ui, storageTaskList);
        assertEquals(todo.toString(), taskList.getLastTask().toString());
        assertFalse(c.isExit());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/todoTest.txt");
        if(cleanUP.exists()){
            assertTrue(cleanUP.delete());
        }
    }
}
