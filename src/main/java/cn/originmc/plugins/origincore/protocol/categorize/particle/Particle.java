package cn.originmc.plugins.origincore.protocol.categorize.particle;

import cn.originmc.plugins.origincore.hook.ProtocolLibHook;
import cn.originmc.plugins.origincore.util.location.Location;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Particle {
    private PacketContainer packet;

    public Particle(int id,boolean longDistance, Location start,Location offset,Float maxSpeed){
        initPacket(id,longDistance,start,offset,maxSpeed);
    }
    public void initPacket(int id,boolean longDistance, Location start,Location offset,Float maxSpeed){
        setPacket(new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES));
        packet.getModifier().writeDefaults();
        packet.getIntegers().write(0,id);
        packet.getBooleans().write(0,longDistance);
        packet.getDoubles().write(0, start.getX());
        packet.getDoubles().write(1, start.getY());
        packet.getDoubles().write(2, start.getZ());
        packet.getFloat().write(0,(float) offset.getX());
        packet.getFloat().write(1,(float) offset.getY());
        packet.getFloat().write(2,(float) offset.getZ());
        packet.getFloat().write(3,maxSpeed);
    }
    public boolean sendToPlayer(Player player){
        if(!ProtocolLibHook.isLoad()){
            return false;
        }
        if(player==null){
            return false;
        }
        if(packet==null){
            return false;
        }
        try {
            ProtocolLibHook.getPm().sendServerPacket(player,packet);
            return true;
        } catch (InvocationTargetException e) {
            return false;
        }
    }
    public PacketContainer getPacket() {
        return packet;
    }

    public void setPacket(PacketContainer packet) {
        this.packet = packet;
    }

}
