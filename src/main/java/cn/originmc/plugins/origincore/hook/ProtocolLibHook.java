package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtocolLibHook {
    private static boolean flag;
    private static ProtocolManager pm;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            setPm(ProtocolLibrary.getProtocolManager());
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到ProtocolLib，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到ProtocolLib，部分功能无效");
            }
            setIsLoad(false);
        }
    }

    public static boolean isLoad() {
        return flag;
    }

    public static void setIsLoad(boolean isLoad) {
        ProtocolLibHook.flag = isLoad;
    }

    public static ProtocolManager getPm() {
        return pm;
    }

    public static void setPm(ProtocolManager pm) {
        ProtocolLibHook.pm = pm;
    }
}
