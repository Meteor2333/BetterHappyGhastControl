/*
 * This file is part of the BetterHappyGhastControl project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Meteor23333 and contributors
 *
 * BetterHappyGhastControl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BetterHappyGhastControl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with BetterHappyGhastControl.  If not, see <https://www.gnu.org/licenses/>.
 */

package cc.meteormc.bhgc.mixins;

import cc.meteormc.bhgc.BetterHappyGhastControl;
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
