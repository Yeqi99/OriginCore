package cn.originmc.plugins.origincore.util.entity;

import cn.originmc.plugins.origincore.util.text.FormatText;
import de.tr7zw.nbtapi.NBTBlock;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DataEntity {
    private Entity entity;
    public DataEntity(Entity entity){
        setEntity(entity);
    }
    public boolean set(String key,Object object){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        switch (DataType.getType(object)){
            case INT:{
                nbtEntity.setInteger(key, (Integer) object);
                return true;
            }
            case FLOAT:{
                nbtEntity.setFloat(key, (Float) object);
                return true;
            }
            case DOUBLE:{
                nbtEntity.setDouble(key, (Double) object);
                return true;
            }
            case STRING:{
                nbtEntity.setString(key, (String) object);
                return true;
            }
            case ITEMSTACK:{
                nbtEntity.setItemStack(key, (ItemStack) object);
                return true;
            }
            case BOOLEAN:{
                nbtEntity.setBoolean(key, (Boolean) object);
                return true;
            }
            case LONG:{
                nbtEntity.setLong(key, (Long) object);
                return true;
            }
            case UUID:{
                nbtEntity.setUUID(key, (UUID) object);
                return true;
            }
            case SHORT:{
                nbtEntity.setShort(key, (Short) object);
                return true;
            }
            case INTARRAY:{
                nbtEntity.setIntArray(key, (int[]) object);
                return true;
            }
            case ITEMSTACKARRAY:{
                nbtEntity.setItemStackArray(key, (ItemStack[]) object);
                return true;
            }
            case BYTE:{
                nbtEntity.setByte(key, (Byte) object);
                return true;
            }
            case BYTEARRAY:{
                nbtEntity.setByteArray(key, (byte[]) object);
                return true;
            }
            case FORMATTEXT:{
                FormatText formatText= (FormatText) object;
                nbtEntity.setString(key,formatText.getFormatString());
                return true;
            }
            case KNOWN:
            default:{
                return false;
            }
        }
    }
    public boolean set(String key,Object object,String spaceName){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        NBTCompound space=nbtEntity.getCompound(spaceName);
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
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        switch (dataType){
            case INT:{
                return nbtEntity.getInteger(key);
            }
            case FLOAT:{
                return nbtEntity.getFloat(key);
            }
            case DOUBLE:{
                return nbtEntity.getDouble(key);
            }
            case STRING:{
                return nbtEntity.getString(key);
            }
            case ITEMSTACK:{
                return nbtEntity.getItemStack(key);
            }
            case BOOLEAN:{
                return nbtEntity.getBoolean(key);
            }
            case LONG:{
                return nbtEntity.getLong(key);
            }
            case UUID:{
                return nbtEntity.getUUID(key);
            }
            case SHORT:{
                return nbtEntity.getShort(key);
            }
            case INTARRAY:{
                return nbtEntity.getIntArray(key);
            }
            case ITEMSTACKARRAY:{
                return nbtEntity.getItemStackArray(key);
            }
            case BYTE:{
                return nbtEntity.getByte(key);
            }
            case BYTEARRAY:{
                return nbtEntity.getByteArray(key);
            }
            case FORMATTEXT:{
                return new FormatText(nbtEntity.getString(key));
            }
            default:{
                return null;
            }
        }
    }
    public Object get(String key, cn.originmc.plugins.origincore.util.item.DataType dataType,String spaceName){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        NBTCompound space=nbtEntity.getCompound(spaceName);
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
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        nbtEntity.addCompound( spaceName);
    }
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public boolean hasTag(String key){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        return nbtEntity.hasTag(key);
    }
    public boolean hasTag(String key,String spaceName){
        if (!hasTag(spaceName)){
            return false;
        }
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        NBTCompound space=nbtEntity.getCompound(spaceName);
        return space.hasTag(key);
    }
    public void removeTag(String key){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        nbtEntity.removeKey(key);
    }
    public void removeTag(String key,String spaceName){
        NBTEntity nbtEntity=new NBTEntity(getEntity());
        NBTCompound space=nbtEntity.getCompound(spaceName);
        space.removeKey(key);
    }
}
