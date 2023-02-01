package cn.originmc.plugins.origincore.util.action.object.abs;

import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractAction {
    private FormatText actionSetting;
    private Map<String,Object> objectMap=new HashMap<>();


    public AbstractAction(FormatText actionSetting,Map<String,Object> objectMap){
        setActionSetting(actionSetting);
        setObjectMap(objectMap);
    }
    public AbstractAction(FormatText actionSetting){
        setActionSetting(actionSetting);
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
    public boolean canExecute(List<Boolean> beforeExecuteResult){
        return getPreconditions(beforeExecuteResult);
    }

    public Object getObject(String key){
        return getObjectMap().get(key);
    }
    public Player getSelf(){
        return (Player) getObjectMap().get("self");
    }
    public Player getTarget(){
        return (Player) getObjectMap().get("target");
    }
    public String getType(){
        return getActionSetting().getValue("type");
    }
    public void putObject(String key,Object object){
        getObjectMap().put(key,object);
    }
    public boolean hasObject(String key){
        return getObjectMap().containsKey(key);
    }
    public abstract boolean execute(List<Boolean> beforeExecuteResult);


    public FormatText getActionSetting() {
        return actionSetting;
    }

    public void setActionSetting(FormatText actionSetting) {
        this.actionSetting = actionSetting;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
