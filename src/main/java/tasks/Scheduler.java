package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A <code>Scheduler</code> object stores the start and end datetime of events.
 * It will check for clashes before an event is added to the task list.
 * Return true if the new event can be added and false otherwise.
 */
public class Scheduler {

    private final ArrayList<String> descriptions;
    private final ArrayList<LocalDateTime> starts;
    private final ArrayList<LocalDateTime> ends;

    public Scheduler(){
        descriptions = new ArrayList<>();
        starts = new ArrayList<>();
        ends = new ArrayList<>();
    }

    /**
     * The core scheduling function.
     * Return true if the event is scheduled, false if the event cannot be added.
     *
     * @param description Brief description of the new event.
     * @param newStart Start DateTime of the new event.
     * @param newEnd End DateTime of the new event.
     */
    public boolean schedule(String description, LocalDateTime newStart, LocalDateTime newEnd) {
        // start time is later than end time
        if (newEnd.isBefore(newStart)) {
            return false;
        }

        // no event in the schedule
        boolean scheduleIsEmpty = starts.size() == 0;
        if (scheduleIsEmpty) {
            descriptions.add(description);
            starts.add(newStart);
            ends.add(newEnd);
            return true;
        }

        boolean startLate = newStart.isAfter(starts.get(starts.size()-1)) || newStart.isEqual(starts.get(starts.size()-1));
        boolean startAfterLastEvent = newStart.isAfter(ends.get(ends.size()-1)) || newStart.isEqual(ends.get(ends.size()-1));
        boolean startEarly = newStart.isBefore(starts.get(0)) || newStart.isEqual(starts.get(0));
        boolean endBeforeFirstEvent = newEnd.isBefore(starts.get(0)) || newEnd.equals(starts.get(0));
        if (startLate) {
            if (startAfterLastEvent) {
                descriptions.add(description);
                starts.add(newStart);
                ends.add(newEnd);
                return true;
            }
        } else if (startEarly) {
            if (endBeforeFirstEvent){
                descriptions.add(0,description);
                starts.add(0,newStart);
                ends.add(0, newEnd);
                return true;
            }
        } else { //start somewhere in the middle
            int index = searchForNewSlot(newStart);
            if (index == -1) { //clashed
                return false;
            }
            boolean startNotClash = newStart.isAfter(ends.get(index - 1)) || newStart.isEqual(ends.get(index - 1));
            boolean endNotClash = newEnd.isBefore(starts.get(index)) || newEnd.isBefore(starts.get(index));
            if (startNotClash && endNotClash) {
                descriptions.add(index, description);
                starts.add(index, newStart);
                ends.add(index, newEnd);
                return true;
            }
        }

        return false;
    }

    /**
     * The helper function to binary search for slot based on event start time.
     * Return -1 if there is time clashes, else return the index of the slot.
     *
     * @param start Start DateTime of the new event.
     */
    private int searchForNewSlot(LocalDateTime start){
        int left = 0;
        int right = starts.size() - 1;

        while (left < right){
            int mid = (left + right) / 2;
            if (starts.get(mid).equals(start)) {
                return -1;
            } else if (starts.get(mid).isAfter(start)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * Load schedule of existing events.
     *
     * @param tasks List of tasks loaded from existing tasks file.
     */
    public void loadSchedule(ArrayList<Task> tasks){
        for (Task task: tasks) {
            if (task.getClass().equals(EventTask.class)) {
                EventTask event = (EventTask) task;
                schedule(event.description, event.start, event.end);
            }
        }
    }

    /**
     * Another helper function to binary search for slot based on event start time.
     * Return -1 if the slot is not found, else return the index of the slot.
     *
     * @param start Start DateTime of the new event.
     */
    private int retrieveSlot(LocalDateTime start){
        int left = 0;
        int right = starts.size() - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (starts.get(mid).equals(start)) {
                return mid;
            } else if (starts.get(mid).isAfter(start)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Remove a particular event from the schedule (clear the slot).
     *
     * @param start Start DateTime of the new event.
     */
    public void freeUpSlot(LocalDateTime start){
        int index = retrieveSlot(start);
        descriptions.remove(index);
        starts.remove(index);
        ends.remove(index);
    }

    /**
     * Retrieve and return the list of events scheduled on the input date.
     *
     * @param date Date of schedule.
     */
    public ArrayList<EventTask> getSchedule(LocalDate date) {
        ArrayList<EventTask> events = new ArrayList<>();
        for (int i = 0; i < starts.size(); i++) {
            if(starts.get(i).toLocalDate().equals(date)){
                String description = descriptions.get(i);
                LocalDateTime start = starts.get(i);
                LocalDateTime end = ends.get(i);
                EventTask event = new EventTask(description, start, end);

                events.add(event);
            }
        }
        return events;
    }
}
