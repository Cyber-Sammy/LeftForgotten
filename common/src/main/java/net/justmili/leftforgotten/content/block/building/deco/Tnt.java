package net.justmili.leftforgotten.content.block.building.deco;

import net.justmili.leftforgotten.content.block.CommonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.material.MapColor;

public class Tnt extends TntBlock {
    public Tnt() {
        super(Properties.of().mapColor(MapColor.FIRE).instabreak().sound(SoundType.GRASS).ignitedByLava().isRedstoneConductor(CommonBlock::never));
    }
}
