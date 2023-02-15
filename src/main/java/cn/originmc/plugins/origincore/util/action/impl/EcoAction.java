package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.PlayerPointsHook;
import cn.originmc.plugins.origincore.hook.VaultHook;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.command.CommandUtil;
import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.entity.Player;

import javax.print.DocFlavor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EcoAction extends AbstractAction {


    public EcoAction(FormatText actionSetting, Map<String, Object> objectMap) {
        super(actionSetting, objectMap);
    }

    public EcoAction(FormatText actionSetting) {
        super(actionSetting);
    }

    @Override
    public boolean execute(List<Boolean> beforeExecuteResult) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        String ecoMode=getActionSetting().getValue("mode");
        Object object =getTargetObject();
        if (object==null){
            return false;
        }
        if (getTarget().equalsIgnoreCase("all")){
            Collection<Player> onlinePlayers= (Collection) object;
            switch (ecoMode){
                case "take":{
                    for (Player onlinePlayer : onlinePlayers) {
                        take(onlinePlayer);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean take(Player player){
        switch (getCurrency()){
            case "vault":{
                if (!VaultHook.isLoad()){
                    return false;
                }
                if (VaultHook.hasMoney(player,getValue())){
                    VaultHook.takeMoney(player,getValue());
                    return true;
                }else {
                    return false;
                }
            }
            case "points":{
                if (!PlayerPointsHook.isLoad()){
                    return false;
                }
                if (PlayerPointsHook.hasPoints(player.getUniqueId(), (int) getValue())){
                    PlayerPointsHook.takePoints(player.getUniqueId(), (int) getValue());
                    return true;
                }else {
                    return false;
                }
            }
            case "custom":{
                if (!PlaceholderAPIHook.isLoad()){
                    return false;
                }
                double playerValue= Double.parseDouble(PlaceholderAPIHook.getPlaceholder(player,getActionSetting().getValue("currency-var")));
                if (playerValue>=getValue()){
                    String command=getActionSetting().getValue("take");
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                    command.replace("~",getValue()+"");
                    CommandUtil.playConsoleCommand(command);
                }else {
                    return false;
                }
            }
            default:{
                return false;
            }
        }
    }
    public String getCurrency(){
        return getActionSetting().getValue("currency");
    }
    public double getValue(){
        return Double.parseDouble(getActionSetting().getValue("value"));
    }
}
