package com.telepathicgrunt.fullstackwatchdog.mixins;

import com.telepathicgrunt.fullstackwatchdog.FullStackWatchdog;
import net.minecraft.server.dedicated.ServerWatchdog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.lang.management.ThreadInfo;

@Mixin(ServerWatchdog.class)
public class ServerWatchdogMixin {

    @ModifyArg(method = "createWatchdogCrashReport",
            at = @At(value = "INVOKE",
                    target = "Ljava/lang/StringBuilder;append(Ljava/lang/Object;)Ljava/lang/StringBuilder;",
                    ordinal = 0,
                    remap = false)
    )
    private static Object fullstackwatchdog_printEntireThreadDump(Object object) {
        if (object instanceof ThreadInfo threadInfo) {
            return FullStackWatchdog.fullThreadInfoToString(threadInfo);
        }
        return object;
    }
}

