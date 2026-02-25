
package net.justmili.leftforgotten.block.building.stone;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class StoneStairs extends StairBlock {
	public StoneStairs() {
		super(Blocks.STONE_STAIRS.defaultBlockState(), Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f));
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
