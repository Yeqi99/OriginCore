package cn.originmc.plugins.origincore.util.text.interactivekey.objcet;

import cn.originmc.plugins.origincore.util.item.Item;
import cn.originmc.plugins.origincore.util.text.Color;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class HoverAction {
    private boolean enable;
    private HoverEvent.Action action;
    private Object actionValue;
    public HoverAction(HoverEvent.Action action,Object actionValue){
        setAction(action);
        setActionValue(actionValue);
        setEnable(true);
    }
    public HoverEvent getEvent(){
        switch (action){
            case SHOW_TEXT:{
                Text text=new Text( Color.toColor((String) actionValue));
                return new HoverEvent(action, text);
            }
            case SHOW_ITEM:{
                Item item=new Item((ItemStack) actionValue);
                return new HoverEvent(action, new net.md_5.bungee.api.chat.hover.content.Item(UUID.randomUUID().toString(),item.getItemStack().getAmount()
                ,ItemTag.ofNbt(item.getString())));
            }
            case SHOW_ENTITY:{
                org.bukkit.entity.Entity e= (org.bukkit.entity.Entity) actionValue;
                BaseComponent[] bc = TextComponent.fromLegacyText(Color.toColor(e.getCustomName()));
                Entity entity=new Entity(e.getType().toString(),e.getUniqueId().toString(),bc[0]);
                return new HoverEvent(action,entity);
            }
            default:{
                return null;
            }
        }
    }
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public HoverEvent.Action getAction() {
        return action;
    }

    public void setAction(HoverEvent.Action action) {
        this.action = action;
    }

    public Object getActionValue() {
        return actionValue;
    }

    public void setActionValue(Object actionValue) {
        this.actionValue = actionValue;
    }
}
