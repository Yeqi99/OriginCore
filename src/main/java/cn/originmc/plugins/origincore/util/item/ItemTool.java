package cn.originmc.plugins.origincore.util.item;

import org.bukkit.inventory.ItemStack;

public class ItemTool {
    public boolean isContainer(ItemStack itemStack){
        return new Item(itemStack).hasTag("OriginCore_Container");
    }
}
