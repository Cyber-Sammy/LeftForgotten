
package net.justmili.leftforgotten.block.building.stone;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CobblestoneWall extends WallBlock {
	public CobblestoneWall() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f, 6f).noOcclusion().isRedstoneConductor(CommonBlock::never).forceSolidOn());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}