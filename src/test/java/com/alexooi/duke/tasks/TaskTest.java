package com.alexooi.duke.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void getDescriptionSuccess() {
        Task task = new Todo("read book");
        Assertions.assertEquals("read book", task.getDescription());
    }
}
