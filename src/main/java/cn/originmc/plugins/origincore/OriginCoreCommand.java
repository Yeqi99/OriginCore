package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.mythicmobs.MythicMobsManager;
import cn.originmc.plugins.origincore.util.action.object.Actions;
import cn.originmc.plugins.origincore.util.bungeecord.BungeeCordUtil;
import cn.originmc.plugins.origincore.util.command.CommandUtil;
import cn.originmc.plugins.origincore.util.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class OriginCoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandUtil c=new CommandUtil(sender,command,label,args);
        if (!c.isAdmin()){
            OriginCore.getSender().sendToSender(sender,"&c权限不足!");
            return true;
        }
        if (c.getParameterAmount()==0){
            OriginCore.getSender().sendToSender(sender,"&c参数不足!");
            return true;
        }
        if (c.is(0,"reload")){
            OriginCore.getInstance().reloadConfig();
            OriginCore.getSender().sendToSender(sender,"&a重载成功!");
        }else if (c.is(0,"tpServer")){
            if (c.getParameterAmount()==2){
                if (!c.isPlayer()){
                    OriginCore.getSender().sendToSender(sender,"&c只允许玩家执行!");
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
                    OriginCore.getSender().sendToSender(sender,"&c只允许玩家执行!");
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
                    OriginCore.getSender().sendToSender(sender,"&c玩家不存在!");
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
        }else if (c.is(0,"attribute")){
            Item item=new Item(c.getPlayer().getInventory().getItemInMainHand());
            String id=c.getParameter(1);
            Attribute attribute= Attribute.valueOf(c.getParameter(2));
            AttributeModifier.Operation operation= AttributeModifier.Operation.valueOf(c.getParameter(3));
            EquipmentSlot slot=EquipmentSlot.valueOf(c.getParameter(4));
            double value= Double.parseDouble(c.getParameter(5));
            item.setAttribute(id,attribute,value,operation,slot);
            c.getPlayer().getInventory().setItemInMainHand(item.getItemStack());
        }else if (c.is(0,"skill")){
            Player player=c.getPlayer();
            String skillName=c.getParameter(1);
            LivingEntity livingEntity=MythicMobsManager.getTarget(player);
            List<Entity> targets = new ArrayList();
            targets.add(livingEntity);
            float power= Float.parseFloat(c.getParameter(2));
            MythicMobsManager.castSkill(player,skillName,player,player.getLocation(),targets,null,power,null);
        }else if (c.is(0,"test")){
            List<String> actionString=new ArrayList<>();
            actionString.add("type^MMSkill`skill^长剑普攻`power^10");
            actionString.add("type^Delay`time^500");
            actionString.add("type^MMSkill`skill^长剑普攻`power^10");
            actionString.add("type^Delay`time^500");
            actionString.add("type^MMSkill`skill^长剑普攻`power^10");
            actionString.add("type^Delay`time^500");
            actionString.add("type^MMSkill`skill^长剑普攻`power^10");

            Actions actions=new Actions(actionString);
            actions.putObject("self",c.getPlayer());
            actions.execute();
        }
        return true;
    }

}
