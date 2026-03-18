package net.justmili.leftforgotten.block.building.wood;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WoodenDoor extends DoorBlock {
    public WoodenDoor() {
        super(Properties.copy(Blocks.OAK_DOOR).sound(SoundType.WOOD).strength(3f).noOcclusion().isRedstoneConductor(CommonBlock::never), BlockSetType.OAK);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }
}
