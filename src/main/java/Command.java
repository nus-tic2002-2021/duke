abstract class Command {
    protected String action;
    public Command(String action){
        setAction(action);
    }

    public void setAction(String action) {
        this.action = action;
    }
    public boolean isExit(){
        return false;
    }
    public abstract void execute(List tasks, Storage storage,UI ui);


}
