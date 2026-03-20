
package net.justmili.leftforgotten.block.building.stone;

import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BrickStairs extends StairBlock {
	public BrickStairs() {
		super(LFBlocks.BRICKS.get().defaultBlockState(), Properties.copy(LFBlocks.BRICKS.get()));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}
}
