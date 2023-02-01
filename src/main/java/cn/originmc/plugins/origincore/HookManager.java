package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.*;
import org.bukkit.plugin.java.JavaPlugin;

public class HookManager {
    /**
     * 尝试与所有插件挂钩
     */
    public static void hookAll(JavaPlugin plugin){
        ProtocolLibHook.hook(plugin);
        PlaceholderAPIHook.hook(plugin);
        VaultHook.hook(plugin);
        PlayerPointsHook.hook(plugin);
        LuckPermsHook.hook(plugin);
        MythicLibHook.hook(plugin);
        MythicMobsHook.hook(plugin);
        MMOItemsHook.hook(plugin);
        ItemsAdderHook.hook(plugin);
    }
}
