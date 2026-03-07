package net.justmili.leftforgotten.mixin;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class NoStomachGrowlSFX {
    private static final ResourceLocation STOMACH_GROWL = LeftForgotten.asPath("subtle_effects:entity.player.stomach_growl");

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void onPlay(SoundInstance sound, CallbackInfo ci) {
        if (sound == null) return;
        if (STOMACH_GROWL.equals(sound.getLocation())) {
            ci.cancel();
        }
    }
}
