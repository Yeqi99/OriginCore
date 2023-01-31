package cn.originmc.plugins.origincore.util.action.action;

import cn.originmc.plugins.origincore.util.action.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.time.TimeControl;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public class DelayAction extends AbstractAction {

    public DelayAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult, Map<String, Object> objectMap) {
        super(plugin, actionSetting, beforeExecuteResult, objectMap);
    }

    public DelayAction(JavaPlugin plugin, FormatText actionSetting, List<Boolean> beforeExecuteResult) {
        super(plugin, actionSetting, beforeExecuteResult);
    }

    @Override
    public boolean execute() {
        if (!canExecute()){
            return false;
        }
        String time=getActionSetting().getValue("time");
        if (time==null){
            return false;
        }
        return TimeControl.delay(Long.parseLong(time));
    }
}
