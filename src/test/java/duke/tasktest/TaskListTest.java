package duke.tasktest;

import duke.task.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskListTest {
    private static TaskList tasks;
    private static ToDos toDo;
    private static Events event;
    private static Deadline deadline;
    private static LocalDateTime sample = LocalDateTime.of(2021,12,25,16,0,0);
    @BeforeAll
    static void init(){
        tasks = new TaskList();
    }

    @Test
    @Order(1)
    void addTodo(){
        toDo = new ToDos("eat");
        assertTrue(tasks.addTask(toDo));
    }

    @Test
    @Order(2)
    void addDeadline_deadlineTask_success(){
        deadline = new Deadline("return book", sample);
        assertTrue(tasks.addTask(deadline));
    }

    @Test
    @Order(3)
    void addEvent_eventTask_success(){
        event = new Events("Finish Homework",sample.toLocalDate(),sample.toLocalTime(),sample.toLocalTime().plusHours(2));
        assertTrue(tasks.addTask(event));
    }
    @Test
    @Order(4)
    void checkSize_taskListSize_success(){
        assertEquals(3, tasks.getListSize());
    }

    @Test
    @Order(5)
    void getLastTask_retrieveLastTask_success(){
        assertEquals(event,tasks.getLastTask());
    }

    @Test
    @Order(6)
    void getTaskByID_retrieveTaskID_success(){
        assertEquals(toDo, tasks.getTask(0));
    }
}
