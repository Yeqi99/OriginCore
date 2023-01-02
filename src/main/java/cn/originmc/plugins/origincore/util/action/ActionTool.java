package cn.originmc.plugins.origincore.util.action;

import cn.originmc.plugins.origincore.util.text.FormatText;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ActionTool {
    /**
     * 从格式化字符串中获取Action
     * @param plugin 插件实例
     * @param formatString 格式化字符串
     * @return Action动作
     */
    public Action getAction(JavaPlugin plugin, String formatString){
        FormatText formatText=new FormatText(formatString);
        return new Action(plugin,formatText);
    }

    /**
     * 从格式化字符串列表中获取动作列表
     * @param plugin 插件实例
     * @param formatStringList 格式化字符串列表
     * @return Action动作列表
     */
    public List<Action> getActionList(JavaPlugin plugin,List<String> formatStringList){
        List<Action> actionList=new ArrayList<>();
        for (String s : formatStringList) {
            FormatText formatText=new FormatText(s);
            actionList.add(new Action(plugin,formatText));
        }
        return actionList;
    }

    /**
     * 让某玩家执行动作列表
     * @param player 玩家实例
     * @param actionList 动作列表
     * @return 执行结果
     */
    public boolean execute(Player player,List<Action> actionList){
        for (Action action : actionList) {
            //某个动作失败则直接中断队列
            if (!action.execute(player)){
                return false;
            }
        }
        return true;
    }

    /**
     * 让玩家直接执行格式化字符串
     * @param plugin 插件实例
     * @param player 玩家实例
     * @param formatString 格式化字符串
     * @return 执行结果
     */
    public boolean execute(JavaPlugin plugin,Player player,String formatString){
        return getAction(plugin,formatString).execute(player);
    }

    /**
     * 让玩家直接执行格式化字符串列表
     * @param plugin 插件实例
     * @param player 玩家实例
     * @param formatStringList 格式化字符串列表
     * @return 执行结果
     */
    public boolean execute(JavaPlugin plugin,Player player,List<String> formatStringList){
        for (String s : formatStringList) {
            //某个动作失败则直接中断队列
            if(!execute(plugin,player,s)){
                return false;
            }
        }
        return true;
    }
}
