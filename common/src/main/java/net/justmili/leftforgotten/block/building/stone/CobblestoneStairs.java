
package net.justmili.leftforgotten.block.building.stone;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CobblestoneStairs extends StairBlock {
	public CobblestoneStairs() {
		super(Blocks.COBBLESTONE_STAIRS.defaultBlockState(), Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2f, 3f));
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return false;
	}

}
