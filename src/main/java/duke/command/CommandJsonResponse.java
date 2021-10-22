package duke.command;

import java.util.List;
import com.google.gson.JsonElement;
import duke.dukeUtility.enums.ResponseType;


public abstract class CommandJsonResponse extends Command {
    private JsonElement _jsonArg;

    protected CommandJsonResponse(ResponseType rt, List<String> args, JsonElement jsonArg) {
        super(rt, args);
        this.setJsonArg(jsonArg);
    }

    public JsonElement getJsonArg() {
        return this._jsonArg;
    }

    private void setJsonArg(JsonElement event) {
        this._jsonArg = event;
    }
}