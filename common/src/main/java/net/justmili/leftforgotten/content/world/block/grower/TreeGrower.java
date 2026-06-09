package net.justmili.leftforgotten.content.world.block.grower;

import net.justmili.leftforgotten.content.world.ConfiguredFeatures;

import java.util.Optional;

public final class TreeGrower {
    public static final net.minecraft.world.level.block.grower.TreeGrower INSTANCE =
        new net.minecraft.world.level.block.grower.TreeGrower(
            "left_forgotten",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ConfiguredFeatures.FANCY_TREE),
            Optional.of(ConfiguredFeatures.TREE),
            Optional.empty(),
            Optional.empty()
        );

    private TreeGrower() {
    }
}