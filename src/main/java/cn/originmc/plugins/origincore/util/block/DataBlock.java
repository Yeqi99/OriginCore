package cn.originmc.plugins.origincore.util.block;

import cn.originmc.plugins.origincore.util.text.FormatText;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBlock {
    private Block block;
    public DataBlock(Block block){
        setBlock(block);
    }
    public DataBlock(Location location){
        setBlock(location.getBlock());
    }
    public boolean set(String key,Object object){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        switch (DataType.getType(object)){
            case INT:{
                nbtBlock.getData().setInteger(key, (Integer) object);
                return true;
            }
            case FLOAT:{
                nbtBlock.getData().setFloat(key, (Float) object);
                return true;
            }
            case DOUBLE:{
                nbtBlock.getData().setDouble(key, (Double) object);
                return true;
            }
            case STRING:{
                nbtBlock.getData().setString(key, (String) object);
                return true;
            }
            case ITEMSTACK:{
                nbtBlock.getData().setItemStack(key, (ItemStack) object);
                return true;
            }
            case BOOLEAN:{
                nbtBlock.getData().setBoolean(key, (Boolean) object);
                return true;
            }
            case LONG:{
                nbtBlock.getData().setLong(key, (Long) object);
                return true;
            }
            case UUID:{
                nbtBlock.getData().setUUID(key, (UUID) object);
                return true;
            }
            case SHORT:{
                nbtBlock.getData().setShort(key, (Short) object);
                return true;
            }
            case INTARRAY:{
                nbtBlock.getData().setIntArray(key, (int[]) object);
                return true;
            }
            case ITEMSTACKARRAY:{
                nbtBlock.getData().setItemStackArray(key, (ItemStack[]) object);
                return true;
            }
            case BYTE:{
                nbtBlock.getData().setByte(key, (Byte) object);
                return true;
            }
            case BYTEARRAY:{
                nbtBlock.getData().setByteArray(key, (byte[]) object);
                return true;
            }
            case FORMATTEXT:{
                FormatText formatText= (FormatText) object;
                nbtBlock.getData().setString(key,formatText.getFormatString());
                return true;
            }
            case KNOWN:
            default:{
                return false;
            }
        }
    }
    public boolean set(String key,Object object,String spaceName){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        NBTCompound space=nbtBlock.getData().getCompound(spaceName);
        switch (DataType.getType(object)){
            case INT:{
                space.setInteger(key, (Integer) object);
                return true;
            }
            case FLOAT:{
                space.setFloat(key, (Float) object);
                return true;
            }
            case DOUBLE:{
                space.setDouble(key, (Double) object);
                return true;
            }
            case STRING:{
                space.setString(key, (String) object);
                return true;
            }
            case ITEMSTACK:{
                space.setItemStack(key, (ItemStack) object);
                return true;
            }
            case BOOLEAN:{
                space.setBoolean(key, (Boolean) object);
                return true;
            }
            case LONG:{
                space.setLong(key, (Long) object);
                return true;
            }
            case UUID:{
                space.setUUID(key, (UUID) object);
                return true;
            }
            case SHORT:{
                space.setShort(key, (Short) object);
                return true;
            }
            case INTARRAY:{
                space.setIntArray(key, (int[]) object);
                return true;
            }
            case ITEMSTACKARRAY:{
                space.setItemStackArray(key, (ItemStack[]) object);
                return true;
            }
            case BYTE:{
                space.setByte(key, (Byte) object);
                return true;
            }
            case BYTEARRAY:{
                space.setByteArray(key, (byte[]) object);
                return true;
            }
            case FORMATTEXT:{
                FormatText formatText= (FormatText) object;
                space.setString(key,formatText.getFormatString());
                return true;
            }
            case KNOWN:
            default:{
                return false;
            }
        }
    }
    public Object get(String key, cn.originmc.plugins.origincore.util.item.DataType dataType){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        switch (dataType){
            case INT:{
                return nbtBlock.getData().getInteger(key);
            }
            case FLOAT:{
                return nbtBlock.getData().getFloat(key);
            }
            case DOUBLE:{
                return nbtBlock.getData().getDouble(key);
            }
            case STRING:{
                return nbtBlock.getData().getString(key);
            }
            case ITEMSTACK:{
                return nbtBlock.getData().getItemStack(key);
            }
            case BOOLEAN:{
                return nbtBlock.getData().getBoolean(key);
            }
            case LONG:{
                return nbtBlock.getData().getLong(key);
            }
            case UUID:{
                return nbtBlock.getData().getUUID(key);
            }
            case SHORT:{
                return nbtBlock.getData().getShort(key);
            }
            case INTARRAY:{
                return nbtBlock.getData().getIntArray(key);
            }
            case ITEMSTACKARRAY:{
                return nbtBlock.getData().getItemStackArray(key);
            }
            case BYTE:{
                return nbtBlock.getData().getByte(key);
            }
            case BYTEARRAY:{
                return nbtBlock.getData().getByteArray(key);
            }
            case FORMATTEXT:{
                return new FormatText(nbtBlock.getData().getString(key));
            }
            default:{
                return null;
            }
        }
    }
    public Object get(String key, cn.originmc.plugins.origincore.util.item.DataType dataType,String spaceName){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        NBTCompound space=nbtBlock.getData().getCompound(spaceName);
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
    public void addSpace(String spaceName){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        nbtBlock.getData().addCompound( spaceName);
    }
    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
    public boolean hasTag(String key){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        return nbtBlock.getData().hasTag(key);
    }
    public boolean hasTag(String key,String spaceName){
        if (!hasTag(spaceName)){
            return false;
        }
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        NBTCompound space=nbtBlock.getData().getCompound(spaceName);
        return space.hasTag(key);
    }
    public void removeTag(String key){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        nbtBlock.getData().removeKey(key);
    }
    public void removeTag(String key,String spaceName){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        NBTCompound space=nbtBlock.getData().getCompound(spaceName);
        space.removeKey(key);
    }
    public List<String> getTags(){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        return new ArrayList<>(nbtBlock.getData().getKeys());
    }
    public List<String> getTags(String spaceName){
        NBTBlock nbtBlock = new NBTBlock(getBlock());
        NBTCompound space=nbtBlock.getData().getCompound(spaceName);
        return new ArrayList<>(space.getKeys());
    }
}
