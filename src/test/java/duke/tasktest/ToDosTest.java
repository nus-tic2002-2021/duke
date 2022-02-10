package duke.tasktest;

import duke.task.ToDos;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ToDosTest {

    private static ToDos toDos;

    @BeforeAll
    static void init(){
        toDos = new ToDos("eat");
    }
    @Test
    @Order(1)
    void printToDoSyntax_todoTaskSampleWithoutDuration_success(){
        assertEquals("[T][ ] eat", toDos.toString());
    }

    @Test
    @Order(3)
    void printToDoSyntax_todoTaskSampleWithDuration_success(){
        toDos.setDuration(1);
        assertEquals("[T][ ] eat (requires: 1 Hour)", toDos.toString());
    }

    @Test
    @Order(2)
    void todoEncodeTest_toDoWithNoDuration_success(){
        assertEquals("T | 0 | eat | 0", toDos.encodeTask());
    }

    @Test
    @Order(4)
    void todoEncodeTest_toDoWithDuration_success(){
        assertEquals("T | 0 | eat | 1", toDos.encodeTask());
    }
}
