package net.justmili.leftforgotten.block.nature;

import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class Cactus extends CactusBlock {
    public Cactus() {
        super(Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.4F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));
    }
}
