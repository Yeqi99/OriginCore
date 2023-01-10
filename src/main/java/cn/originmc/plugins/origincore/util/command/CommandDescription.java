package cn.originmc.plugins.origincore.util.command;

import java.util.ArrayList;
import java.util.List;

public class CommandDescription {
    private String command;
    private List<String> description=new ArrayList<>();


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getDescription() {
        return description;
    }
    public List<String> getFormat(){
        List<String> returnList=new ArrayList<>();
        for (String s : description) {
            returnList.add("<bold><color:#5492bf>"+s);
        }
        return returnList;
    }
    public void setDescription(List<String> description) {
        this.description = description;
    }
}
