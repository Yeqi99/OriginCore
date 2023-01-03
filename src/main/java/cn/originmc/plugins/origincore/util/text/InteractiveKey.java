package cn.originmc.plugins.origincore.util.text;

import cn.originmc.plugins.origincore.util.text.interactivekey.objcet.ClickAction;
import cn.originmc.plugins.origincore.util.text.interactivekey.objcet.HoverAction;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class InteractiveKey {
    private String display;
    private ClickAction clickAction;
    private HoverAction hoverAction;
    public InteractiveKey(String display){
        setDisplay(display);
    }
    public BaseComponent[] get(){
        BaseComponent[] bc = TextComponent.fromLegacyText(Color.toColor(display));
        if (clickAction!=null){
            if (clickAction.isEnable()){
                ClickEvent ce=clickAction.getEvent();
                if (ce!=null){
                    bc[0].setClickEvent(ce);
                }
            }
        }
        if (hoverAction!=null){
            if (hoverAction.isEnable()){
                HoverEvent ha=hoverAction.getEvent();
                if (ha!=null){
                    bc[0].setHoverEvent(ha);
                }
            }
        }
        return bc;
    }
    public ClickAction getClickAction() {
        return clickAction;
    }

    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }


    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public HoverAction getHoverAction() {
        return hoverAction;
    }

    public void setHoverAction(HoverAction hoverAction) {
        this.hoverAction = hoverAction;
    }
}
