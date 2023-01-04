package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

public final class OriginCore extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;
    private static final String VERSION = "1.5";
    public static JavaPlugin getInstance() {
        return instance;
    }

    public static Sender getSender() {
        return sender;
    }

    @Override
    public void onEnable() {
        instance=this;
        sender=new Sender(this);
        saveDefaultConfig();
        HookManager.hookAll();
        getSender().sendOnEnableMsgToLogger("OriginCore","Yeqi",VERSION,"Dependency");
    }

    @Override
    public void onDisable() {
        getSender().sendOnDisableMsgToLogger("OriginCore","Yeqi",VERSION,"Dependency");
    }
}
