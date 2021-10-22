package duke.task.aggregator;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import duke.task.model.Task;


public class TaskList {
    private final ConcurrentHashMap<Integer, Task> map = new ConcurrentHashMap<>();

    // helper class
    private Integer getTaskKey(Task task) {
        return task.getTaskId();
    }

    public TaskList() {
    }

    public void addTask(Task t) {
        this.map.put(this.getTaskKey(t), t);
    }

    private ConcurrentHashMap<Integer, Task> getContainer() {
        return this.map;
    }


    public ArrayList<Task> getAllAsArray() {
        return new ArrayList<>(this.getMap().values());
    }


    public Integer getSize() {
        return this.getMap().size();
    }


    public Boolean containsKey(Integer key) {
        return this.getMap().containsKey(key);
    }

    public Task getTaskById(Integer taskId) {
        return this.getMap().get(taskId);
    }

    public Task removeTaskById(Integer taskId) {
        Task task = this.getTaskById(taskId);
        this.getMap().remove(taskId);
        return task;
    }

    private ConcurrentHashMap<Integer, Task> getMap() {
        return this.map;
    }
}
