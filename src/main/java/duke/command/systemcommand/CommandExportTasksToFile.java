package duke.command.systemcommand;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandExportTasksToFile extends Command {
    /**
     * Export tasks to file as JSON. Will return response type based on the execution.
     *
     * @param tasks task objects to write
     * @param jw    writer
     */
    public CommandExportTasksToFile(JsonArray tasks, JsonWriter jw) {
        super(ResponseType.EXPORT_IN_PROGRESS, List.of("export", jw.toString()));
        try {
            new Gson().toJson(tasks, jw);
            jw.close();
            this.setResponseType(ResponseType.FILE_SAVED);
        } catch (Exception error) {
            this.setResponseType(ResponseType.ERROR_SAVE_FAILURE);
        }
    }
}
