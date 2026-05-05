package net.justmili.leftforgotten.registries;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@SuppressWarnings("NullableProblems")
public class LFResources {
    public static Block[] getBlocks() {
        return Streams.stream(LFBlocks.REGISTRY).map(Supplier::get).toArray(Block[]::new);
    }

    public static final class Levels {
        public static final ResourceKey<Level> ALPHA_MINECRAFT = ResourceKey.create(
            Registries.DIMENSION, LeftForgotten.asResource("alpha_minecraft"));
    }

    public static final class Tabs {
        public static final String creativeTabID = "left_forgotten";
        public static final String transKey = "left_forgotten.tab";
    }
}