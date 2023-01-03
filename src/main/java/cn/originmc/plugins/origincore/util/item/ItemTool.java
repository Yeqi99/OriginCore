package cn.originmc.plugins.origincore.util.item;

import org.bukkit.inventory.ItemStack;

public class ItemTool {
    /**
     * 判断物品是否为容器
     * @param itemStack minecraft中原版物品
     * @return 是否为容器
     */
    public boolean isContainer(ItemStack itemStack){
        return new Item(itemStack).hasTag("OriginCore_Container");
    }
}
