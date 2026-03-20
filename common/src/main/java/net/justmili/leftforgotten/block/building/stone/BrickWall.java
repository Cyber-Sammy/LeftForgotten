
package net.justmili.leftforgotten.block.building.stone;

import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BrickWall extends WallBlock {
	public BrickWall() {
		super(Properties.copy(LFBlocks.BRICKS.get()).forceSolidOn());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}