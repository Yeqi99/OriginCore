package cn.originmc.plugins.origincore.util.action.object;


import cn.originmc.plugins.origincore.util.action.impl.CommandAction;
import cn.originmc.plugins.origincore.util.action.impl.DelayAction;
import cn.originmc.plugins.origincore.util.action.impl.EcoAction;
import cn.originmc.plugins.origincore.util.action.impl.MessageAction;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Actions {
    private List<AbstractAction> actions=new ArrayList<>();
    private Map<String,Object> objectMap=new HashMap<>();
    
    public Actions(List<String> strings){
        for (String string : strings) {
            actions.add(Actions.getAbsAction(string));
        }
    }
    public void execute(){
        List<Boolean> results=new ArrayList<>();
        for (AbstractAction action : actions) {
            action.setObjectMap(getObjectMap());
            results.add(action.execute(results));
        }
    }
    public void putObject(String key,Object object){
        getObjectMap().put(key,object);
    }
    public List<AbstractAction> getActions() {
        return actions;
    }

    public void setActions(List<AbstractAction> actions) {
        this.actions = actions;
    }
    public static AbstractAction getAbsAction(String s){
        FormatText formatText=new FormatText(s);
        String type=formatText.getValue("type");
        switch (type){
            case "Command":{
                return new CommandAction(formatText);
            }
            case "Delay":{
                return new DelayAction(formatText);
            }
            case "Eco":{
                return new EcoAction(formatText);
            }
            case "Message":{
                return new MessageAction(formatText);
            }
        }
        return null;
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

    public void setObjectMap(Map<String, Object> objectMap) {
        this.objectMap = objectMap;
    }
}
