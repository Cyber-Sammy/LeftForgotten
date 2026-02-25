package net.justmili.leftforgotten.init;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class DimKeys {
    public static final ResourceKey<Level> ALPHA_MINECRAFT = ResourceKey.create(
        Registries.DIMENSION, LeftForgotten.asResource("alpha_minecraft")
    );
}
