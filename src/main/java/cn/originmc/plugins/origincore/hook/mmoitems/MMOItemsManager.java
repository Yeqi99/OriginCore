package cn.originmc.plugins.origincore.hook.mmoitems;


import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.inventory.ItemStack;


public class MMOItemsManager {
    public static MMOItem getMMOItem(String type, String name){
        return MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(type), name);
    }
    public static ItemStack getItem(String type, String name){
        return getMMOItem(type,name).newBuilder().build();
    }

}
