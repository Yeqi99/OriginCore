package cn.originmc.plugins.origincore.util.register;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerRegister {
    public static void register(JavaPlugin plugin, Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener,plugin);
    }
}
