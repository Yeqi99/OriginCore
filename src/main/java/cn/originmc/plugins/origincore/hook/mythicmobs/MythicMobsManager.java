package cn.originmc.plugins.origincore.hook.mythicmobs;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class MythicMobsManager {
    public static ActiveMob spawnMob(String mobName, Location spawnLoc){
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        Location spawnLocation = spawnLoc;
        if(mob != null){
            // spawns mob
            ActiveMob someMob = mob.spawn(BukkitAdapter.adapt(spawnLocation),1);
            return someMob;
        }
        return null;
    }
    public static Entity spawnMobGetEntity(String mobName, Location spawnLoc){
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        Location spawnLocation = spawnLoc;
        if(mob != null){
            // spawns mob
            ActiveMob someMob = mob.spawn(BukkitAdapter.adapt(spawnLocation),1);
            return someMob.getEntity().getBukkitEntity();
        }
        return null;
    }
    public static Entity getEntity(ActiveMob mob){
        return mob.getEntity().getBukkitEntity();
    }
    public static ActiveMob getActiveMob(Entity entity){
        return MythicBukkit.inst().getMobManager().getActiveMob(entity.getUniqueId()).orElse(null);
    }
    public static boolean isMythicMob(Entity entity){
        ActiveMob mythicMob=getActiveMob(entity);
        if(mythicMob==null){
            return false;
        }
        return true;
    }
    public static boolean isNamedMythicMob(Entity entity,String mobName){
        if(isMythicMob(entity)){
            if(getActiveMob(entity).getName().equals(mobName)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
