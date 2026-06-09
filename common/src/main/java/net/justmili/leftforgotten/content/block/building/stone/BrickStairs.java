
package net.justmili.leftforgotten.content.block.building.stone;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BrickStairs extends StairBlock {
	public BrickStairs() {
		super(LFBlocks.BRICKS.get().defaultBlockState(), Properties.ofFullCopy(LFBlocks.BRICKS.get()));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}
}
