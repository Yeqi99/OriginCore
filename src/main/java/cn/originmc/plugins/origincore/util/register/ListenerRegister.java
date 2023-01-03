package cn.originmc.plugins.origincore.util.register;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerRegister {
    /**
     * 注册Minecraft事件监听器
     * @param plugin 插件实例
     * @param listener 监听器
     */
    public static void register(JavaPlugin plugin, Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener,plugin);
    }
}
