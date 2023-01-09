package cn.originmc.plugins.origincore.util.command;

import java.util.ArrayList;
import java.util.List;

public class HelpMessage {
    private String title;
    private int pageSize;
    private List<CommandDescription> descriptions=new ArrayList<>();
    public HelpMessage(String title,int pageSize){
        setTitle(title);
        setPageSize(pageSize);
    }
    public List<String> getPage(int pageIndex){
        if (getPageAmount()<=pageIndex){
            return null;
        }
        List<String> helpList=new ArrayList<>();
        for (CommandDescription description : descriptions) {
            helpList.add(description.getCommand());
            helpList.addAll(description.getDescription());
        }
        List<String> returnList=new ArrayList<>();
        returnList.add("<bold><gradient:#5e4fa2:#f79459:red>---------"+getTitle()+"---------</gradient>");
        returnList.addAll(helpList.subList(pageIndex*getPageSize(),(pageIndex+1)*getPageSize()));
        returnList.add("<bold><gradient:#5e4fa2:#f79459:red>---------"+getTitle()+"---------</gradient>");
        returnList.add("<bold><gradient:#5e4fa2:#f79459:red>(page."+(pageIndex+1)+")</gradient>");
        return returnList;
    }
    public int getPageAmount(){
        int amount=0;
        for (CommandDescription description : descriptions) {
            amount+=description.getDescription().size();
        }
        return amount/getPageSize()+1;
    }

    public void addDescriptionLine(String command,String description){
        if (!hasDescription(command)){
            CommandDescription commandDescription=new CommandDescription();
            commandDescription.setCommand(command);
            commandDescription.getDescription().add(description);
            return;
        }
        for (CommandDescription commandDescription : descriptions) {
            if (commandDescription.getCommand().equalsIgnoreCase(command)){
                commandDescription.getDescription().add(description);
            }
        }
    }
    public boolean hasDescription(String command){
        for (CommandDescription description : descriptions) {
            if (description.getCommand().equalsIgnoreCase(command)){
                return true;
            }
        }
        return false;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommandDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<CommandDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
