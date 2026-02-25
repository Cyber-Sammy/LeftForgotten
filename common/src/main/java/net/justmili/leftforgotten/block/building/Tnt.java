package net.justmili.leftforgotten.block.building;

import net.justmili.leftforgotten.block.Common;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.material.MapColor;

public class Tnt extends TntBlock {
    public Tnt() {
        super(Properties.of().mapColor(MapColor.FIRE).instabreak().sound(SoundType.GRASS).ignitedByLava().isRedstoneConductor(Common::never));
    }
}
