package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFResources;
import net.justmili.leftforgotten.init.LFSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @Unique private static final ResourceLocation STOMACH_GROWL = LeftForgotten.asPath("subtle_effects:entity.player.stomach_growl");
    @Unique private static final ResourceLocation HURT_VANILLA = SoundEvents.PLAYER_HURT.getLocation();
    @Unique private static final ResourceLocation HURT_FREEZE_VANILLA = SoundEvents.PLAYER_HURT_FREEZE.getLocation();
    @Unique private static final ResourceLocation HURT_FIRE_VANILLA = SoundEvents.PLAYER_HURT_ON_FIRE.getLocation();
    @Unique private static final ResourceLocation HURT_DROWN_VANILLA = SoundEvents.PLAYER_HURT_DROWN.getLocation();
    @Unique private static final ResourceLocation HURT_BERRY_VANILLA = SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH.getLocation();

    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void onPlay(SoundInstance sound, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (sound == null || mc.level == null) return;
        if (!mc.level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;

        ResourceLocation loc = sound.getLocation();

        // Cancel stomach growl
        if (STOMACH_GROWL.equals(loc)) {
            ci.cancel();
            return;
        }

        // Replace vanilla hurt with alpha hurt
        if (HURT_VANILLA.equals(loc)) {
            ci.cancel();
            ((SoundEngine)(Object)this).play(SimpleSoundInstance.forUI(LFSounds.HURT.get(), 1.0f));
            return;
        }
        // Play alpha hurt on top of other hurt sounds
        if (HURT_FREEZE_VANILLA.equals(loc)
            || HURT_FIRE_VANILLA.equals(loc)
            || HURT_DROWN_VANILLA.equals(loc)
            || HURT_BERRY_VANILLA.equals(loc)) {
            ((SoundEngine)(Object)this).play(SimpleSoundInstance.forUI(LFSounds.HURT.get(), 1.0f));
            // Don't cancel original sound
        }
    }
}
