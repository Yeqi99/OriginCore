package cn.originmc.plugins.origincore.hook;

import cn.originmc.plugins.origincore.OriginCore;
import cn.originmc.plugins.origincore.util.text.Sender;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.track.Track;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;

public class LuckPermsHook {
    private static boolean flag;
    private static LuckPerms api;
    public static void hook(JavaPlugin plugin) {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&a检查到LuckPerms，对应功能就绪");
            }
            setIsLoad(true);
        } else {
            if (OriginCore.getInstance().getConfig().getBoolean("hook-message")){
                new Sender(plugin).sendToLogger("&c没有找到LuckPerms，部分功能无效");
            }
            setIsLoad(false);
        }
    }
    public static boolean isLoad() {
        return flag;
    }

    public static void setIsLoad(boolean isLoad) {
        LuckPermsHook.flag = isLoad;
    }

    public static LuckPerms getApi() {
        return api;
    }

    public static void setApi(LuckPerms api) {
        LuckPermsHook.api = api;
    }
    public static User getUser(Player player){
        return getApi().getPlayerAdapter(Player.class).getUser(player);
    }
    public static Group getGroup(String groupName){
        return getApi().getGroupManager().getGroup(groupName);
    }
    public static Track getTrack(String name){
        return getApi().getTrackManager().getTrack(name);
    }
    public static void addPermission(String groupName,String permission){
        Group group=getGroup(groupName);
        group.data().add(Node.builder(permission).build());
        getApi().getGroupManager().saveGroup(group);
    }
    public static void addPermission(Player player,String permission){
        User user= getUser(player);
        user.data().add(Node.builder(permission).build());
        getApi().getUserManager().saveUser(user);
    }
    public static boolean hasPermission(String groupName,String permission){
        return getGroup(groupName).getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
    public static boolean hasPermission(Player player,String permission){
        return getUser(player).getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }
    public static String getPrefix(String groupName){
        return getGroup(groupName).getCachedData().getMetaData().getPrefix();
    }
    public static String getPrefix(Player player){
        return getUser(player).getCachedData().getMetaData().getPrefix();
    }
    public static String getSuffix(String groupName){
        return getGroup(groupName).getCachedData().getMetaData().getSuffix();
    }
    public static String getSuffix(Player player){
        return getUser(player).getCachedData().getMetaData().getSuffix();
    }
    public static String getMeta(String groupName,String key){
        return getGroup(groupName).getCachedData().getMetaData().getMetaValue(key);
    }
    public static String getMeta(Player player,String key){
        return getUser(player).getCachedData().getMetaData().getMetaValue(key);
    }
    public static  void setMeta(String groupName,String key,String value){
        Group group=getGroup(groupName);
        MetaNode node = MetaNode.builder(key, value).build();
        group.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals(key)));
        group.data().add(node);
        getApi().getGroupManager().saveGroup(group);
    }

    public static void setMeta(Player player,String key,String value){
        User user=getUser(player);
        MetaNode node=MetaNode.builder(key,value).build();
        user.data().clear(NodeType.META.predicate(mn -> mn.getMetaKey().equals(key)));
        user.data().add(node);
        getApi().getUserManager().saveUser(user);
    }
    public static List<String> getMetaKeyList(Player player){
        User user=getUser(player);
        List<String> returnList=new ArrayList<>();
        Collection<MetaNode> nodes=user.getNodes(NodeType.META);
        for (MetaNode node : nodes) {
            returnList.add(node.getMetaKey());
        }
        return returnList;
    }
    public static List<String> getMetaKeyList(String groupName){
        Group group=getGroup(groupName);
        List<String> returnList=new ArrayList<>();
        Collection<MetaNode> nodes=group.getNodes(NodeType.META);
        for (MetaNode node : nodes) {
            returnList.add(node.getMetaKey());
        }
        return returnList;
    }
    public static @NonNull OptionalInt getWeight(String groupName){
        return getGroup(groupName).getWeight();
    }
    public static List<String> getParentsList(String groupName){
        Group group=getGroup(groupName);
        List<String> returnList=new ArrayList<>();
        Collection<InheritanceNode> inheritanceNodes= group.getNodes(NodeType.INHERITANCE);
        for (InheritanceNode inheritanceNode : inheritanceNodes) {
            returnList.add(inheritanceNode.getGroupName());
        }
        return returnList;
    }
}
