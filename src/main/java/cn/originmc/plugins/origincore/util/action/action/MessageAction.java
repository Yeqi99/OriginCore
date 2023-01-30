package cn.originmc.plugins.origincore.util.action.action;

import cn.originmc.plugins.origincore.util.action.AbstractAction;
import cn.originmc.plugins.origincore.util.action.ObjectType;
import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class MessageAction extends AbstractAction {


    public MessageAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult, Map<ObjectType, Object> objectMap) {
        super(plugin, actionSetting, beforeExecuteResult, objectMap);
    }

    @Override
    public boolean execute() {
        if (!getPreconditions(getBeforeExecuteResult())){
            return false;
        }

        return true;
    }
}
