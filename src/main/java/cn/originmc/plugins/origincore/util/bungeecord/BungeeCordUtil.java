package cn.originmc.plugins.origincore.util.bungeecord;

import cn.originmc.plugins.origincore.OriginCore;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class BungeeCordUtil {
    public void teleport(Player sender,String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void teleport(Player sender,String playerName,String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void sendMessage(Player sender,String playerName,String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void kickPlayer(Player sender,String playerName,String kickMessage){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(kickMessage);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
}
