package duke.task;

import java.util.ArrayList;

public class FixedDurationTaskList {

    protected int listSize;
    private ArrayList<FixedDurationTask> unscheduleTaskList;


    /** Creates Fixed Duration Task List */
    public FixedDurationTaskList() {
        unscheduleTaskList = new ArrayList<>();
    }

    /**
     * Creates Fixed Duration Task List  with records within.
     *
     * @param load - the task list that already contained task.
     */
    public FixedDurationTaskList(FixedDurationTaskList load) {
        this.listSize = load.listSize;
        this.unscheduleTaskList = load.unscheduleTaskList;
    }

    /**
     * Gets the list size.
     *
     * @return - list size in integer.
     */
    public int getListSize(){
        return this.listSize;
    }


    /**
     * Adds an fixed duration task record to  list.
     *
     * @param task - the full task information.
     */
    public void addTask(FixedDurationTask task){
        this.unscheduleTaskList.add(task);
        this.listSize++;
    }

    /**
     * Finds if a fixed duration task exists in record.
     *
     * @param description find by description.
     * @return the task ID if exists.
     */
    public int findRecord(String description){
        for(FixedDurationTask ut : unscheduleTaskList){
            if(description.equals(ut.getDesc())){
                return ut.getDuration();
            }
        }
        return 0;
    }
}
