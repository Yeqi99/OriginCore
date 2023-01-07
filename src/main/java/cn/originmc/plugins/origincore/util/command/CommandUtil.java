package cn.originmc.plugins.origincore.util.command;

import cn.originmc.plugins.origincore.hook.LuckPermsHook;
import cn.originmc.plugins.origincore.hook.luckperms.LuckPermsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandUtil {
    private CommandSender sender;
    private Command command;
    private String label;
    private String[] args;

    public CommandUtil(CommandSender sender, Command command, String label, String[] args){
        setSender(sender);
        setCommand(command);
        setLabel(label);
        setArgs(args);
    }
    public boolean isPlayer(){
        return getSender() instanceof Player;
    }
    public boolean hasParameter(){
        return getParameterAmount()>0;
    }
    public boolean hasParameter(int index){
        return index<getParameterAmount() & hasParameter();
    }
    public int getParameterAmount(){
        return args.length;
    }
    public String getParameter(int index){
        if (hasParameter(index)){
            return args[index];
        }
        return "";
    }
    public List<String> getParameterList(){
        return new ArrayList<>(Arrays.asList(args));
    }
    public boolean hasPerm(String perm){
        if (!isPlayer()){
            return true;
        }
        if (LuckPermsHook.isLoad()){
            return LuckPermsManager.hasPermission(getPlayer(),perm);
        }else {
            return getPlayer().hasPermission(perm);
        }
    }
    public boolean inGroup(String groupName){
        if (!isPlayer()){
            return true;
        }
        return getPlayer().hasPermission("group." + groupName);
    }
    public boolean isAdmin(){
        if (isPlayer()){
            return getPlayer().isOp();
        }else {
            return true;
        }
    }
    public Player getPlayer(){
        return (Player) sender;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public boolean is(int index,String Parameter){
        if (!hasParameter(index)){
            return false;
        }
        return getParameter(index).equalsIgnoreCase(Parameter);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }
    public CommandSender getSender(){
        return sender;
    }

}
