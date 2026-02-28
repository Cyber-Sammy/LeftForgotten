package net.justmili.leftforgotten.block.building.bricks;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class Bricks extends Block {
    public Bricks() {
        super(Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRAVEL).strength(0.6f));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }
}