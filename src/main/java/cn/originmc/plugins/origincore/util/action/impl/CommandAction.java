package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class CommandAction extends AbstractAction {


    public CommandAction( FormatText actionSetting, Map<String, Object> objectMap) {
        super(actionSetting, objectMap);
    }

    public CommandAction(FormatText actionSetting) {
        super(actionSetting);
    }

    @Override
    public boolean execute(List<Boolean> beforeExecuteResult) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        String commandMode=getActionSetting().getValue("mode");
        String command=getActionSetting().getValue("command");
        Player player=getSelf();
        if (player==null){
            return false;
        }
        if (PlaceholderAPIHook.isLoad()){
            command=PlaceholderAPIHook.getPlaceholder(player,command);
        }
        if (commandMode.equalsIgnoreCase("player")){
            String finalCommand = command;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getScheduler().runTask(OriginCore.getInstance(),() -> player.performCommand(finalCommand));
                }
            }.runTaskAsynchronously(OriginCore.getInstance());
        }else if (commandMode.equalsIgnoreCase("console")){
            String finalCommand = command;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getScheduler().runTask(OriginCore.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                }
            }.runTaskAsynchronously(OriginCore.getInstance());
        }
        return true;
    }
}
