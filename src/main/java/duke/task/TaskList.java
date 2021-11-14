package duke.task;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Task list
 */

public class TaskList {
    private static List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTasks(Task newTask) {
        tasks.add(newTask);
    }

    public Task deleteTasks(int num) {
        int index = num - 1;
        Task removeTask = tasks.remove(index);
        return removeTask;
    }
    public Task setDone(int num) {
        int i = num - 1;
        Task current = tasks.get(i);
        Task completed = current.complete();
        tasks.set(i, completed);
        return completed;
    }

    public List<Task> getList() {
        return tasks;
    }
    public String getAllTasks() {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n";
            Task currentTask = tasks.get(i);
            int taskNumber = i + 1;
            result += taskNumber + ". " + currentTask;
        }
        return result;
    }
    public static String findTask(String keyword) {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.toString().contains(keyword)) {
                result += "\n";
                int taskNumber = i + 1;
                result += taskNumber + ". " + currentTask;
            }
        }
        return result;
    }

}
