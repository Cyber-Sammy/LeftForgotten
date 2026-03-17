package net.justmili.leftforgotten.world.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class AlphaMinecraft extends DimensionSpecialEffects {
    public AlphaMinecraft() {
        super(Float.NaN, true, SkyType.NORMAL, false, false);
    }
    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(@NotNull Vec3 color, float sunHeight) {
        return color;
    }

    @Override
    public boolean isFoggyAt(int x, int y) {
        return false;
    }
}
