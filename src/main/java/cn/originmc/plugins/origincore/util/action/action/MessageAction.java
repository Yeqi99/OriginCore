package cn.originmc.plugins.origincore.util.action.action;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class MessageAction extends AbstractAction {


    public MessageAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult, Map<String, Object> objectMap) {
        super(plugin, actionSetting, beforeExecuteResult, objectMap);
    }

    public MessageAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult) {
        super(plugin, actionSetting, beforeExecuteResult);
    }

    @Override
    public boolean execute() {
        if (!canExecute()){
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
