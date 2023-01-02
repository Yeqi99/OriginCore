package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultHook {
    private static boolean flag;
    private static Economy eco;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            setEco(getEconomy());
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到Vault，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到Vault，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static Economy getEconomy(){
        RegisteredServiceProvider<Economy> rsp = OriginCore.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return null;
        }
        return  rsp.getProvider();
    }
    public static boolean isLoad() {
        return flag;
    }
    public static void setIsLoad(boolean isLoad) {
        VaultHook.flag = isLoad;
    }

    public static Economy getEco() {
        return eco;
    }

    public static void setEco(Economy eco) {
        VaultHook.eco = eco;
    }
    public static void giveMoney(Player player, double amount){
        if (eco!=null){
            eco.depositPlayer(player,amount);
        }
    }
    public static double getMoney(Player player){
        if (eco!=null){
            return eco.getBalance(player);
        }
        return -1;
    }
    public static boolean hasMoney(Player player,double amount){
        if(eco!=null){
            return getMoney(player)>=amount;
        }
        return false;
    }
    public static void takeMoney(Player player, double amount){
        if (eco!=null){
            eco.withdrawPlayer(player,amount);
        }
    }
}
