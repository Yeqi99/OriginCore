package cn.originmc.plugins.origincore.util.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Container extends Item{
    public Container(ItemStack itemStack) {
        super(itemStack);
    }
    public Container(String itemString) {
        super(itemString);
    }

    /**
     * 初始化容器
     * @param size 容器行数
     * @return 初始化结果
     */
    public boolean init(int size,String title){
        if (isNull()){
            return false;
        }
        if (isAir()){
            return false;
        }
        addSpace("OriginCore_Container");
        set("size",size,"OriginCore_Container");
        set("title",title,"OriginCore_Container");
        return true;
    }
    public boolean isInit(){
        if (hasTag("OriginCore_Container")){
            return hasTag("size", "OriginCore_Container");
        }
        return false;
    }
    public boolean put(int index,ItemStack itemStack){
        ItemStack item=view(index);
        if (item==null){
            return false;
        }
        if (item.getType()!=Material.AIR){
            return false;
        }
        set(index+"",itemStack,"OriginCore_Container");
        return true;
    }
    public boolean take(int index){
        ItemStack item=view(index);
        if (item==null){
            return false;
        }
        if (item.getType()==Material.AIR){
            return false;
        }
        removeTag(index+"","OriginCore_Container");
        return true;
    }
    public ItemStack view(int index){
        if (!hasTag("OriginCore_Container")){
            return null;
        }
        if (!hasTag(index+"","OriginCore_Container")){
            return new ItemStack(Material.AIR);
        }
        return (ItemStack) get(index+"",DataType.ITEMSTACK,"OriginCore_Container");
    }

    /**
     * 获取容器最大容量
     * @return 最大容量
     */
    public int getMaxCapacity(){
        if (!isInit()){
            return -1;
        }
        return (int) get("size",DataType.INT,"OriginCore_Container");
    }

    /**
     * 获取空余容量
     * @return 空余容量
     */
    public int getFreeCapacity(){
        if (!isInit()){
            return -1;
        }
        int amount=0;
        int max=getMaxCapacity();
        for (int i=0;i<max;i++){
            if (view(i).getType()==Material.AIR){
                amount++;
            }
        }
        return amount;
    }

    /**
     * 获取存放的物品数量
     * @return 容器中物品数量
     */
    public int getItemAmount(){
        if (!isInit()){
            return -1;
        }
        int amount=0;
        int max=getMaxCapacity();
        for (int i=0;i<max;i++){
            if (view(i).getType()!=Material.AIR){
                amount++;
            }
        }
        return amount;
    }

    /**
     * 获取第一个空闲位置序号
     * @return 第一个空余位置序号 -2为容器已满
     */
    public int getFirstFreeIndex(){
        if (!isInit()){
            return -1;
        }
        int max=getMaxCapacity();
        for (int i=0;i<max;i++){
            if (view(i).getType()==Material.AIR){
                return i;
            }
        }
        return -2;
    }

    /**
     * 在第一个空闲位置放入物品
     * @param itemStack 放入物品
     * @return 结果
     */
    public boolean add(ItemStack itemStack){
        if (!isInit()){
            return false;
        }
        if (getFreeCapacity()<=0){
            return false;
        }
        return put(getFirstFreeIndex(),itemStack);
    }

    /**
     * 清空容器 需要再次初始化
     * @return 结果
     */
    public boolean clear(){
        if (!isInit()){
            return false;
        }
        removeTag("OriginCore_Container");
        return true;
    }
}
