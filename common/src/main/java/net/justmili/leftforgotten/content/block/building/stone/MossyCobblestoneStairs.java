
package net.justmili.leftforgotten.content.block.building.stone;

import net.justmili.leftforgotten.registries.LFBlocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MossyCobblestoneStairs extends StairBlock {
	public MossyCobblestoneStairs() {
		super(LFBlocks.MOSSY_COBBLESTONE.get().defaultBlockState(), Properties.ofFullCopy(LFBlocks.MOSSY_COBBLESTONE.get()));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

}
