package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.registries.LFResources;
import net.justmili.leftforgotten.registries.LFSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(MusicManager.class)
public class MusicPlayerMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow @Nullable private SoundInstance currentMusic;
    @Shadow private int nextSongDelay;

    @Shadow
    @Final
    private RandomSource random;
    @Unique
    private static final Random RANDOM = new Random();
    @Unique
    private static List<SoundEvent> ALPHA_TRACKS = null;
    @Unique
    private static List<SoundEvent> getTracks() {
        if (ALPHA_TRACKS == null) {
            ALPHA_TRACKS = List.of(
                LFSounds.MUSIC_13.get(),
                LFSounds.MUSIC_BOO.get(),
                LFSounds.MUSIC_CALM1.get(),
                LFSounds.MUSIC_CALM2.get(),
                LFSounds.MUSIC_CALM3.get(),
                LFSounds.MUSIC_HAL1.get(),
                LFSounds.MUSIC_HAL2.get(),
                LFSounds.MUSIC_HAL3.get(),
                LFSounds.MUSIC_HAL4.get(),
                LFSounds.MUSIC_NUANCE1.get(),
                LFSounds.MUSIC_NUANCE2.get(),
                LFSounds.MUSIC_PIANO1.get(),
                LFSounds.MUSIC_PIANO2.get(),
                LFSounds.MUSIC_PIANO3.get(),
                LFSounds.MUSIC_DROOPY_LIKES_YOUR_FACE.get()
            );
        }
        return ALPHA_TRACKS;
    }

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true, remap = false)
    private void onTick(CallbackInfo ci) {
        if (minecraft.level == null) return;
        if (!minecraft.level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;

        ci.cancel();

        if (this.currentMusic != null) {
            if (!this.minecraft.getSoundManager().isActive(this.currentMusic)) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay, Mth.nextInt(this.random, 6000, 24000));
            }
        }

        this.nextSongDelay = Math.min(this.nextSongDelay, 24000);
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            SoundEvent track = pickTrack();
            SoundInstance instance = SimpleSoundInstance.forMusic(track);
            this.currentMusic = instance;
            if (this.currentMusic.getSound() != SoundManager.EMPTY_SOUND) {
                minecraft.getSoundManager().play(instance);
            }
            this.nextSongDelay = Integer.MAX_VALUE;
        }
    }

    private SoundEvent pickTrack() {
        List<SoundEvent> tracks = getTracks();
        while (true) {
            SoundEvent track = tracks.get(RANDOM.nextInt(tracks.size()));
            if (track == LFSounds.MUSIC_13.get() && RANDOM.nextFloat() >= 0.02f) continue;
            if (track == LFSounds.MUSIC_DROOPY_LIKES_YOUR_FACE.get() && RANDOM.nextFloat() >= 0.20f) continue;
            return track;
        }
    }
}
