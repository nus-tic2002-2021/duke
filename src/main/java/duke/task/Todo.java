package duke.task;

/**
 * Represents a To do task
 */

public class Todo extends Task {
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public static Todo decode(String s) {
        String[] sentence = s.split(",");
        boolean decodeIsDone = sentence[1].equals("1");
        return new Todo(sentence[2], decodeIsDone);
    }

    @Override
    public Task complete() {
        return new Todo(this.getName(),true);
    }

    @Override
    public String taskEncode() {
        int encodeIsDone = this.getIsDone() ? 1 : 0;
        return "T," + encodeIsDone + "," + description;
    }


    @Override
    public Task setName(String newName) {
        return new Todo(newName, isDone);
    }
}
