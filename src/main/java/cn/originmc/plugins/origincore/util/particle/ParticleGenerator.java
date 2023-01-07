package cn.originmc.plugins.origincore.util.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class ParticleGenerator {
    private Location location;
    private int count;
    private double offsetX,offsetY,offsetZ,extra;
    private Color color,toColor;
    private float size;
    private ItemStack itemStack;
    private Vibration vibration;
    private BlockData blockData;
    public boolean generator(Particle particle,Location location){
        if (location.getWorld()==null){
            return false;
        }
        switch (particle){
            case REDSTONE:{
                Particle.DustOptions dustOptions=new Particle.DustOptions(getColor(),getSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustOptions);
                return true;
            }
            case DUST_COLOR_TRANSITION:{
                Particle.DustTransition dustTransition=new Particle.DustTransition(getColor(),getToColor(),getSize());
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),dustTransition);
                return true;
            }
            case ITEM_CRACK:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getItemStack());
                return true;
            }
            case VIBRATION:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getVibration());
                return true;
            }
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case BLOCK_MARKER:
            case FALLING_DUST:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra(),getBlockData());
                return true;
            }
            default:{
                location.getWorld().spawnParticle(particle,location,getCount(),getOffsetX(),getOffsetY(),getOffsetZ(),getExtra());
                return true;
            }
        }
    }
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(double offsetZ) {
        this.offsetZ = offsetZ;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getToColor() {
        return toColor;
    }

    public void setToColor(Color toColor) {
        this.toColor = toColor;
    }

    public Vibration getVibration() {
        return vibration;
    }

    public void setVibration(Vibration vibration) {
        this.vibration = vibration;
    }

    public BlockData getBlockData() {
        return blockData;
    }

    public void setBlockData(BlockData blockData) {
        this.blockData = blockData;
    }
}
