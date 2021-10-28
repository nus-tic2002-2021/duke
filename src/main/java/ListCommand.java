public class ListCommand extends Command {
    public boolean isExit(){
        return false;
    }
    int index;
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            if (index == -1){
                throw new IndexOutOfBoundsException();
            }
            ui.readList(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Index out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number that you would like to delete.");
        }
    };
}