package duke.tasklist;

public class Todo extends Task{
    public Todo(String description){
        super(description);
        this.type = "T";
        this.isDone = false;
    }

    public String getDescription() {return description.substring(5);}

    public String printTask (){
            return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription();
    }

    public String[] getKeyword (){
        return description.split(" ");
    }
}
