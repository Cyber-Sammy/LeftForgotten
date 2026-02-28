
package net.justmili.leftforgotten.block.building.cobble;

import net.justmili.leftforgotten.block.Common;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class CobblestoneWall extends WallBlock {
	public CobblestoneWall() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f, 6f).noOcclusion().isRedstoneConductor(Common::never).forceSolidOn());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}