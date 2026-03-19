package net.justmili.leftforgotten.block.nature.vegetation;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class RedMushroom extends MushroomBlock {
    public RedMushroom() {
        super(Properties.of()
            .mapColor(MapColor.COLOR_RED)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.GRASS)
            .hasPostProcess(CommonBlock::always)
            .pushReaction(PushReaction.DESTROY),
            TreeFeatures.HUGE_RED_MUSHROOM
        );
    }
}