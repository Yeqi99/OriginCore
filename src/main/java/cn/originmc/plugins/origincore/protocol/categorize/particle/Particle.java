package cn.originmc.plugins.origincore.protocol.categorize.particle;

import cn.originmc.plugins.origincore.hook.ProtocolLibHook;
import cn.originmc.plugins.origincore.util.location.Location;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Particle {
    private PacketContainer packet;

    public Particle(boolean longDistance, Location start,Location offset,Float maxSpeed,int count){
        initPacket(longDistance,start,offset,maxSpeed,count);
    }
    public void initPacket(boolean longDistance, Location start,Location offset,Float maxSpeed,int count){
        setPacket(new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES));
        packet.getModifier().writeDefaults();
        packet.getBooleans().write(0,longDistance);
        packet.getDoubles().write(0, start.getX());
        packet.getDoubles().write(1, start.getY());
        packet.getDoubles().write(2, start.getZ());
        packet.getFloat().write(0,(float) offset.getX());
        packet.getFloat().write(1,(float) offset.getY());
        packet.getFloat().write(2,(float) offset.getZ());
        packet.getFloat().write(3,maxSpeed);
        packet.getIntegers().write(1,count);
    }
    public PacketContainer getNormalParticlePacket(int id){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,id);
        return clone;
    }
    public PacketContainer getDustParticlePacket(float red,float green,float blue,float scale){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,14);
        clone.getFloat().write(4,red);
        clone.getFloat().write(5,green);
        clone.getFloat().write(6,blue);
        clone.getFloat().write(7,scale);
        return clone;
    }
    public PacketContainer getTransitionDustParticlePacket(float from_Red,float from_Green,float from_Blue,float to_Red,float to_Green,float to_Blue,float scale){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,15);
        clone.getFloat().write(4,from_Red);
        clone.getFloat().write(5,from_Green);
        clone.getFloat().write(6,from_Blue);
        clone.getFloat().write(7,scale);
        clone.getFloat().write(8,to_Red);
        clone.getFloat().write(9,to_Green);
        clone.getFloat().write(10,to_Blue);
        return clone;
    }

    public PacketContainer getBlockParticlePacket(int blockState){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,2);
        clone.getIntegers().write(3,blockState);
        return clone;
    }
    public PacketContainer getBlockMarkerParticlePacket(int blockState){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,3);
        clone.getIntegers().write(3,blockState);
        return clone;
    }
    public PacketContainer getFallingDustParticlePacket(int blockState){
        PacketContainer clone=packet;
        clone.getIntegers().write(0,24);
        clone.getIntegers().write(3,blockState);
        return clone;
    }
    public boolean sendToPlayer(PacketContainer packet,Player player){
        if(ProtocolLibHook.isLoad()){
            if(player!=null){
                if(packet!=null){
                    try {
                        ProtocolLibHook.getPm().sendServerPacket(player,packet);
                        return true;
                    } catch (InvocationTargetException e) {
                        return false;
                    }
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
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
