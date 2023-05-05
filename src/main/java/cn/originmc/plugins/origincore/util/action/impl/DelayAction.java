package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.time.TimeControl;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class DelayAction extends AbstractAction {


    public DelayAction(FormatText actionSetting, Map<String, Object> objectMap) {
        super(actionSetting, objectMap);
    }

    public DelayAction(FormatText actionSetting) {
        super(actionSetting);
    }

    @Override
    public boolean execute(List<Boolean> beforeExecuteResult,FormatText parameter) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        String time=getActionSetting().getValue("time");
        if (parameter!=null){
            if (time.contains("!")){
                if (parameter.hasKey(time.replace("!",""))){
                    time=parameter.getValue(time.replace("!",""));
                }
            }
        }
        if (PlaceholderAPIHook.isLoad()){
            time=PlaceholderAPIHook.getPlaceholder((Player) getObject("self"),time);
        }
        if (time==null){
            return false;
        }
        return TimeControl.delay(Long.parseLong(time));
    }
}
