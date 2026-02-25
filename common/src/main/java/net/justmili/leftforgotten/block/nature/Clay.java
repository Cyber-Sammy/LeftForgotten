package net.justmili.leftforgotten.block.nature;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class Clay extends Block {
    public Clay() {
        super(Properties.of().mapColor(MapColor.CLAY).sound(SoundType.GRAVEL).strength(0.6f, 0.6f));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }
}