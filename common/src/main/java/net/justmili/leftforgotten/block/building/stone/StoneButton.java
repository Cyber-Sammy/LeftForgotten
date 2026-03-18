
package net.justmili.leftforgotten.block.building.stone;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class StoneButton extends ButtonBlock {
	public StoneButton() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f).noOcclusion().isRedstoneConductor(CommonBlock::never), BlockSetType.STONE, 20, false);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

}
