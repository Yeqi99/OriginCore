package cn.originmc.plugins.origincore.util.bungeecord;

import cn.originmc.plugins.origincore.OriginCore;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class BungeeCordUtil {
    private Player sender;
    public BungeeCordUtil(Player player){
        setSender(player);
    }
    public void teleport(String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        getSender().sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void teleport(String playerName,String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        getSender().sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void sendMessage(String playerName,String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);
        getSender().sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public void kickPlayer(String playerName,String kickMessage){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(kickMessage);
        getSender().sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public static void teleport(Player sender,String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public static void teleport(Player sender,String playerName,String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public static void sendMessage(Player sender,String playerName,String message){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(playerName);
        out.writeUTF(message);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }
    public static void kickPlayer(Player sender,String playerName,String kickMessage){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(playerName);
        out.writeUTF(kickMessage);
        sender.sendPluginMessage(OriginCore.getInstance(),"BungeeCord",out.toByteArray());
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }
}
