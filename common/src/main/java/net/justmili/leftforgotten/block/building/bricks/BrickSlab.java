
package net.justmili.leftforgotten.block.building.bricks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class BrickSlab extends SlabBlock {
	public BrickSlab() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(0.6f));
	}
}
