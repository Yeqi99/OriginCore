package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.*;
import cn.originmc.plugins.origincore.util.data.database.mysql.MysqlManager;
import cn.originmc.plugins.origincore.util.text.Sender;
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
        boolean mysql= MysqlManager.hook();
        if (mysql){
            new Sender(plugin).sendToLogger("&a检查到Mysql，对应功能就绪");
        }else {
            new Sender(plugin).sendToLogger("&c没有找到Mysql，部分功能无效");
        }
    }
}
