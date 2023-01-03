package cn.originmc.plugins.origincore.util.text.interactivekey.objcet;


import net.md_5.bungee.api.chat.ClickEvent;

public class ClickAction {
    private boolean enable;
    private ClickEvent.Action action;
    private String actionValue;
    public ClickAction(ClickEvent.Action action,String actionValue){
        setAction(action);
        setActionValue(actionValue);
        setEnable(true);
    }
    public ClickEvent getEvent(){
        return new ClickEvent(getAction(),getActionValue());
    }
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public ClickEvent.Action getAction() {
        return action;
    }

    public void setAction(ClickEvent.Action action) {
        this.action = action;
    }


    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }
}
