
package net.justmili.leftforgotten.block.building.wood;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class WoodenTrapdoor extends TrapDoorBlock {
	public WoodenTrapdoor() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(3f).noOcclusion().isRedstoneConductor(CommonBlock::never), BlockSetType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}
