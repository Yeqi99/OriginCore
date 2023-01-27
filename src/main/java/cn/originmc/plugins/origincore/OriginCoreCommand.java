package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.util.command.CommandUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;



public class OriginCoreCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandUtil c=new CommandUtil(sender,command,label,args);
        if (!c.isAdmin()){
            return true;
        }
        if (c.getParameterAmount()==0){
            return true;
        }
        if (c.is(0,"reload")){
            OriginCore.getInstance().reloadConfig();
        }
        return true;
    }
}
