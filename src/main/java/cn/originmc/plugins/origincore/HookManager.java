package cn.originmc.plugins.origincore;

import cn.originmc.plugins.origincore.hook.*;
import net.bytebuddy.asm.Advice;

public class HookManager {
    public static void hookAll(){
        ProtocolLibHook.hook(OriginCore.getInstance());
        PlaceholderAPIHook.hook(OriginCore.getInstance());
        VaultHook.hook(OriginCore.getInstance());
        PlayerPointsHook.hook(OriginCore.getInstance());
        NBTAPIHook.hook(OriginCore.getInstance());
        LuckPermsHook.hook(OriginCore.getInstance());
    }
}
