package cn.originmc.plugins.origincore.util.action.action;

import cn.originmc.plugins.origincore.hook.PlayerPointsHook;
import cn.originmc.plugins.origincore.hook.VaultHook;
import cn.originmc.plugins.origincore.util.action.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import io.lumine.mythic.core.skills.conditions.all.LineOfSightCondition;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class EcoAction extends AbstractAction {
    public EcoAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult, Map<String, Object> objectMap) {
        super(plugin, actionSetting, beforeExecuteResult, objectMap);
    }

    public EcoAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult) {
        super(plugin, actionSetting, beforeExecuteResult);
    }

    @Override
    public boolean execute() {
        if (!canExecute()){
            return false;
        }
        String ecoMode=getActionSetting().getValue("mode");
        String targetType=getActionSetting().getValue("target");
        Player target;
        if (targetType.equalsIgnoreCase("self")){
            target=getSelf();
        }else if (targetType.equalsIgnoreCase("target")){
            target=getTarget();
        }else {
            return false;
        }
        if (target==null){
            return false;
        }
        double value= Double.parseDouble(getActionSetting().getValue("value"));
        if (value==0){
            return true;
        }
        if (ecoMode.equalsIgnoreCase("vault")){
            if (!VaultHook.isLoad()){
                return false;
            }
            VaultHook.giveMoney(target,value);
        }else if (ecoMode.equalsIgnoreCase("points")){
            if (!PlayerPointsHook.isLoad()){
                return false;
            }
            PlayerPointsHook.givePoints(target.getUniqueId(), (int) value);
        }
        return false;
    }
}
