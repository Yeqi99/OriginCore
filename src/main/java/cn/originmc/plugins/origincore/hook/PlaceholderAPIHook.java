package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPIHook {
    private static boolean flag;
    public static boolean isLoad() {
        return flag;
    }
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到PlaceholderAPI，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到PlaceholderAPI，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static String getPlaceholder(Player player, String inStr){
        if(isLoad()){
            return PlaceholderAPI.setPlaceholders(player,inStr);
        }else {
            return inStr;
        }
    }
    public static List<String> getPlaceholder(Player player, List<String> strList){
        List<String> returnList=new ArrayList<>();
        for(String s:strList){
            returnList.add(getPlaceholder(player,s));
        }
        return returnList;
    }
    public static void setIsLoad(boolean isLoad) {
        PlaceholderAPIHook.flag = isLoad;
    }
}
