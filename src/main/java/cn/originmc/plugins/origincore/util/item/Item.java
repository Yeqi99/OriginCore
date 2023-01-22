package cn.originmc.plugins.origincore.util.item;

import cn.originmc.plugins.origincore.util.text.FormatText;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Item {
    private ItemStack itemStack;
    public Item(ItemStack itemStack){
        setItemStack(itemStack);
        initMeta();
    }
    public Item(String itemString){
        fromString(itemString);
        initMeta();
    }
    public boolean set(String key,Object object){
        if (isNull()){
            return false;
        }
        if (isAir()){
            return false;
        }
        NBTItem nbtItem = new NBTItem(getItemStack());
        switch (DataType.getType(object)){
            case INT:{
                nbtItem.setInteger(key, (Integer) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case FLOAT:{
                nbtItem.setFloat(key, (Float) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case DOUBLE:{
                nbtItem.setDouble(key, (Double) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case STRING:{
                nbtItem.setString(key, (String) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case ITEMSTACK:{
                nbtItem.setItemStack(key,(ItemStack) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BOOLEAN:{
                nbtItem.setBoolean(key,(Boolean) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case LONG:{
                nbtItem.setLong(key, (Long) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case UUID:{
                nbtItem.setUUID(key, (UUID) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case SHORT:{
                nbtItem.setShort(key, (Short) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case INTARRAY:{
                nbtItem.setIntArray(key, (int[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case ITEMSTACKARRAY:{
                nbtItem.setItemStackArray(key, (ItemStack[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BYTE:{
                nbtItem.setByte(key, (Byte) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BYTEARRAY:{
                nbtItem.setByteArray(key, (byte[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case FORMATTEXT:{
                FormatText formatText= (FormatText) object;
                nbtItem.setString(key,formatText.getFormatString());
                setItemStack(nbtItem.getItem());
                return true;
            }
            default:{
                return false;
            }
        }
    }
    public Object get(String key,DataType dataType){
        if (isNull()){
            return null;
        }
        if (isAir()){
            return null;
        }
        NBTItem nbtItem = new NBTItem(getItemStack());
        switch (dataType){
            case INT:{
                return nbtItem.getInteger(key);
            }
            case FLOAT:{
                return nbtItem.getFloat(key);
            }
            case DOUBLE:{
                return nbtItem.getDouble(key);
            }
            case STRING:{
                return nbtItem.getString(key);
            }
            case ITEMSTACK:{
                return nbtItem.getItemStack(key);
            }
            case BOOLEAN:{
                return nbtItem.getBoolean(key);
            }
            case LONG:{
                return nbtItem.getLong(key);
            }
            case UUID:{
                return nbtItem.getUUID(key);
            }
            case SHORT:{
                return nbtItem.getShort(key);
            }
            case INTARRAY:{
                return nbtItem.getIntArray(key);
            }
            case ITEMSTACKARRAY:{
                return nbtItem.getItemStackArray(key);
            }
            case BYTE:{
                return nbtItem.getByte(key);
            }
            case BYTEARRAY:{
                return nbtItem.getByteArray(key);
            }
            case FORMATTEXT:{
                return new FormatText(nbtItem.getString(key));
            }
            default:{
                return null;
            }
        }
    }
    public boolean set(String key,Object object,String spaceName){
        if (isNull()){
            return false;
        }
        if (isAir()){
            return false;
        }
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound space=nbtItem.getCompound(spaceName);
        switch (DataType.getType(object)){
            case INT:{
                space.setInteger(key, (Integer) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case FLOAT:{
                space.setFloat(key, (Float) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case DOUBLE:{
                space.setDouble(key, (Double) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case STRING:{
                space.setString(key, (String) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case ITEMSTACK:{
                space.setItemStack(key,(ItemStack) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BOOLEAN:{
                space.setBoolean(key,(Boolean) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case LONG:{
                space.setLong(key, (Long) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case UUID:{
                space.setUUID(key, (UUID) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case SHORT:{
                space.setShort(key, (Short) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case INTARRAY:{
                space.setIntArray(key, (int[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case ITEMSTACKARRAY:{
                space.setItemStackArray(key, (ItemStack[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BYTE:{
                space.setByte(key, (Byte) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case BYTEARRAY:{
                space.setByteArray(key, (byte[]) object);
                setItemStack(nbtItem.getItem());
                return true;
            }
            case FORMATTEXT:{
                FormatText formatText= (FormatText) object;
                space.setString(key,formatText.getFormatString());
                setItemStack(nbtItem.getItem());
                return true;
            }
            default:{
                return false;
            }
        }
    }
    public Object get(String key,DataType dataType,String spaceName){
        if (isNull()){
            return null;
        }
        if (isAir()){
            return null;
        }
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound space=nbtItem.getCompound(spaceName);
        switch (dataType){
            case INT:{
                return space.getInteger(key);
            }
            case FLOAT:{
                return space.getFloat(key);
            }
            case DOUBLE:{
                return space.getDouble(key);
            }
            case STRING:{
                return space.getString(key);
            }
            case ITEMSTACK:{
                return space.getItemStack(key);
            }
            case BOOLEAN:{
                return space.getBoolean(key);
            }
            case LONG:{
                return space.getLong(key);
            }
            case UUID:{
                return space.getUUID(key);
            }
            case SHORT:{
                return space.getShort(key);
            }
            case INTARRAY:{
                return space.getIntArray(key);
            }
            case ITEMSTACKARRAY:{
                return space.getItemStackArray(key);
            }
            case BYTE:{
                return space.getByte(key);
            }
            case BYTEARRAY:{
                return space.getByteArray(key);
            }
            case FORMATTEXT:{
                return new FormatText(space.getString(key));
            }
            default:{
                return null;
            }
        }
    }
    public void addDouble(String key,double addValue){
        if (hasTag(key)){
            set(key,(double)get(key,DataType.DOUBLE)+addValue);
        }else {
            set(key,addValue);
        }
    }
    public void addDouble(String key,double addValue,String spaceName){
        if (!hasTag(spaceName)){
            addSpace(spaceName);
        }
        if (hasTag(key,spaceName)){
            set(key,(double)get(key,DataType.DOUBLE,spaceName)+addValue,spaceName);
        }else {
            set(key,addValue,spaceName);
        }
    }
    public void addInt(String key,int addValue){
        if (hasTag(key)){
            set(key,(int)get(key,DataType.INT)+addValue);
        }else {
            set(key,addValue);
        }
    }
    public void addInt(String key,int addValue,String spaceName){
        if (!hasTag(spaceName)){
            addSpace(spaceName);
        }
        if (hasTag(key)){
            set(key,(int)get(key,DataType.INT,spaceName)+addValue,spaceName);
        }else {
            set(key,addValue,spaceName);
        }
    }
    public void addFloat(String key,float addValue){
        if (hasTag(key)){
            set(key,(float)get(key,DataType.FLOAT)+addValue);
        }else {
            set(key,addValue);
        }
    }
    public void addFloat(String key,float addValue,String spaceName){
        if (!hasTag(spaceName)){
            addSpace(spaceName);
        }
        if (hasTag(key)){
            set(key,(float)get(key,DataType.FLOAT,spaceName)+addValue,spaceName);
        }else {
            set(key,addValue,spaceName);
        }
    }
    public void addSpace(String spaceName){
        NBTItem nbtItem = new NBTItem(getItemStack());
        nbtItem.addCompound( spaceName);
        setItemStack(nbtItem.getItem());
    }
    /**
     *     隐藏编号含义
     *    1 - 隐藏附魔
     * 　　2 - 隐藏自定义属性
     * 　　3 - 隐藏附魔和自定义属性
     * 　　4 - 隐藏{Unbreakable} (永久不毁)
     * 　　8 - 隐藏{CanDestroy} (可破坏)
     * 　　16 - 隐藏{CanPlaceOn} (可放置在)
     * 　　32 - 隐藏大部分信息(药水信息，书作者，烟花效果等等)
     * 　　63 - 隐藏所有的信息，除了名字和附加文字
     */
    public void setHideFlags(int flag){
        set("HideFlags",flag);
    }
    public void setUnbreakable(boolean flag){
        set("Unbreakable",flag);
    }
    public boolean isUnbreakable(){
        return (boolean) get("Unbreakable",DataType.BOOLEAN);
    }
    public boolean setCustomModelData(int value){
        return set("CustomModelData",value);
    }
    public int getCustomModelData(){
        return (int) get("CustomModelData",DataType.INT);
    }
    public void setEnchantment(String id,int lvl,boolean flag){
        ItemMeta itemMeta= getItemStack().getItemMeta();
        assert itemMeta != null;
        Enchantment enchantment=Enchantment.getByKey(NamespacedKey.fromString(id));
        if (enchantment != null) {
            itemMeta.addEnchant(enchantment,lvl,flag);
            getItemStack() .setItemMeta(itemMeta);
        }
    }
    public int getEnchantmentsLevel(String id){
        ItemMeta itemMeta= getItemStack().getItemMeta();
        assert itemMeta != null;
        Enchantment enchantment=Enchantment.getByKey(NamespacedKey.fromString(id));
        if (enchantment != null) {
            return itemMeta.getEnchantLevel(enchantment);
        }
        return -1;
    }
    public void mergeItemNBT(ItemStack item){
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound itemData= NBTItem.convertItemtoNBT(item);
        nbtItem.mergeCompound(itemData);
        setItemStack(nbtItem.getItem());
    }
    public boolean hasTag(String key){
        if (isNull()){
            return false;
        }
        if (isAir()){
            return false;
        }
        return new NBTItem(getItemStack()).hasTag(key);
    }
    public boolean hasTag(String key,String spaceName){
        if (isNull()){
            return false;
        }
        if (isAir()){
            return false;
        }
        if (!hasTag(spaceName)){
            return false;
        }
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound space=nbtItem.getCompound(spaceName);
        return space.hasTag(key);
    }
    public void removeTag(String key){
        NBTItem nbtItem = new NBTItem(getItemStack());
        nbtItem.removeKey(key);
        setItemStack(nbtItem.getItem());
    }
    public void removeTag(String key,String spaceName){
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound space=nbtItem.getCompound(spaceName);
        space.removeKey(key);
        setItemStack(nbtItem.getItem());
    }
    public List<String> getTags(){
        NBTItem nbtItem = new NBTItem(getItemStack());
        return new ArrayList<>(nbtItem.getKeys());
    }
    public List<String> getTags(String spaceName){
        NBTItem nbtItem = new NBTItem(getItemStack());
        NBTCompound space=nbtItem.getCompound(spaceName);
        return new ArrayList<>(space.getKeys());
    }

    public boolean isAir(){
        return getItemStack().getType().equals(Material.AIR);
    }
    public boolean isNull(){
        return getItemStack() == null;
    }
    public boolean hasLore(){
        if (getItemStack().hasItemMeta()){
            return Objects.requireNonNull(getItemStack().getItemMeta()).hasLore();
        }else {
            return false;
        }
    }
    public boolean hasDisplay(){
        if (getItemStack().hasItemMeta()){
            if (Objects.requireNonNull(getItemStack().getItemMeta()).hasDisplayName()){
                return true;
            }
        }
        return false;
    }
    public void initMeta(){
        if(!getItemStack().hasItemMeta()){
            getItemStack().setItemMeta(getItemStack().getItemMeta());
        }
    }
    public String getString(){
        ReadWriteNBT nbt = NBT.itemStackToNBT(getItemStack());
        return nbt.toString();
    }
    public void fromString(String itemString){
        ReadWriteNBT nbt = NBT.parseNBT(itemString);
        setItemStack(NBT.itemStackFromNBT(nbt));
    }
    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    public void setDisplay(String display){
        ItemMeta itemMeta=getItemStack().getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(display);
        getItemStack().setItemMeta(itemMeta);
    }
    public String getDisplay(){
        if (getItemStack().getItemMeta()==null){
            return getItemStack().getType().name();
        }else {
            if (getItemStack().getItemMeta().hasDisplayName()){
                return getItemStack().getItemMeta().getDisplayName();
            }else {
                return getItemStack().getType().name();
            }
        }
    }
    public void setLore(List<String> strings){
        ItemMeta itemMeta=getItemStack().getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(strings);
        getItemStack().setItemMeta(itemMeta);
    }
    public List<String> getLore(){
        if (hasLore()){
            return Objects.requireNonNull(getItemStack().getItemMeta()).getLore();
        }else {
            return null;
        }
    }
}
