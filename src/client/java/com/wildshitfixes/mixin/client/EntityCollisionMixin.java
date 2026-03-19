package com.wildshitfixes.mixin.client;

import com.wildshitfixes.RenderState;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityCollisionMixin {

    @Inject(method = "isCollidable", at = @At("HEAD"), cancellable = true)
    private void disableCollision(CallbackInfoReturnable<Boolean> cir) {

        if (RenderState.hideAllEntities) {
            cir.setReturnValue(false);
        }
    }
}