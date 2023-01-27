package cn.originmc.plugins.origincore.hook;


import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MMOItemsHook {
    private static boolean flag;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("MMOItems") != null) {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到MMOItems，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到MMOItems，部分功能无效");
            }
            setIsLoad(false);
        }
    }

    public static void setIsLoad(boolean isLoad) {
        MMOItemsHook.flag = isLoad;
    }

    public static boolean isLoad() {
        return flag;
    }
}
