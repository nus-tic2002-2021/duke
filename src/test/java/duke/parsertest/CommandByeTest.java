package duke.parsertest;

import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommandByeTest {
    private static Command c;
    private static StorageTaskList storageTaskList;
    private static UI ui;
    private static TaskList taskList;
    private static String sampleCmd = "bye";

    @BeforeAll
    static void init(){
        ui = new UI();
        storageTaskList = new StorageTaskList("/byeTest.txt");
        taskList = new TaskList();
    }

    @Test
    void byeCommand_byeTask_successAndTrue(){
        c = CommandParser.parse(sampleCmd);
        c.execute(taskList, ui, storageTaskList);
        assertTrue(c.isExit());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/byeTest.txt"); // create a File for the given file path
        assertTrue(cleanUP.delete());
    }
}
