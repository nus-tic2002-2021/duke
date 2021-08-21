package com.lockarhythm.tasks;

import java.util.ArrayList;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    public String getDescription() {
      return description;
    }

    public boolean isDone() {
      return isDone;
    }

    private String getDoneIcon() {
      return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneIcon(), description);
    }
}
