package net.justmili.leftforgotten.world.tree;

import net.justmili.leftforgotten.world.ConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class TreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean p_222911_) {
        if (random.nextDouble() > 0.9) {
            return ConfiguredFeatures.TREE;
        } else {
            return ConfiguredFeatures.FANCY_TREE;
        }
    }
}
