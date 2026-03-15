package net.justmili.leftforgotten.mixin;

import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Works on Forge and Fabric, but because Forge does mixins in a weird way it doesn't work in Forge Dev Environment
@Mixin(Minecraft.class)
public abstract class BlockyLighting {
    @Inject(method = "useAmbientOcclusion", at = @At("HEAD"), cancellable = true)
    private static void blockyLighting(CallbackInfoReturnable<Boolean> cir) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null && mc.level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            cir.setReturnValue(false);
        }
    }
}