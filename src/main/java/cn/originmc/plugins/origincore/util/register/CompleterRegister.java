package cn.originmc.plugins.origincore.util.register;

import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public class CompleterRegister {
    public static boolean register(JavaPlugin plugin,TabCompleter tabCompleter,String command){
        if(plugin.getServer().getPluginCommand(command)==null){
            return false;
        }else{
            plugin.getServer().getPluginCommand(command).setTabCompleter(tabCompleter);
            return true;
        }
    }
}
