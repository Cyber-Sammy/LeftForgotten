package net.justmili.leftforgotten.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PerlinNoise.class, priority = 2000)
public class Farlands {
    @ModifyReturnValue(method = "wrap(D)D", at = @At("RETURN"), remap = false)
    private static double replaceWrapReturn(double originalReturn, double input) {
        return input;
    }
}
