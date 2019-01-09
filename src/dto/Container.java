package dto;

import java.util.HashMap;

public class Container {

    private HashMap<String, String> coms = new HashMap<>();
//    private String command;
//    private String table;

    public String getTable() {
        return coms.get("table");
    }

    public HashMap<String, String> getComs() {
        return coms;
    }

    public void setTable(String table) {
        coms.put("table", table);
    }

    public Commands getCommand() {
        return Commands.valueOf(coms.get("command"));
    }

    public void setCommand(Commands command) {
        coms.put("table", command.name());
    }

}
