package cn.originmc.plugins.origincore.util.action.old;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.hook.PlaceholderAPIHook;
import cn.originmc.plugins.origincore.hook.PlayerPointsHook;
import cn.originmc.plugins.origincore.hook.VaultHook;
import cn.originmc.plugins.origincore.util.list.ListUtil;
import cn.originmc.plugins.origincore.util.particle.ParticleGenerator;
import cn.originmc.plugins.origincore.util.text.FormatText;
import cn.originmc.plugins.origincore.util.text.Sender;
import cn.originmc.plugins.origincore.util.time.TimeControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
@Deprecated
public class Action {
    private ActionType type;
    private FormatText formatText;
    private JavaPlugin plugin;


    /**
     * 动作构造方法
     * @param plugin 插件实例
     * @param formatText 格式化文本容器
     */
    public Action(JavaPlugin plugin, FormatText formatText){
        setPlugin(plugin);
        setType(ActionType.valueOf(formatText.getValue("type")));
        setFormatText(formatText);
    }

    /**
     * 给某玩家执行本动作
     * 动作枚举详见ActionType
     * @param player 玩家实例
     * @return 执行结果
     */
    public boolean execute(Player player){
        switch (type){
            case SELF:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> player.performCommand(finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case CONSOLE:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case TELL:{
                new Sender(getPlugin()).sendToPlayer(player,formatText.getValue("text"));
                return true;
            }
            case TELLALL:{
                new Sender(getPlugin()).sendToAllPlayer(formatText.getValue("text"));
                return true;
            }
            case DELAY:{
                return TimeControl.delay(Long.parseLong(formatText.getValue("time")));
            }
            case MONEY:{
                if (VaultHook.isLoad()){
                    VaultHook.giveMoney(player, Double.parseDouble(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            case POINTS:{
                if (PlayerPointsHook.isLoad()){
                    PlayerPointsHook.givePoints(player.getUniqueId(), Integer.parseInt(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            case PARTICLE:{
                List<String> locationList= ListUtil.getListFromFormatStr(formatText.getValue("location"));
                List<String> offsetList= ListUtil.getListFromFormatStr(formatText.getValue("offset"));
                String rgb1=formatText.getValue("rgb");
                String rgb2=formatText.getValue("rgbFrom");
                String rgb3=formatText.getValue("rgbTo");
                List<String> rgbList=null;
                List<String> rgbFromList=null;
                List<String> rgbToList=null;
                if (rgb1!=null){
                    rgbList= ListUtil.getListFromFormatStr(rgb1);
                }
                if (rgb2!=null){
                    rgbFromList= ListUtil.getListFromFormatStr(rgb2);
                }
                if (rgb3!=null){
                    rgbToList= ListUtil.getListFromFormatStr(rgb3);
                }
                String type=formatText.getValue("pType");
                int count= Integer.parseInt(formatText.getValue("count"));
                double offsetX= Double.parseDouble(offsetList.get(0));
                double offsetY= Double.parseDouble(offsetList.get(1));
                double offsetZ= Double.parseDouble(offsetList.get(2));
                double extra= Double.parseDouble(formatText.getValue("extra"));
                if (locationList.get(0).equalsIgnoreCase("self")){
                    locationList.set(0,player.getLocation().getWorld().getName());
                }else if(locationList.get(0).contains("!")){
                    String world= locationList.get(0).replace("!","");
                    locationList.set(0,world);
                }else {
                    locationList.set(0,locationList.get(0));
                }
                if (locationList.get(1).equalsIgnoreCase("self")){
                    locationList.set(1,player.getLocation().getX()+"");
                }else if(locationList.get(1).contains("!")){
                    double x= Double.parseDouble(locationList.get(1).replace("!",""));
                    locationList.set(1,x+"");
                }else {
                    locationList.set(1,player.getLocation().getX()+Double.parseDouble(locationList.get(1))+"");
                }
                if (locationList.get(2).equalsIgnoreCase("self")){
                    locationList.set(2,player.getLocation().getY()+"");
                }else if(locationList.get(2).contains("!")){
                    double y= Double.parseDouble(locationList.get(2).replace("!",""));
                    locationList.set(2,y+"");
                }else {
                    locationList.set(2,player.getLocation().getY()+Double.parseDouble(locationList.get(2))+"");
                }
                if (locationList.get(3).equalsIgnoreCase("self")){
                    locationList.set(3,player.getLocation().getZ()+"");
                }else if(locationList.get(3).contains("!")){
                    double z= Double.parseDouble(locationList.get(3).replace("!",""));
                    locationList.set(3,z+"");
                }else {
                    locationList.set(3,player.getLocation().getZ()+Double.parseDouble(locationList.get(3))+"");
                }
                Location location=new Location(Bukkit.getWorld(locationList.get(0)),Double.parseDouble(locationList.get(1)),Double.parseDouble(locationList.get(2)),Double.parseDouble(locationList.get(3)));
                ParticleGenerator particleGenerator=new ParticleGenerator(location,count,offsetX,offsetY,offsetZ,extra);
                if (rgbList!=null){
                    particleGenerator.setRedStoneData(Integer.parseInt(rgbList.get(0)),Integer.parseInt(rgbList.get(1)),Integer.parseInt(rgbList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                if (rgbFromList!=null&&rgbToList!=null){
                    particleGenerator.setDustColorTransition(
                            Integer.parseInt(rgbFromList.get(0))
                            ,Integer.parseInt(rgbFromList.get(1))
                            ,Integer.parseInt(rgbFromList.get(2))
                            ,Integer.parseInt(rgbToList.get(0))
                            ,Integer.parseInt(rgbToList.get(1))
                            ,Integer.parseInt(rgbToList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                new Sender(OriginCore.getInstance()).sendToAllPlayer("22222222");
                ParticleGenerator pg= new ParticleGenerator(player.getLocation(),10,10,10,10,2);
                pg.setRedStoneData(255,255,255,10);
                pg.generator(Particle.REDSTONE);
                particleGenerator.generator(Particle.valueOf(type));
                return true;
            }
            default:{
                return false;
            }
        }
    }
    public boolean getPreconditions(List<Boolean> beforeExecuteResult){
        if (formatText.hasKey("preconditions")){
            int preconditions= Integer.parseInt(formatText.getValue("preconditions"));
            if (preconditions>=beforeExecuteResult.size()){
                return false;
            }
            return beforeExecuteResult.get(preconditions);
        }else if (formatText.hasKey("!preconditions")){
            int preconditions= Integer.parseInt(formatText.getValue("!preconditions"));
            if (preconditions>=beforeExecuteResult.size()){
                return true;
            }
            return !beforeExecuteResult.get(preconditions);
        }
        return true;
    }
    public boolean execute(Player player, List<Boolean> beforeExecuteResult){
        if (!getPreconditions(beforeExecuteResult)){
            return false;
        }
        switch (type){
            case SELF:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> player.performCommand(finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case CONSOLE:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case TELL:{
                new Sender(getPlugin()).sendToPlayer(player,formatText.getValue("text"));
                return true;
            }
            case TELLALL:{
                new Sender(getPlugin()).sendToAllPlayer(formatText.getValue("text"));
                return true;
            }
            case DELAY:{
                return TimeControl.delay(Long.parseLong(formatText.getValue("time")));
            }
            case MONEY:{
                if (VaultHook.isLoad()){
                    VaultHook.giveMoney(player, Double.parseDouble(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            case POINTS:{
                if (PlayerPointsHook.isLoad()){
                    return PlayerPointsHook.givePoints(player.getUniqueId(), Integer.parseInt(formatText.getValue("value")));
                }else {
                    return false;
                }
            }
            case PARTICLE:{
                List<String> locationList= ListUtil.getListFromFormatStr(formatText.getValue("location"));
                List<String> offsetList= ListUtil.getListFromFormatStr(formatText.getValue("offset"));
                String rgb1=formatText.getValue("rgb");
                String rgb2=formatText.getValue("rgbFrom");
                String rgb3=formatText.getValue("rgbTo");
                List<String> rgbList=null;
                List<String> rgbFromList=null;
                List<String> rgbToList=null;
                if (rgb1!=null){
                    rgbList= ListUtil.getListFromFormatStr(rgb1);
                }
                if (rgb2!=null){
                    rgbFromList= ListUtil.getListFromFormatStr(rgb2);
                }
                if (rgb3!=null){
                    rgbToList= ListUtil.getListFromFormatStr(rgb3);
                }
                String type=formatText.getValue("pType");
                int count= Integer.parseInt(formatText.getValue("count"));
                double offsetX= Double.parseDouble(offsetList.get(0));
                double offsetY= Double.parseDouble(offsetList.get(1));
                double offsetZ= Double.parseDouble(offsetList.get(2));
                double extra= Double.parseDouble(formatText.getValue("extra"));
                if (locationList.get(0).equalsIgnoreCase("self")){
                    locationList.set(0,player.getLocation().getWorld().getName());
                }else if(locationList.get(0).contains("!")){
                    String world= locationList.get(0).replace("!","");
                    locationList.set(0,world);
                }else {
                    locationList.set(0,locationList.get(0));
                }
                if (locationList.get(1).equalsIgnoreCase("self")){
                    locationList.set(1,player.getLocation().getX()+"");
                }else if(locationList.get(1).contains("!")){
                    double x= Double.parseDouble(locationList.get(1).replace("!",""));
                    locationList.set(1,x+"");
                }else {
                    locationList.set(1,player.getLocation().getX()+Double.parseDouble(locationList.get(1))+"");
                }
                if (locationList.get(2).equalsIgnoreCase("self")){
                    locationList.set(2,player.getLocation().getY()+"");
                }else if(locationList.get(2).contains("!")){
                    double y= Double.parseDouble(locationList.get(2).replace("!",""));
                    locationList.set(2,y+"");
                }else {
                    locationList.set(2,player.getLocation().getY()+Double.parseDouble(locationList.get(2))+"");
                }
                if (locationList.get(3).equalsIgnoreCase("self")){
                    locationList.set(3,player.getLocation().getZ()+"");
                }else if(locationList.get(3).contains("!")){
                    double z= Double.parseDouble(locationList.get(3).replace("!",""));
                    locationList.set(3,z+"");
                }else {
                    locationList.set(3,player.getLocation().getZ()+Double.parseDouble(locationList.get(3))+"");
                }
                Location location=new Location(Bukkit.getWorld(locationList.get(0)),Double.parseDouble(locationList.get(1)),Double.parseDouble(locationList.get(2)),Double.parseDouble(locationList.get(3)));
                ParticleGenerator particleGenerator=new ParticleGenerator(location,count,offsetX,offsetY,offsetZ,extra);
                if (rgbList!=null){
                    particleGenerator.setRedStoneData(Integer.parseInt(rgbList.get(0)),Integer.parseInt(rgbList.get(1)),Integer.parseInt(rgbList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                if (rgbFromList!=null&&rgbToList!=null){
                    particleGenerator.setDustColorTransition(
                            Integer.parseInt(rgbFromList.get(0))
                            ,Integer.parseInt(rgbFromList.get(1))
                            ,Integer.parseInt(rgbFromList.get(2))
                            ,Integer.parseInt(rgbToList.get(0))
                            ,Integer.parseInt(rgbToList.get(1))
                            ,Integer.parseInt(rgbToList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                particleGenerator.generator(Particle.valueOf(type));
                return true;
            }
            default:{
                return false;
            }
        }
    }
    public boolean execute(Player player, List<Boolean> beforeExecuteResult,List<Object> objects){
        if (!getPreconditions(beforeExecuteResult)){
            return false;
        }
        switch (type){
            case SELF:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> player.performCommand(finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case CONSOLE:{
                String command=formatText.getValue("command");
                if (PlaceholderAPIHook.isLoad()){
                    command=PlaceholderAPIHook.getPlaceholder(player,command);
                }
                String finalCommand = command;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand));
                    }
                }.runTaskAsynchronously(getPlugin());
                return true;
            }
            case TELL:{
                new Sender(getPlugin()).sendToPlayer(player,formatText.getValue("text"));
                return true;
            }
            case TELLALL:{
                new Sender(getPlugin()).sendToAllPlayer(formatText.getValue("text"));
                return true;
            }
            case DELAY:{
                return TimeControl.delay(Long.parseLong(formatText.getValue("time")));
            }
            case MONEY:{
                if (VaultHook.isLoad()){
                    VaultHook.giveMoney(player, Double.parseDouble(formatText.getValue("value")));
                    return true;
                }else {
                    return false;
                }
            }
            case POINTS:{
                if (PlayerPointsHook.isLoad()){
                    return PlayerPointsHook.givePoints(player.getUniqueId(), Integer.parseInt(formatText.getValue("value")));
                }else {
                    return false;
                }
            }
            case PARTICLE:{
                List<String> locationList= ListUtil.getListFromFormatStr(formatText.getValue("location"));
                List<String> offsetList= ListUtil.getListFromFormatStr(formatText.getValue("offset"));
                String rgb1=formatText.getValue("rgb");
                String rgb2=formatText.getValue("rgbFrom");
                String rgb3=formatText.getValue("rgbTo");
                List<String> rgbList=null;
                List<String> rgbFromList=null;
                List<String> rgbToList=null;
                if (rgb1!=null){
                    rgbList= ListUtil.getListFromFormatStr(rgb1);
                }
                if (rgb2!=null){
                    rgbFromList= ListUtil.getListFromFormatStr(rgb2);
                }
                if (rgb3!=null){
                    rgbToList= ListUtil.getListFromFormatStr(rgb3);
                }
                String type=formatText.getValue("pType");
                int count= Integer.parseInt(formatText.getValue("count"));
                double offsetX= Double.parseDouble(offsetList.get(0));
                double offsetY= Double.parseDouble(offsetList.get(1));
                double offsetZ= Double.parseDouble(offsetList.get(2));
                double extra= Double.parseDouble(formatText.getValue("extra"));
                if (locationList.get(0).equalsIgnoreCase("self")){
                    locationList.set(0,player.getLocation().getWorld().getName());
                }else if (locationList.get(0).equalsIgnoreCase("target")){
                    Entity entity= (Entity) objects.get(0);
                    locationList.set(0,entity.getLocation().getWorld().getName());
                } if(locationList.get(0).contains("!")){
                    String world= locationList.get(0).replace("!","");
                    locationList.set(0,world);
                }else {
                    locationList.set(0,locationList.get(0));
                }

                if (locationList.get(1).equalsIgnoreCase("self")){
                    locationList.set(1,player.getLocation().getX()+"");
                }else if (locationList.get(1).equalsIgnoreCase("target")){
                    Entity entity= (Entity) objects.get(0);
                    locationList.set(1,entity.getLocation().getX()+"");
                }else if(locationList.get(1).contains("!")){
                    double x= Double.parseDouble(locationList.get(1).replace("!",""));
                    locationList.set(1,x+"");
                }else{
                    locationList.set(1,player.getLocation().getX()+Double.parseDouble(locationList.get(1))+"");
                }

                if (locationList.get(2).equalsIgnoreCase("self")){
                    locationList.set(2,player.getLocation().getY()+"");
                }else if (locationList.get(2).equalsIgnoreCase("target")){
                    Entity entity= (Entity) objects.get(0);
                    locationList.set(2,entity.getLocation().getY()+"");
                }else if(locationList.get(2).contains("!")){
                    double y= Double.parseDouble(locationList.get(2).replace("!",""));
                    locationList.set(2,y+"");
                }else {
                    locationList.set(2,player.getLocation().getY()+Double.parseDouble(locationList.get(2))+"");
                }

                if (locationList.get(3).equalsIgnoreCase("self")){
                    locationList.set(3,player.getLocation().getZ()+"");
                }else if (locationList.get(3).equalsIgnoreCase("target")){
                    Entity entity= (Entity) objects.get(0);
                    locationList.set(3,entity.getLocation().getZ()+"");
                }else if(locationList.get(3).contains("!")){
                    double z= Double.parseDouble(locationList.get(3).replace("!",""));
                    locationList.set(3,z+"");
                }else {
                    locationList.set(3,player.getLocation().getZ()+Double.parseDouble(locationList.get(3))+"");
                }

                Location location=new Location(Bukkit.getWorld(locationList.get(0)),Double.parseDouble(locationList.get(1)),Double.parseDouble(locationList.get(2)),Double.parseDouble(locationList.get(3)));
                ParticleGenerator particleGenerator=new ParticleGenerator(location,count,offsetX,offsetY,offsetZ,extra);
                if (rgbList!=null){
                    particleGenerator.setRedStoneData(Integer.parseInt(rgbList.get(0)),Integer.parseInt(rgbList.get(1)),Integer.parseInt(rgbList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                if (rgbFromList!=null&&rgbToList!=null){
                    particleGenerator.setDustColorTransition(
                            Integer.parseInt(rgbFromList.get(0))
                            ,Integer.parseInt(rgbFromList.get(1))
                            ,Integer.parseInt(rgbFromList.get(2))
                            ,Integer.parseInt(rgbToList.get(0))
                            ,Integer.parseInt(rgbToList.get(1))
                            ,Integer.parseInt(rgbToList.get(2))
                            ,Integer.parseInt(formatText.getValue("size")));
                }
                particleGenerator.generator(Particle.valueOf(type));
                if (objects!=null){
                    if (objects.size()>=1){
                        Entity entity= (Entity) objects.get(0);
                        if (entity.isDead()){
                            return false;
                        }
                    }
                }
                return true;
            }
            default:{
                return false;
            }
        }
    }
    public boolean viewExecute(Player player){
        switch (type){
            case SELF:
            case TELLALL:
            case TELL:
            case CONSOLE: {
                return true;
            }
            case DELAY:{
                return TimeControl.delay(Long.parseLong(formatText.getValue("time")));
            }
            case MONEY:{
                return VaultHook.isLoad();
            }
            case POINTS:{
                return PlayerPointsHook.isLoad();
            }
            default:{
                return false;
            }
        }
    }
    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public FormatText getFormatText() {
        return formatText;
    }

    public void setFormatText(FormatText formatText) {
        this.formatText = formatText;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }


}
