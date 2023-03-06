package cn.originmc.plugins.origincore.listener.gui.event;

import cn.originmc.plugins.origincore.listener.gui.object.Gui;
import cn.originmc.plugins.origincore.util.item.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GuiClickEvent extends Event implements Cancellable{
    private static final HandlerList handlers=new HandlerList();
    private Gui clickGui;
    private boolean cancelled;
    private int clickIndex;
    private Item clickItem;
    public GuiClickEvent(Gui gui){
        setClickGui(gui);
    }

    public void setClickGui(Gui clickGui) {
        this.clickGui = clickGui;
    }
    public Gui getClickGui() {
        return clickGui;
    }
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled=cancel;
    }

    public int getClickIndex() {
        return clickIndex;
    }

    public void setClickIndex(int clickIndex) {
        this.clickIndex = clickIndex;
    }

    public Item getClickItem() {
        return clickItem;
    }

    public void setClickItem(Item clickItem) {
        this.clickItem = clickItem;
    }
}
