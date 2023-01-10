package cn.originmc.plugins.origincore.util.inventory;

import cn.originmc.plugins.origincore.util.item.DataType;
import cn.originmc.plugins.origincore.util.item.Item;
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
    public int getAmountByNBT(Inventory inventory,String key){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            if (item.hasTag(key)){
                amount+=content.getAmount();
            }
        }
        return amount;
    }
    public int getAmountByNBT(Inventory inventory,String spaceName,String key){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            if (item.hasTag(key,spaceName)){
                amount+=content.getAmount();
            }
        }
        return amount;
    }
    public int getAmountByNBT(Inventory inventory, String key, Object value, DataType dataType){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            Object object= item.get(key,dataType);
            if (object.equals(value)){
                amount+= content.getAmount();
            }
        }
        return amount;
    }
    public int getAmountByNBT(Inventory inventory, String spaceName,String key, Object value, DataType dataType){
        int amount=0;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            Object object= item.get(key,dataType,spaceName);
            if (object.equals(value)){
                amount+= content.getAmount();
            }
        }
        return amount;
    }
    public void removeAmountByMaterial(Inventory inventory, String material,int amount){
        int nowAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            if (content.getType()==Material.valueOf(material)){
                if (content.getAmount()<nowAmount){
                   nowAmount-=content.getAmount();
                   content.setAmount(0);
                }else {
                    content.setAmount(content.getAmount()-amount);
                    return;
                }
            }
        }
    }
    public void removeAmountByName(Inventory inventory, String name,int amount){
        int newAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            if (content.hasItemMeta()){
                if (content.getItemMeta().hasDisplayName()){
                    if (content.getItemMeta().getDisplayName().equalsIgnoreCase(name)){
                        if (content.getAmount()<newAmount){
                            newAmount-=content.getAmount();
                            content.setAmount(0);
                        }else {
                            content.setAmount(content.getAmount()-amount);
                            return;
                        }
                    }
                }
            }
        }
    }
    public void removeAmountByNBT(Inventory inventory, String key,int amount){
        int newAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            if (item.hasTag(key)){
                if (content.getAmount()<newAmount){
                    newAmount-=content.getAmount();
                    content.setAmount(0);
                }else {
                    content.setAmount(content.getAmount()-amount);
                    return;
                }
            }
        }
    }
    public void removeAmountByNBT(Inventory inventory, String spaceName,String key,int amount){
        int newAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            if (item.hasTag(spaceName,spaceName)){
                if (content.getAmount()<newAmount){
                    newAmount-=content.getAmount();
                    content.setAmount(0);
                }else {
                    content.setAmount(content.getAmount()-amount);
                    return;
                }
            }
        }
    }
    public void removeAmountByNBT(Inventory inventory, String key, Object value, DataType dataType,int amount){
        int newAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            Object object= item.get(key,dataType);
            if (object.equals(value)){
                if (content.getAmount()<newAmount){
                    newAmount-=content.getAmount();
                    content.setAmount(0);
                }else {
                    content.setAmount(content.getAmount()-amount);
                    return;
                }
            }
        }
    }
    public void removeAmountByNBT(Inventory inventory, String spaceName,String key, Object value, DataType dataType,int amount){
        int newAmount=amount;
        for (ItemStack content : inventory.getContents()) {
            Item item=new Item(content);
            Object object= item.get(key,dataType,spaceName);
            if (object.equals(value)){
                if (content.getAmount()<newAmount){
                    newAmount-=content.getAmount();
                    content.setAmount(0);
                }else {
                    content.setAmount(content.getAmount()-amount);
                    return;
                }
            }
        }
    }
}
