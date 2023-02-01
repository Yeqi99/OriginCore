package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
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
    public boolean execute(List<Boolean> beforeExecuteResult) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        String messageMode=getActionSetting().getValue("mode");
        String message=getActionSetting().getValue("message");
        if (messageMode.equalsIgnoreCase("self")){
            Player self=getSelf();
            if (PlaceholderAPIHook.isLoad()){
                message=PlaceholderAPIHook.getPlaceholder(self,message);
            }
            if (self==null){
                return false;
            }
            OriginCore.getSender().sendToPlayer(self,message);
        }else if (messageMode.equalsIgnoreCase("all")){
            OriginCore.getSender().sendPAPIToOnline(message);
        }else if (messageMode.equalsIgnoreCase("target")){
            Player target= getTarget();
            if (PlaceholderAPIHook.isLoad()){
                message=PlaceholderAPIHook.getPlaceholder(target,message);
            }
            if (target==null){
                return false;
            }
            OriginCore.getSender().sendToPlayer(target,message);
        }
        return true;
    }
}
