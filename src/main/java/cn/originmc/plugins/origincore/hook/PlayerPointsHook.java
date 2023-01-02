package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerPointsHook {
    private static boolean flag;
    private static PlayerPointsAPI playerPointsAPI;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("PlayerPoints") != null) {
            setPlayerPointsAPI(PlayerPoints.getInstance().getAPI());
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到PlayerPoints，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到PlayerPoints，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static int lookPoints(UUID playerUUID){
        if(isLoad()){
            return playerPointsAPI.look(playerUUID);
        }else {
            return -1;
        }
    }
    public static boolean takePoints(UUID playerUUID,int amount){
        if(isLoad()){
            return playerPointsAPI.take(playerUUID, amount);
        }else {
            return false;
        }
    }
    public static boolean hasPoints(UUID playerUUID,int amount){
        if(isLoad()){
            return lookPoints(playerUUID) >= amount;
        }else {
            return false;
        }
    }
    public static boolean givePoints(UUID playerUUID,int amount){
        if(isLoad()){
            playerPointsAPI.give(playerUUID,amount);
            return true;
        }else {
            return false;
        }
    }
    public static boolean setPoints(UUID playerUUID,int amount){
        if(isLoad()){
            playerPointsAPI.set(playerUUID,amount);
            return true;
        }else {
            return false;
        }
    }
    public static boolean isLoad() {
        return flag;
    }

    public static void setIsLoad(boolean isLoad) {
        PlayerPointsHook.flag = isLoad;
    }

    public static PlayerPointsAPI getPlayerPointsAPI() {
        return playerPointsAPI;
    }

    public static void setPlayerPointsAPI(PlayerPointsAPI playerPointsAPI) {
        PlayerPointsHook.playerPointsAPI = playerPointsAPI;
    }
}
