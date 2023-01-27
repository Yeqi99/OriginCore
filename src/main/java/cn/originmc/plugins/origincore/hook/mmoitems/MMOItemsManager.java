package cn.originmc.plugins.origincore.hook.mmoitems;

import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.inventory.ItemStack;

public class MMOItemsManager {
    public static MMOItem getMMOItem(String type, String name){
        return MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(type), name);
    }
    public static ItemStack getMMOItemSkill(ItemStack itemStack, String type, String name){
        MMOItem mmoItem=getMMOItem(type,name);
        if (mmoItem==null){
            return itemStack;
        }
        ItemStack item= mmoItem.newBuilder().build();
        NBTItem nbtItem = new NBTItem(item);
        NBTItem toItem=new NBTItem(itemStack);
        NBTCompoundList space=nbtItem.getCompoundList("MMOITEMS_ABILITY");
        if (!toItem.hasTag("MMOITEMS_ABILITY")){
            toItem.addCompound("MMOITEMS_ABILITY");
        }
        for (ReadWriteNBT readWriteNBT : space) {
            toItem.getCompoundList("MMOITEMS_ABILITY").addCompound(readWriteNBT);
        }
        return toItem.getItem();
    }
}
