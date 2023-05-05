package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.text.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class MessageAction extends AbstractAction {


    public MessageAction(FormatText actionSetting, Map<String, Object> objectMap) {
        super(actionSetting, objectMap);
    }

    public MessageAction(FormatText actionSetting) {
        super(actionSetting);
    }

    @Override
    public boolean execute(List<Boolean> beforeExecuteResult,FormatText parameter) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        switch (getMode()){
            case "public":{
                new Sender(OriginCore.getInstance()).sendToAllPlayer(getMessage(parameter));
                return true;
            }
            case "private":{
                Player player=getPlayer(parameter);
                if (player==null){
                    return false;
                }
                if (!player.isOnline()){
                    return false;
                }
                new Sender(OriginCore.getInstance()).sendToPlayer(player,getMessage(parameter));
                return true;
            }
            case "logger":{
                new Sender(OriginCore.getInstance()).sendToLogger(getMessage(parameter));
            }
        }
        return true;
    }
    public String getMessage(FormatText parameter){
        String message=getActionSetting().getValue("message");
        if (parameter!=null){
            if (message.contains("!")){
                if (parameter.hasKey(message.replace("!",""))){
                    message=parameter.getValue(message.replace("!",""));
                }
            }
        }
        if (PlaceholderAPIHook.isLoad()){
            message=PlaceholderAPIHook.getPlaceholder((Player) getObject("self"),message);
        }
        return message;
    }
    public Player getPlayer(FormatText parameter){
        String playerName=getActionSetting().getValue("player");
        if (parameter!=null){
            if (playerName.contains("!")){
                if (parameter.hasKey(playerName.replace("!",""))){
                    playerName=parameter.getValue(playerName.replace("!",""));
                }
            }
        }
        if (PlaceholderAPIHook.isLoad()){
            playerName=PlaceholderAPIHook.getPlaceholder((Player) getObject("self"),playerName);
        }
        return Bukkit.getPlayer(playerName);
    }
}
