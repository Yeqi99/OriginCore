package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MultiverseCoreHook {
    private static boolean flag;
    public static MultiverseCore getMv(){
        return (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
    }
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("Multiverse-Core") != null) {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到Multiverse-Core，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到Multiverse-Core，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static void setIsLoad(boolean isLoad) {
        MultiverseCoreHook.flag = isLoad;
    }

    public static boolean isLoad() {
        return flag;
    }
}
