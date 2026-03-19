package com.wildshitfixes.mixin.client;

import com.wildshitfixes.RenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    @Inject(method = "raycast", at = @At("HEAD"), cancellable = true)
    private static void disableEntityRaycast(
            Entity entity, Vec3d min, Vec3d max, Box box, Predicate<Entity> predicate, double maxDistance, CallbackInfoReturnable<EntityHitResult> cir
    ) {
        if (RenderState.hideAllEntities) {
            cir.setReturnValue(null);
        }
    }
}