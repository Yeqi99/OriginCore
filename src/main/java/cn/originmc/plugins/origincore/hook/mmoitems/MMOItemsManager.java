package cn.originmc.plugins.origincore.hook.mmoitems;


import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTCompoundList;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MMOItemsManager {
    public static MMOItem getMMOItem(String type, String name){
        return MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(type), name);
    }
    public static ItemStack getItem(String type, String name){
        return getMMOItem(type,name).newBuilder().build();
    }
    public static ItemStack addMMOItemSkill(ItemStack itemStack, String skillName, String castMode, Map<String,Integer> setting){
        NBTItem nbtItem=new NBTItem(itemStack);
        if (!nbtItem.hasTag("MMOITEMS_ABILITY")){
            nbtItem.addCompound("MMOITEMS_ABILITY");
        }
        NBTCompoundList space=nbtItem.getCompoundList("MMOITEMS_ABILITY");
        NBTListCompound mod=space.addCompound();
        mod.setString("Id",skillName);
        mod.setString("CastMode",castMode);
        NBTCompound skillSetting=mod.addCompound("Modifiers");
        for (Map.Entry<String, Integer> entry : setting.entrySet()) {
            skillSetting.setInteger(entry.getKey(),entry.getValue());
        }
        return nbtItem.getItem();
    }
    public static Map<String,Integer> getMMOItemsSkillSetting(ItemStack itemStack, String skillName){
        NBTItem nbtItem=new NBTItem(itemStack);
        NBTCompoundList space=nbtItem.getCompoundList("MMOITEMS_ABILITY");
        Map<String,Integer> setting=new HashMap<>();
        for (int i=0;i<space.size();i++){
            NBTListCompound mod=space.get(i);
            String id=mod.getString("Id");
            if (id.equalsIgnoreCase(skillName)){
                NBTCompound nbtCompound= mod.getCompound("Modifiers");
                for (String key : nbtCompound.getKeys()) {
                    setting.put(key,nbtCompound.getInteger(key));
                }
                return setting;
            }
        }
        return null;
    }
    public static String getMMOItemsSkillCastMode(ItemStack itemStack, String skillName){
        NBTItem nbtItem=new NBTItem(itemStack);
        NBTCompoundList space=nbtItem.getCompoundList("MMOITEMS_ABILITY");
        for (int i=0;i<space.size();i++){
            NBTListCompound mod=space.get(i);
            String id=mod.getString("Id");
            if (id.equalsIgnoreCase(skillName)){
                return mod.getString("CastMode");
            }
        }
        return null;
    }
}
