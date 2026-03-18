package net.justmili.leftforgotten.block.nature;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BrownMushroom extends MushroomBlock {
    public BrownMushroom() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS)
                .hasPostProcess(CommonBlock::always)
                .pushReaction(PushReaction.DESTROY),
            TreeFeatures.HUGE_BROWN_MUSHROOM
        );
    }
}