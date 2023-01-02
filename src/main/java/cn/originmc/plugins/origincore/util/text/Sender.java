package cn.originmc.plugins.origincore.util.text;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static cn.originmc.plugins.origincore.util.text.Color.toColor;


public class Sender {
    private static JavaPlugin plugin;
    public Sender(JavaPlugin plugin){
        this.plugin=plugin;
    }
    public void sendToLogger(String inStr){
        plugin.getServer().getConsoleSender().sendMessage(toColor(inStr));
    }
    public void sendToLogger(List<String> inList){
        for (String s : inList) {
            sendToLogger(s);
        }
    }
    public void sendToPlayer(Player player, String inStr){
        player.sendMessage(toColor(inStr));
    }
    public void sendToPlayer(Player player, List<String> inList){
        for (String s : inList) {
            player.sendMessage(toColor(s));
        }
    }
    public void sendToPlayerBC(Player player, List<BaseComponent[]> bcList){
        if(bcList==null){
            return;
        }
        for(BaseComponent[] bc:bcList){
            player.spigot().sendMessage(bc);
        }
    }
    public void sendToPlayerBC(Player player, BaseComponent[] bc){
        if(bc==null){
            return;
        }
        player.spigot().sendMessage(bc);
    }
    public void sendToSender(CommandSender sender, String inStr) {
        sender.sendMessage(toColor(String.valueOf(inStr)));
    }
    public void sendToSender(CommandSender sender, List<String> inList) {
        for (String s : inList) {
            sender.sendMessage(toColor(s));
        }
    }
    public void sendToAllPlayer(String str){
        Bukkit.broadcastMessage(toColor(str));
    }
    public void sendToAllPlayer(List<String> inList){
        for (String s : inList) {
            Bukkit.broadcastMessage(toColor(s));
        }
    }
    public void sendToAllTitle(String title,String sub,int fadeIn,int stay,int fadeOut){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendTitle(toColor(title),toColor(sub),fadeIn,stay,fadeOut);
        }
    }
    public void sendOnEnableMsgToLogger(String pluginName,String author,String version,String type){
        sendToLogger("");
        sendToLogger("    &b"+pluginName+"  &fv"+version+"-"+type+"  &7Successfully loaded");
        sendToLogger("                   &7Made by "+author);
        sendToLogger("");
    }
    public void sendOnDisableMsgToLogger(String pluginName,String author,String version,String type){
        sendToLogger("");
        sendToLogger("    &b"+pluginName+"  &fv"+version+"-"+type+"  &7Successfully unloaded");
        sendToLogger("                   &7Made by "+author);
        sendToLogger("");
    }
}
