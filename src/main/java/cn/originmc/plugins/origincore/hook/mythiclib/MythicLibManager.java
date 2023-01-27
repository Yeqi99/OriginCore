package cn.originmc.plugins.origincore.hook.mythiclib;

import cn.originmc.plugins.origincore.util.text.FormatText;
import io.lumine.mythic.lib.api.player.MMOPlayerData;
import io.lumine.mythic.lib.api.stat.StatInstance;
import io.lumine.mythic.lib.api.stat.StatMap;
import io.lumine.mythic.lib.api.stat.modifier.StatModifier;
import io.lumine.mythic.lib.player.modifier.ModifierType;
import org.bukkit.entity.Player;

import java.util.*;

public class MythicLibManager {
    public static MMOPlayerData getPlayerData(Player player){
        return MMOPlayerData.get(player);
    }
    public static MMOPlayerData getPlayerData(UUID uuid){
        return MMOPlayerData.get(uuid);
    }
    public static StatMap getStatMap(Player player){
        return getPlayerData(player).getStatMap();
    }
    public static StatMap getStatMap(UUID uuid){
        return getPlayerData(uuid).getStatMap();
    }
    public static void addStat(Player player,String id,String name,double value,int type){
        ModifierType modifierType;
        if (type==1){
            modifierType=ModifierType.RELATIVE;
        }else {
            modifierType=ModifierType.FLAT;
        }
        StatModifier modifier = new StatModifier(id, name, value, modifierType);
        modifier.register(getPlayerData(player));
    }
    public static void addStat(UUID uuid,String id,String name,double value,int type){
        ModifierType modifierType;
        if (type==1){
            modifierType=ModifierType.RELATIVE;
        }else {
            modifierType=ModifierType.FLAT;
        }
        StatModifier modifier = new StatModifier(id, name, value, modifierType);
        modifier.register(getPlayerData(uuid));
    }
    public static Collection<StatModifier> getStat(Player player, String name){
        return getStatMap(player).getInstance(name).getModifiers();
    }
    public static Collection<StatModifier> getStat(UUID uuid, String name){
        return getStatMap(uuid).getInstance(name).getModifiers();
    }
    public static double getValue(Player player,String id,String name){
        if (getStatMap(player).getInstance(name).getModifier(id)==null){
            return 0;
        }
        return Objects.requireNonNull(getStatMap(player).getInstance(name).getModifier(id)).getValue();
    }
    public static double getValue(UUID uuid,String id,String name){
        if (getStatMap(uuid).getInstance(name).getModifier(id)==null){
            return 0;
        }
        return Objects.requireNonNull(getStatMap(uuid).getInstance(name).getModifier(id)).getValue();
    }
    public static double getValue(Player player,String name){
        return getStatMap(player).getInstance(name).getTotal();
    }
    public static double getValue(UUID uuid,String name){
        return getStatMap(uuid).getInstance(name).getTotal();
    }
    public static void removePrefix(Player player,String prefix,String name){
        getStatMap(player).getInstance(name).removeIf(key -> key.startsWith(prefix));
    }
    public static void removePrefix(UUID uuid,String prefix,String name){
        getStatMap(uuid).getInstance(name).removeIf(key -> key.startsWith(prefix));
    }
    public static List<FormatText> getStatList(Player player,String name){
        List<FormatText> formatTextList=new ArrayList<>();
        for (String key : getStatMap(player).getInstance(name).getKeys()) {
            FormatText formatText=new FormatText("name^"+name+"`id^"+key+"`value^"+getStatMap(player).getInstance(name).getModifier(key).getValue());
            formatTextList.add(formatText);
        }
        return formatTextList;
    }
    public static List<FormatText> getStatList(UUID uuid,String name){
        List<FormatText> formatTextList=new ArrayList<>();
        for (String key : getStatMap(uuid).getInstance(name).getKeys()) {
            FormatText formatText=new FormatText("name^"+name+"`id^"+key+"`value^"+getStatMap(uuid).getInstance(name).getModifier(key).getValue());
            formatTextList.add(formatText);
        }
        return formatTextList;
    }
    public static List<FormatText> getStatList(Player player){
        List<FormatText> formatTextList=new ArrayList<>();
        for (StatInstance instance : getStatMap(player).getInstances()) {
            for (String key : instance.getKeys()) {
                FormatText formatText=new FormatText("name^"+instance.getStat()+"`id^"+key+"`value^"+instance.getModifier(key).getValue());
                formatTextList.add(formatText);
            }
        }
        return formatTextList;
    }
    public static List<FormatText> getStatList(UUID uuid){
        List<FormatText> formatTextList=new ArrayList<>();
        for (StatInstance instance : getStatMap(uuid).getInstances()) {
            for (String key : instance.getKeys()) {
                FormatText formatText=new FormatText("name^"+instance.getStat()+"`id^"+key+"`value^"+instance.getModifier(key).getValue());
                formatTextList.add(formatText);
            }
        }
        return formatTextList;
    }
}
