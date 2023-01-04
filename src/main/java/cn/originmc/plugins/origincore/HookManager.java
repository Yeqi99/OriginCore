package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.*;

public class HookManager {
    /**
     * 尝试与所有插件挂钩
     */
    public static void hookAll(){
        ProtocolLibHook.hook(OriginCore.getInstance());
        PlaceholderAPIHook.hook(OriginCore.getInstance());
        VaultHook.hook(OriginCore.getInstance());
        PlayerPointsHook.hook(OriginCore.getInstance());
        NBTAPIHook.hook(OriginCore.getInstance());
        LuckPermsHook.hook(OriginCore.getInstance());
    }
}
