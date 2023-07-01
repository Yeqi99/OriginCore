package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.listener.cooldown.CoolDownListener;
import cn.originmc.plugins.origincore.listener.gui.listener.GuiListener;
import cn.originmc.plugins.origincore.util.register.CommandRegister;
import cn.originmc.plugins.origincore.util.register.ListenerRegister;
import cn.originmc.plugins.origincore.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

public final class OriginCore extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;
    private static final String VERSION = "2.40";
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
        HookManager.hookAll(this);
        CommandRegister.register(this,new OriginCoreCommand(),"OriginCore");
        if (getConfig().getBoolean("cool-down-listener.enable")){
            CoolDownListener.setEnable(true);
            CoolDownListener.listen();
        }
        if (getConfig().getBoolean("gui-listener.enable")){
            ListenerRegister.register(this,new GuiListener());
        }
        getSender().sendOnEnableMsgToLogger("OriginCore","Yeqi",VERSION,"Dependency");
    }

    @Override
    public void onDisable() {
        getSender().sendOnDisableMsgToLogger("OriginCore","Yeqi",VERSION,"Dependency");
    }
}
