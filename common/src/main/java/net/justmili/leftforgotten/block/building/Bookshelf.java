package net.justmili.leftforgotten.block.building;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class Bookshelf extends Block {
    public Bookshelf() {
        super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.5f, 1.5f));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 15;
    }
}