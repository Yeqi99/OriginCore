package cn.originmc.plugins.origincore.util.location;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

public class OCLocation extends Location {
    public OCLocation(@Nullable World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }
    public Location getFrontOffset(int distance){
        float yaw=getYaw();
        if (yaw>=0&yaw<=22.5|yaw<=0&yaw>=-22.5){
            return add(0,0, distance);
        }else if(yaw>=157.5&yaw<=180|yaw>=-180&yaw<=-157.5){
            return add(0,0,-distance);
        }else if (yaw>=67.5&yaw<=112.5){
            return add(-distance,0,0);
        }else if (yaw<-67.5&yaw>-112.5){
            return add(distance,0,0);
        }else if (yaw>22.5&yaw<67.5){
            return add(-distance,0, distance);
        }else if (yaw>112.5&yaw<157.5){
            return add(-distance,0,-distance);
        }else if (yaw<-22.5&yaw>-67.5){
            return add(distance,0, distance);
        }else if (yaw<-112.5&yaw>-157.5){
            return add(distance,0,-distance);
        }
        return this;
    }
}
