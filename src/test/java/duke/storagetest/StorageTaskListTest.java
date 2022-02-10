package duke.storagetest;

import duke.storage.StorageTaskList;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.TaskList;
import duke.task.ToDos;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StorageTaskListTest {
    private static TaskList tasks;
    private static ToDos toDos;
    private static Deadline deadline;
    private static Events event;
    private static StorageTaskList store;

    @BeforeAll
    static void init(){
        LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
        tasks = new TaskList();
        toDos = new ToDos("eat");
        deadline = new Deadline("return book", sample);
        event = new Events("Finish Homework",sample.toLocalDate(),sample.toLocalTime(),sample.toLocalTime().plusHours(2));
        tasks.addTask(toDos);
        assert tasks.getListSize()==1:"There should exist  1 task";
        tasks.addTask(deadline);
        assert tasks.getListSize()==2:"There should exist  2 task";
        tasks.addTask(event);
        assert tasks.getListSize()==3:"There should exist  3 task";
        store = new StorageTaskList("Testing.txt");
    }

    @Test
    @Order(1)
    public void fileNotCreated_checkExistenceFunction_false(){
        assertFalse(store.existence());
    }

    @Test
    @Order(2)
    public void fileCreated_checkExistenceFunction_true() throws IOException {
        store.createFile();
        store.save(tasks);
        assertTrue(store.existence());
    }

    @Test
    @Order(3)
    public void checkFileLoadStatus_anotherSameFile_true(){
        StorageTaskList store2 = new StorageTaskList("Testing.txt");
        assertEquals(tasks.getLastTask().toString(), store2.load().getLastTask().toString());
    }

    @AfterAll
    static void wrapUp(){
        File cleanUP = new File(System.getProperty("user.dir") + "/Testing.txt"); // create a File for the given file path
        assertTrue(cleanUP.delete());
    }
}
