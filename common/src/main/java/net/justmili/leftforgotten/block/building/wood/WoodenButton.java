
package net.justmili.leftforgotten.block.building.wood;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class WoodenButton extends ButtonBlock {
	public WoodenButton() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(0.5f).noOcclusion().isRedstoneConductor(CommonBlock::never), BlockSetType.OAK, 30, true);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

}
