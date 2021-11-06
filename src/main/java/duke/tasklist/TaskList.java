package duke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask (Task t) {
        taskList.add(t);
    }

    public void removeTask (int i)  {
        taskList.remove(i);
    }

    public void reset() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public TaskList getTaskByDate(LocalDate date, TaskList taskList) {
        TaskList taskByDate = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getType().equals("E") | taskList.get(i).getType().equals("D")) {
                if (taskList.get(i).getDateTime().toLocalDate().equals(date)) {
                    taskByDate.addTask(taskList.get(i));
                }
            }
        }
        return taskByDate;
    }

    public TaskList getTaskByKeyword (String keyword, TaskList taskList) {
        TaskList taskByKeyword = new TaskList();
        for (int i = 0; i < taskList.size(); i++){
            String[] keywordInTask = taskList.get(i).getKeyword();
            for (String s : keywordInTask){
                if (s.contains(keyword) || keyword.contains(s)) {
                    taskByKeyword.addTask(taskList.get(i));
                }
            }
        }
        return taskByKeyword;
    }
}
