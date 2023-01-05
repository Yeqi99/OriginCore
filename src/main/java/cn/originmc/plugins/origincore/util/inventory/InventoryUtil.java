package cn.originmc.plugins.origincore.util.inventory;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil {
    public int getAmountByMaterial(Inventory inventory, String material){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            if (content.getType()== Material.valueOf(material)){
                amount++;
            }
        }
        return amount;
    }
    public int getAmountByName(Inventory inventory, String name){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            if (content.hasItemMeta()){
                if (content.getItemMeta().hasDisplayName()){
                    if (content.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
                        amount++;
                    }
                }
            }
        }
        return amount;
    }
}
