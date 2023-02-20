package cn.originmc.plugins.origincore.util.item;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.data.yaml.YamlManager;
import org.bukkit.inventory.ItemStack;

public class ItemTool {
    /**
     * 判断物品是否为容器
     * @param itemStack minecraft中原版物品
     * @return 是否为容器
     */
    public static boolean isContainer(ItemStack itemStack){
        return new Item(itemStack).hasTag("OriginCore_Container");
    }

    public static boolean saveItem(String id,ItemStack itemStack){
        YamlManager yamlManager=new YamlManager(OriginCore.getInstance(),"item",true);
        if(yamlManager.hasElement(id)){
            return false;
        }
        Item item=new Item(itemStack);
        yamlManager.set(id,"item",item.getString());
        return true;
    }
    public static ItemStack getItem(String id){
        YamlManager yamlManager=new YamlManager(OriginCore.getInstance(),"item",true);
        if(yamlManager.hasElement(id)){
            return null;
        }
        Item item=new Item((String) yamlManager.get(id,"item"));
        return item.getItemStack();
    }

}
