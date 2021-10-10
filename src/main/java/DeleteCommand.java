public class DeleteCommand extends Command{
    protected String delete;
    public DeleteCommand(String action, String delete){
        super(action);
        setDelete(delete);
    }
    public void setDelete(String delete) {
        this.delete = delete;
    }
    public void execute(List tasks, Storage storage){
        try {
            tasks.taskDelete(delete);
        } catch(NotFoundException e) {
            System.out.println("\tTask cannot be found.");
        } catch (NumberFormatException e){
            System.out.println("\tInvalid task number entry.");
        }
    }

}