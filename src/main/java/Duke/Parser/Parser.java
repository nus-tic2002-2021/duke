package Duke.Parser;
import Duke.DukeException;
import Duke.Models.*;
import Duke.Checker.FileLineChecker;
import Duke.Checker.InputChecker;
import Duke.TaskList;
import Duke.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void parseInput(String input) {
        if (input.trim().equals("list")) {
            TaskList.printTaskList();
        } else if (input.startsWith("todo ")) {
            AddTodo(input);
        } else if (input.startsWith("deadline ")) {
            AddDeadline(input);
        } else if (input.startsWith("event ")) {
            AddEvent(input);
        } else if (input.startsWith("done ")) {
            MarkDone(input);
        } else if (input.startsWith("delete ")) {
            DeleteTask(input);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void AddTodo(String input) {
        try {
            if (InputChecker.CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                TaskList.addTaskToList(newTodo);
                Ui.PrintTaskAdded(newTodo);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddDeadline(String input) {
        try {
            if (InputChecker.CheckValidDeadline(input)) {
                Deadline newDeadline = buildDeadline(input);
                TaskList.addTaskToList(newDeadline);
                Ui.PrintTaskAdded(newDeadline);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddEvent(String input) {
        try {
            if (InputChecker.CheckValidEvent(input)) {
                Event newEvent = buildEvent(input);
                TaskList.addTaskToList(newEvent);
                Ui.PrintTaskAdded(newEvent);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void MarkDone(String input) {
        try {
            if (InputChecker.CheckValidDone(input)) {
                TaskList.markTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void DeleteTask(String input) {
        try {
            if (InputChecker.CheckValidDelete(input)) {
                TaskList.DeleteIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static Task ParseStorageLine(String FileLine) throws DukeException {
        String[] parts = FileLine.split("\\|");
        if (parts[0].trim().equals("T")) {
            if(FileLineChecker.CheckTodoLine(parts)) {
                Todo newTodo = getTodoFromLine(parts);
                return newTodo;
            }
        } else if (parts[0].trim().equals("D")) {
            if(FileLineChecker.CheckDeadlineLine(parts)) {
                Deadline newDeadline = getDeadlineFromLine(parts);
                return newDeadline;
            }
        } else if (parts[0].trim().equals("E")) {
            if (FileLineChecker.CheckEventLine(parts)) {
                Event newEvent = getEventFromLine(parts);
                return newEvent;
            }
        } else {
            System.out.println("Line is invalid");
        }
        throw new DukeException("A line from storage file is invalid and will not be added to Duke.Duke.");
    }

    public static Todo getTodoFromLine(String[] parts) {
        Todo newTodo = new Todo(parts[2].trim());
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newTodo);
        }
        return newTodo;
    }

    public static Deadline getDeadlineFromLine(String[] parts) {
        String[] datetime = parts[3].trim().split(" ");
        Deadline newDeadline;
        if (datetime.length > 2 ) {
            newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
        } else {
            LocalDate newDate = null;
            LocalTime newTime = null;
            try {
                newDate = LocalDate.parse(datetime[0]);
                newTime = LocalTime.parse(datetime[1]);
            } catch (DateTimeParseException e) {
                e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
            }
            if (newDate != null && newTime != null) {
                newDeadline = new Deadline(parts[2].trim(), newDate, newTime);
            } else if (newDate != null) {
                newDeadline = new Deadline(parts[2].trim(), newDate);
            } else {
                newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
            }
        }
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newDeadline);
        }
        return newDeadline;
    }

    public static Event getEventFromLine(String[] parts) {
        String[] datetime = parts[3].trim().split(" ");
        Event newEvent;
        if (datetime.length > 2 ) {
            newEvent = new Event(parts[2].trim(), parts[3].trim());
        } else {
            LocalDate newDate = null;
            LocalTime newTime = null;
            try {
                newDate = LocalDate.parse(datetime[0]);
                newTime = LocalTime.parse(datetime[1]);
            } catch (DateTimeParseException e) {
                e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
            }
            if (newDate != null && newTime != null) {
                newEvent = new Event(parts[2].trim(), newDate, newTime);
            } else if (newDate != null) {
                newEvent = new Event(parts[2].trim(), newDate);
            } else {
                newEvent = new Event(parts[2].trim(), parts[3].trim());
            }
        }
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newEvent);
        }
        return newEvent;
    }

    public static boolean checkTimeInput(String timeInput) {
        if (timeInput.equals("")) {
            return false;
        }
        try {
            int time = Integer.parseInt(timeInput);
            int hour = time/100;
            int mins = time%100;
            if (hour < 24 && hour > -1 && mins < 60 && mins > -1) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static LocalTime buildTaskTime(String timeInput) {
        int time = Integer.parseInt(timeInput);
        int hour = time/100;
        int mins = time%100;
        LocalTime taskTime = LocalTime.of(hour, mins);
        return taskTime;
    }

    public static boolean checkDateInput(String[] details) {
        if (details.length != 3) {
            return false;
        }
        try {
            int day = Integer.parseInt(details[0].trim());
            int month = Integer.parseInt(details[1].trim());
            int year = Integer.parseInt(details[2].trim());
            if (day > 31 || day < 1) {
                return false;
            } else if (month > 12 || month < 1) {
                return false;
            } else if (year < 0) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static LocalDate buildTaskDate(String[] details) {
        String day = details[0].trim();
        if (day.length() == 1) {
            day = "0" + day;
        }
        String month = details[1].trim();
        if (month.length() == 1) {
            month = "0" + month;
        }
        String year = details[2].trim();
        if (year.length() < 4) {
            for (int i = year.length(); i < 4; i ++) {
                year = "0" + year;
            }
        }
        String newDateFormat = year + "-" + month + "-" + day;
        LocalDate taskDate = LocalDate.parse(newDateFormat);
        return taskDate;
    }

    public static Deadline buildDeadline(String input) {
        String[] parts = input.substring(8).split("/by");
        String[] datetime = parts[1].trim().split(" ");
        Deadline newDeadline = null;
        if (datetime.length > 2) {
            newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        } else {
            String[] dateDetails = datetime[0].split("/");
            if (checkDateInput(dateDetails)) {
                LocalDate taskDate = buildTaskDate(dateDetails);
                if (datetime.length == 2 && checkTimeInput(datetime[1])) {
                    LocalTime taskTime = buildTaskTime(datetime[1]);
                    newDeadline = new Deadline(parts[0].trim(), taskDate, taskTime);
                } else {
                    newDeadline = new Deadline(parts[0].trim(), taskDate);
                }
            } else {
                newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
            }
        }
        return newDeadline;
    }

    public static Event buildEvent(String input) {
        String[] parts = input.substring(5).split("/at");
        String[] datetime = parts[1].trim().split(" ");
        Event newEvent = null;
        if (datetime.length > 2) {
            newEvent = new Event(parts[0].trim(), parts[1].trim());
        } else {
            String[] dateDetails = datetime[0].split("/");
            if (checkDateInput(dateDetails)) {
                LocalDate taskDate = buildTaskDate(dateDetails);
                if (datetime.length == 2 && checkTimeInput(datetime[1])) {
                    LocalTime taskTime = buildTaskTime(datetime[1]);
                    newEvent = new Event(parts[0].trim(), taskDate, taskTime);
                } else {
                    newEvent = new Event(parts[0].trim(), taskDate);
                }
            } else {
                newEvent = new Event(parts[0].trim(), parts[1].trim());
            }
        }
        return newEvent;
    }

}

