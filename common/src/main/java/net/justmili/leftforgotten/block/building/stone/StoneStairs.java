
package net.justmili.leftforgotten.block.building.stone;

import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StoneStairs extends StairBlock {
	public StoneStairs() {
		super(LFBlocks.STONE.get().defaultBlockState(), Properties.copy(LFBlocks.STONE.get()));
	}

	@Override
	public float getExplosionResistance() {
		return 3f;
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

}
