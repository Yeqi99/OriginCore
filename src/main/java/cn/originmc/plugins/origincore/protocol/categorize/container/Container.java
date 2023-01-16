package cn.originmc.plugins.origincore.protocol.categorize.container;

import cn.originmc.plugins.origincore.util.location.Location;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class Container {
    private PacketContainer packet;
    public Container(){

    }
    public void initPacket(int id, boolean longDistance, Location start, Location offset, Float maxSpeed){
        setPacket(new PacketContainer(PacketType.Play.Server.WINDOW_ITEMS));
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
    public PacketContainer getPacket() {
        return packet;
    }

    public void setPacket(PacketContainer packet) {
        this.packet = packet;
    }
}
