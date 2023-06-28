package cn.originmc.plugins.origincore.protocol.categorize.border;

import cn.originmc.plugins.origincore.hook.ProtocolLibHook;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class Border {
    private PacketContainer packet;
    public Border(double x, double y, double oldDiameter, double newDiameter, long speed,int warningBlocks,int warningTime){
        initPacket(x,y,oldDiameter,newDiameter,speed,warningBlocks,warningTime);
    }
    public void initPacket(double x, double y, double oldDiameter, double newDiameter, long speed,int warningBlocks,int warningTime){
        packet=new PacketContainer(PacketType.Play.Server.INITIALIZE_BORDER);
        packet.getModifier().writeDefaults();
        packet.getDoubles().write(0,x);
        packet.getDoubles().write(1,y);
        packet.getDoubles().write(2,oldDiameter);
        packet.getDoubles().write(3,newDiameter);
        packet.getLongs().write(0,speed);
        packet.getIntegers().write(0,29999984);
        packet.getIntegers().write(1,warningBlocks);
        packet.getIntegers().write(2,warningTime);
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
        ProtocolLibHook.getPm().sendServerPacket(player,packet);
        return true;
    }
    public PacketContainer getPacket() {
        return packet;
    }

    public void setPacket(PacketContainer packet) {
        this.packet = packet;
    }
}
