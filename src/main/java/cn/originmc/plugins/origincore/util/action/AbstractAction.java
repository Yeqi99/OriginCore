package cn.originmc.plugins.origincore.util.action;

import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public abstract class AbstractAction {
    private FormatText actionSetting;
    private JavaPlugin plugin;
    private Map<ObjectType,Object> objectMap;
    private List<Boolean> beforeExecuteResult;

    public AbstractAction(JavaPlugin plugin,FormatText actionSetting,List<Boolean> beforeExecuteResult,Map<ObjectType,Object> objectMap){
        setPlugin(plugin);
        setActionSetting(actionSetting);
        setBeforeExecuteResult(beforeExecuteResult);
        setObjectMap(objectMap);
    }
    public boolean getPreconditions(List<Boolean> beforeExecuteResult){
        if (actionSetting.hasKey("preconditions")){
            int preconditions= Integer.parseInt(actionSetting.getValue("preconditions"));
            if (preconditions>=beforeExecuteResult.size()){
                return false;
            }
            return beforeExecuteResult.get(preconditions);
        }else if (actionSetting.hasKey("!preconditions")){
            int preconditions= Integer.parseInt(actionSetting.getValue("!preconditions"));
            if (preconditions>=beforeExecuteResult.size()){
                return true;
            }
            return !beforeExecuteResult.get(preconditions);
        }
        return true;
    }
    public Player getSelf(){
        return (Player) getObjectMap().get(ObjectType.SELF);
    }
    public abstract boolean execute();

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public FormatText getActionSetting() {
        return actionSetting;
    }

    public void setActionSetting(FormatText actionSetting) {
        this.actionSetting = actionSetting;
    }



    public List<Boolean> getBeforeExecuteResult() {
        return beforeExecuteResult;
    }

    public void setBeforeExecuteResult(List<Boolean> beforeExecuteResult) {
        this.beforeExecuteResult = beforeExecuteResult;
    }

    public Map<ObjectType, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<ObjectType, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
