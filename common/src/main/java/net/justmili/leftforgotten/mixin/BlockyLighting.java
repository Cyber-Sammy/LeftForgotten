package net.justmili.leftforgotten.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ModelBlockRenderer.class)
public abstract class BlockyLighting {

    @WrapOperation(method = "tesselateBlock", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/Minecraft;useAmbientOcclusion()Z"))
    private boolean blockyLighting(Operation<Boolean> original) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null && mc.player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            return false;
        }
        return original.call();
    }
}