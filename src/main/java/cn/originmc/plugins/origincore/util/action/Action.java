package cn.originmc.plugins.origincore.util.action;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.PlayerPointsHook;
import cn.originmc.plugins.origincore.hook.VaultHook;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.text.Sender;
import cn.originmc.plugins.origincore.util.time.TimeControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Action {
    private ActionType type;
    private FormatText formatText;
    private JavaPlugin plugin;
    public Action(JavaPlugin plugin, FormatText formatText){
        setPlugin(plugin);
        setType(ActionType.valueOf(formatText.getValue("type")));
        setFormatText(formatText);
    }
    public boolean execute(Player player){
        switch (type){
            case SELF:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> player.performCommand(finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case CONSOLE:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case TELL:{
                new Sender(getPlugin()).sendToPlayer(player,formatText.getValue("text"));
                return true;
            }
            case TELLALL:{
                new Sender(getPlugin()).sendToAllPlayer(formatText.getValue("text"));
                return true;
            }
            case DELAY:{
                return TimeControl.delay(Long.parseLong(formatText.getValue("time")));
            }
            case MONEY:{
                if (VaultHook.isLoad()){
                    VaultHook.giveMoney(player, Double.parseDouble(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            case POINTS:{
                if (PlayerPointsHook.isLoad()){
                    PlayerPointsHook.givePoints(player.getUniqueId(), Integer.parseInt(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            default:{
                return false;
            }
        }
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public FormatText getFormatText() {
        return formatText;
    }

    public void setFormatText(FormatText formatText) {
        this.formatText = formatText;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }
}
