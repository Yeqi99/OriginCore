package cn.originmc.plugins.origincore.util.action.impl;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.mythicmobs.MythicMobsManager;
import cn.originmc.plugins.origincore.util.action.object.abs.AbstractAction;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.text.Sender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class MMSkillAction extends AbstractAction {
    public MMSkillAction(FormatText actionSetting, Map<String, Object> objectMap) {
        super(actionSetting, objectMap);
    }

    public MMSkillAction(FormatText actionSetting) {
        super(actionSetting);
    }

    @Override
    public boolean execute(List<Boolean> beforeExecuteResult,FormatText parameter) {
        if (!canExecute(beforeExecuteResult)){
            return false;
        }
        String skillName=getActionSetting().getValue("skill");
        String p=getActionSetting().getValue("power");
        if (parameter!=null){
            if (skillName.contains("!")){
                if (parameter.hasKey(skillName.replace("!",""))){
                    skillName=parameter.getValue(skillName.replace("!",""));
                }
            }
            if (p.contains("!")){
                if (parameter.hasKey(p.replace("!",""))){
                    p=parameter.getValue(p.replace("!",""));
                }
            }
        }
        if (PlaceholderAPIHook.isLoad()){
            skillName=PlaceholderAPIHook.getPlaceholder((Player) getObject("self"),skillName);
            p=PlaceholderAPIHook.getPlaceholder((Player) getObject("self"),p);
        }
        float power= Float.parseFloat(p);
        return MythicMobsManager.castSkill((Player) getObject("self"),skillName,power);
    }
}
