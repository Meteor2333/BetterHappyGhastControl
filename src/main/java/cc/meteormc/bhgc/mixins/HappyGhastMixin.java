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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends LivingEntity {
    protected HappyGhastMixin(EntityType<? extends HappyGhast> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
            method = "getRiddenInput",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetRiddenInput(Player player, Vec3 travelVector, CallbackInfoReturnable<Vec3> cir) {
        float up = 0f;
        if (player.isJumping()) {
            up += 0.5f;
        }

        if (BetterHappyGhastControl.KEY_CONTROL_DOWN.isDown()) {
            up -= 0.5f;
        }

        double speed = this.getAttributeValue(Attributes.FLYING_SPEED);
        Vec3 vec3 = new Vec3(player.xxa, up, player.zza).scale(3.9F * speed);
        cir.setReturnValue(vec3);
    }
}
