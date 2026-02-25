
package net.justmili.leftforgotten.block.building.stone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

public class StonePressurePlate extends PressurePlateBlock {
	public StonePressurePlate() {
		super(Sensitivity.MOBS, Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).forceSolidOn(), BlockSetType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

}
