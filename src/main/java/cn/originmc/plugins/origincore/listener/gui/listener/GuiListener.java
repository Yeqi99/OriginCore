package cn.originmc.plugins.origincore.listener.gui.listener;

import cn.originmc.plugins.origincore.listener.gui.event.GuiClickEvent;
import cn.originmc.plugins.origincore.listener.gui.object.Gui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class GuiListener implements Listener {
    private static List<Gui> guis=new ArrayList<>();
    @EventHandler
    public static void whenClickGui(InventoryClickEvent e){
        Player player= (Player) e.getWhoClicked();
        if (opened(player)){
            Gui gui=getGui(player);
            GuiClickEvent event=new GuiClickEvent(gui);
            event.setClickIndex(e.getRawSlot());
            assert gui != null;
            event.setClickItem(gui.getItem(e.getRawSlot()));
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (gui.isLimitSlot(e.getRawSlot())){
                e.setCancelled(true);
            }
        }
    }
    public static boolean opened(Player player){
        for (Gui gui : guis) {
            if (gui.getUser().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())){
                return true;
            }
        }
        return false;
    }
    public static Gui getGui(Player player){
        for (Gui gui : guis) {
            if (gui.getUser().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())){
                return gui;
            }
        }
        return null;
    }
    public static void register(Gui gui){
        for (int i=0;i<guis.size();i++){
            if (gui.getUUID()==guis.get(i).getUUID()){
                guis.set(i,gui);
                return;
            }
        }
        guis.add(gui);
    }
    public static void removeGui(Player player){

    }

    public static List<Gui> getGuis() {
        return guis;
    }

    public static void setGuis(List<Gui> guis) {
        GuiListener.guis = guis;
    }
}
