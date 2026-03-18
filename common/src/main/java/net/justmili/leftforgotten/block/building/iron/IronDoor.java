package net.justmili.leftforgotten.block.building.iron;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class IronDoor extends DoorBlock {
    public IronDoor() {
        super(Properties.copy(Blocks.IRON_DOOR).sound(SoundType.WOOD).strength(3f).noOcclusion().isRedstoneConductor(CommonBlock::never), BlockSetType.IRON);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }
}
