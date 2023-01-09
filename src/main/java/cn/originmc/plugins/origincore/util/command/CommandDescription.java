package cn.originmc.plugins.origincore.util.command;

import java.util.List;

public class CommandDescription {
    private String command;
    private List<String> description;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
