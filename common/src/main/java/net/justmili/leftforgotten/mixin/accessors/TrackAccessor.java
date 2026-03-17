package net.justmili.leftforgotten.mixin.accessors;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MusicManager.class)
public interface TrackAccessor {
    @Accessor("currentMusic")
    SoundInstance getCurrent();

    @Accessor("currentMusic")
    void setCurrent(SoundInstance music);

    @Accessor("nextSongDelay")
    int getDelay();

    @Accessor("nextSongDelay")
    void setDelay(int delay);
}
