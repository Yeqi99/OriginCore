package cn.originmc.plugins.origincore.listener.cooldown;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.listener.cooldown.object.CoolDownQueue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CoolDownListener {
    private static boolean enable;
    private static CoolDownQueue queue=new CoolDownQueue();
    public static void listen(){
        new BukkitRunnable(){
            @Override
            public void run() {
                if (isEnable()){
                    queue.goTime();
                }else {
                    queue.clear();
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(OriginCore.getInstance(),0,1);
    }
    public static boolean register(long time,UUID id){
        return queue.createCoolDown(time,id);
    }
    public static boolean register(long time,UUID id,String sId){
        return queue.add(time,id,sId);
    }
    public static long getTime(UUID id,String sId){
        if (queue.hasCoolDown(id)){
            return queue.getTime(id,sId);
        }else {
            return 0;
        }
    }
    public static boolean isCoolDown(UUID id){
        return queue.hasCoolDown(id);
    }
    public static long getTime(UUID id){
        if (queue.hasCoolDown(id)){
            return queue.getCoolDown(id).getTime();
        }else {
            return 0;
        }
    }
    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        CoolDownListener.enable = enable;
    }
    public static void clear(){
        queue.clear();
    }
    public static void clear(UUID id){
        queue.remove(id);
    }
    public static CoolDownQueue getQueue() {
        return queue;
    }

    public static void setQueue(CoolDownQueue queue) {
        CoolDownListener.queue = queue;
    }
}
