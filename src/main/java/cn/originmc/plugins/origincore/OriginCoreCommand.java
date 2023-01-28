package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.mmoitems.MMOItemsManager;
import cn.originmc.plugins.origincore.util.bungeecord.BungeeCordUtil;
import cn.originmc.plugins.origincore.util.command.CommandUtil;
import cn.originmc.plugins.origincore.util.item.Item;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;



public class OriginCoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandUtil c=new CommandUtil(sender,command,label,args);
        if (!c.isAdmin()){
            c.getSender().sendMessage("&c权限不足!");
            return true;
        }
        if (c.getParameterAmount()==0){
            c.getSender().sendMessage("&c参数不足!");
            return true;
        }
        if (c.is(0,"reload")){
            OriginCore.getInstance().reloadConfig();
            c.getSender().sendMessage("&a重载成功!");
        }else if (c.is(0,"tpServer")){
            if (c.getParameterAmount()==2){
                if (!c.isPlayer()){
                    c.getSender().sendMessage("&c只允许玩家执行!");
                    return true;
                }
                String serverName=c.getParameter(1);
                if (PlaceholderAPIHook.isLoad()){
                    serverName=PlaceholderAPIHook.getPlaceholder(c.getPlayer(),serverName);
                }
                BungeeCordUtil.teleport(c.getPlayer(),serverName);
                return true;
            }else if (c.getParameterAmount()>=3){
                if (!c.isPlayer()){
                    c.getSender().sendMessage("&c只允许玩家执行!");
                    return true;
                }
                String playerName=c.getParameter(1);
                String serverName=c.getParameter(2);
                if (PlaceholderAPIHook.isLoad()){
                    playerName=PlaceholderAPIHook.getPlaceholder(c.getPlayer(),playerName);
                    serverName=PlaceholderAPIHook.getPlaceholder(c.getPlayer(),serverName);
                }
                BungeeCordUtil.teleport(c.getPlayer(),playerName,serverName);
                return true;
            }
        }else if (c.is(0,"papi")){
            if (c.getParameterAmount()==2){
                String papi=c.getParameter(1);
                c.getSender().sendMessage(PlaceholderAPIHook.getPlaceholder(c.getPlayer(),papi));
                return true;
            }else if (c.getParameterAmount()>=3){
                String papi=c.getParameter(1);
                Player player=Bukkit.getPlayer(c.getParameter(2));
                if (player==null){
                    c.getSender().sendMessage("&c玩家不存在!");
                    return true;
                }
                c.getSender().sendMessage(PlaceholderAPIHook.getPlaceholder(player,papi));
                return true;
            }
        }else if (c.is(0,"msgPlayer")){
            Player player=Bukkit.getPlayer(c.getParameter(1));
            if (player==null){
                return true;
            }
            String msg=c.getParameter(2);
            OriginCore.getSender().sendToPlayer(player,msg);
            return true;
        }else if (c.is(0,"msgAll")){
            String msg=c.getParameter(1);
            OriginCore.getSender().sendToAllPlayer(msg);
        }
        return true;
    }
}
