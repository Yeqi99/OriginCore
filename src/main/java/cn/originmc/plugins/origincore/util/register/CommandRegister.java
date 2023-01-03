package cn.originmc.plugins.origincore.util.register;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {
    /**
     * 注册Minecraft指令监听器
     * @param plugin 插件实例
     * @param ce 指令实现实例
     * @param commandStr 指令文本
     * @return 结果
     */
    public static boolean register(JavaPlugin plugin, CommandExecutor ce, String commandStr){
        if(plugin.getServer().getPluginCommand(commandStr)==null){
            return false;
        }else{
            plugin.getServer().getPluginCommand(commandStr).setExecutor(ce);
            return true;
        }
    }
}
