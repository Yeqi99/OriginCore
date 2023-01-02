package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LuckPermsHook {
    private static boolean flag;
    private static LuckPerms api;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到LuckPerms，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到LuckPerms，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static boolean isLoad() {
        return flag;
    }

    public static void setIsLoad(boolean isLoad) {
        LuckPermsHook.flag = isLoad;
    }

    public static LuckPerms getApi() {
        return api;
    }

    public static void setApi(LuckPerms api) {
        LuckPermsHook.api = api;
    }
}
