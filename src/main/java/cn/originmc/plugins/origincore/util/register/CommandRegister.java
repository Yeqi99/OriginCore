package cn.originmc.plugins.origincore.util.register;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {
    public static boolean register(JavaPlugin plugin, CommandExecutor ce, String commandStr){
        if(plugin.getServer().getPluginCommand(commandStr)==null){
            return false;
        }else{
            plugin.getServer().getPluginCommand(commandStr).setExecutor(ce);
            return true;
        }
    }
}
