package duke.parser;

public enum CommandEnums {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    SAVE("save"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    FIND("find"),
    VIEW("view")
    ;

    /**
     * Creates Enum  constructor
     *
     * @param name the keyword for the command
     */
    CommandEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
