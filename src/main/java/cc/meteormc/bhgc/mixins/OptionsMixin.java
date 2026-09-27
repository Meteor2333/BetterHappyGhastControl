package cc.meteormc.bhgc.mixins;

import cc.meteormc.bhgc.BetterHappyGhastControl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.Arrays;

@Mixin(Options.class)
@Environment(EnvType.CLIENT)
public abstract class OptionsMixin {
    @Final
    @Mutable
    @Shadow
    public KeyMapping[] keyMappings;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Options;load()V"
            )
    )
    private void onInit(Minecraft minecraft, File gameDirectory, CallbackInfo ci) {
        KeyMapping[] newKeys = Arrays.copyOf(keyMappings, keyMappings.length + 1);
        newKeys[keyMappings.length] = BetterHappyGhastControl.KEY_CONTROL_DOWN;
        keyMappings = newKeys;
    }
}
