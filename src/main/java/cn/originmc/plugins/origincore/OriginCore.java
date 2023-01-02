package cn.originmc.plugins.origincore;

import org.bukkit.plugin.java.JavaPlugin;

public final class OriginCore extends JavaPlugin {
    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance=this;
        HookManager.hookAll();
    }

    @Override
    public void onDisable() {

    }
}
