
package net.justmili.leftforgotten.block.building.stone;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class StoneSlab extends SlabBlock {
	public StoneSlab() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(1.5f, 6f));
	}
}
