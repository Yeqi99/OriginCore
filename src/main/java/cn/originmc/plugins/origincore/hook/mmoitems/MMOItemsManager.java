package cn.originmc.plugins.origincore.hook.mmoitems;


import io.lumine.mythic.lib.skill.handler.SkillAPISkillHandler;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.skill.RegisteredSkill;
import net.Indyuce.mmoitems.stat.data.AbilityData;
import net.Indyuce.mmoitems.stat.data.AbilityListData;
import org.bukkit.inventory.ItemStack;


public class MMOItemsManager {
    public static MMOItem getMMOItem(String type, String name){
        return MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(type), name);
    }
    public static ItemStack getItem(String type, String name){
        return getMMOItem(type,name).newBuilder().build();
    }
    /*
    public static ItemStack giveSkill(ItemStack itemStack){
        // Getting the mmoitem from your itemstack
        MMOItem mmoitem = new LiveMMOItem(itemStack);

// You need the AbilityData class to bind an ability to an item
        AbilityData ad = new AbilityData();
// setup modifiers using ad.setModifier(...);

// The stat data is not an ability, it's a LIST of abilities
        AbilityListData ald = new AbilityListData();
        ald.add(ald.getAbilities());

// Finally set the data of your item
        mmoitem.setData(ItemStats.ABILITIES, ald);

// Do whatever you want with your MMOItem, generate it etc.
    }
     */
}
