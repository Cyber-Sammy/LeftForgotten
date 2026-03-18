package net.justmili.leftforgotten.mixin.client;

import net.justmili.leftforgotten.init.LFResources;
import net.justmili.leftforgotten.init.LFSounds;
import net.justmili.leftforgotten.mixin.accessors.TrackAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.SoundEvent;
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
//    @Shadow @Final private Minecraft minecraft;
//    @Unique
//    private static final Random RANDOM = new Random();
//    @Unique
//    private static List<SoundEvent> ALPHA_TRACKS = null;
//    @Unique
//    private static List<SoundEvent> getTracks() {
//        if (ALPHA_TRACKS == null) {
//            ALPHA_TRACKS = List.of(
//                LFSounds.MUSIC_13.get(),
//                LFSounds.MUSIC_BOO.get(),
//                LFSounds.MUSIC_CALM1.get(),
//                LFSounds.MUSIC_CALM2.get(),
//                LFSounds.MUSIC_CALM3.get(),
//                LFSounds.MUSIC_HAL1.get(),
//                LFSounds.MUSIC_HAL2.get(),
//                LFSounds.MUSIC_HAL3.get(),
//                LFSounds.MUSIC_HAL4.get(),
//                LFSounds.MUSIC_NUANCE1.get(),
//                LFSounds.MUSIC_NUANCE2.get(),
//                LFSounds.MUSIC_PIANO1.get(),
//                LFSounds.MUSIC_PIANO2.get(),
//                LFSounds.MUSIC_PIANO3.get(),
//                LFSounds.MUSIC_DROOPY_LIKES_YOUR_FACE.get()
//            );
//        }
//        return ALPHA_TRACKS;
//    }

//    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
//    private void onTick(CallbackInfo ci) {
//        if (minecraft.level == null) return;
//        if (!minecraft.level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;
//        TrackAccessor accessor = (TrackAccessor) this;
//        if (accessor.getCurrent() != null && minecraft.getSoundManager().isActive(accessor.getCurrent())) return;
//
//        if (accessor.getDelay() > 0) {
//            accessor.setDelay(accessor.getDelay() - 1);
//            ci.cancel();
//            return;
//        }
//
//        SoundEvent track = pickTrack();
//        SoundInstance instance = SimpleSoundInstance.forUI(track, 1.0f);
//        accessor.setCurrent(instance);
//        minecraft.getSoundManager().play(instance);
//        accessor.setDelay(Integer.MAX_VALUE);
//        ci.cancel();
//    }

//    private SoundEvent pickTrack() {
//        List<SoundEvent> tracks = getTracks();
//        while (true) {
//            SoundEvent track = tracks.get(RANDOM.nextInt(tracks.size()));
//            if (track == LFSounds.MUSIC_13.get() && RANDOM.nextFloat() >= 0.02f) continue;
//            if (track == LFSounds.MUSIC_DROOPY_LIKES_YOUR_FACE.get() && RANDOM.nextFloat() >= 0.20f) continue;
//            return track;
//        }
//    }
}