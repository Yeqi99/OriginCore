package cn.originmc.plugins.origincore.util.action.action;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class CommandAction extends AbstractAction {


    public CommandAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult, Map<String, Object> objectMap) {
        super(plugin, actionSetting, beforeExecuteResult, objectMap);
    }

    public CommandAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult) {
        super(plugin, actionSetting, beforeExecuteResult);
    }

    @Override
    public boolean execute() {
        if (!canExecute()){
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
                    Bukkit.getScheduler().runTask(getPlugin(), () -> player.performCommand(finalCommand));
                }
            }.runTaskAsynchronously(getPlugin());
        }else if (commandMode.equalsIgnoreCase("console")){
            String finalCommand = command;
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getScheduler().runTask(getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                }
            }.runTaskAsynchronously(getPlugin());
        }
        return true;
    }
}
