package cn.originmc.plugins.origincore.listener.gui.object;

import cn.originmc.plugins.origincore.listener.gui.listener.GuiListener;
import cn.originmc.plugins.origincore.util.item.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Gui {
    private String id;
    private UUID uuid;
    private Inventory inventory;
    private Player user;
    private List<Integer> limitSlots=new ArrayList<>();
    public Gui(String id,Player player){
        setId(id);
        setUser(player);
        setUUID(UUID.randomUUID());
    }

    public void open(){
        GuiListener.register(this);
        getUser().openInventory(getInventory());
    }

    public void close(){
        GuiListener.removeGui(user);
        getUser().closeInventory();
    }
    public Item getItem(int index){
        ItemStack itemStack=inventory.getItem(index);
        if (itemStack==null){
            return null;
        }
        return new Item(itemStack);
    }
    public boolean isLimitSlot(int index){
        return limitSlots.contains(index);
    }

    public boolean isUser(Player player){
        return getUser().getUniqueId()==player.getUniqueId();
    }

    public void addLimitSlot(int index){
        if (!limitSlots.contains(index)){
            limitSlots.add(index);
        }
    }
    public void addLimitSlots(int[] index){
        for (int i : index) {
            addLimitSlot(i);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public List<Integer> getLimitSlots() {
        return limitSlots;
    }

    public void setLimitSlots(List<Integer> limitSlots) {
        this.limitSlots = limitSlots;
    }
}
