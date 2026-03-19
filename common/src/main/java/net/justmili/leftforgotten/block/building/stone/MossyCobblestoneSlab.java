
package net.justmili.leftforgotten.block.building.stone;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MossyCobblestoneSlab extends SlabBlock {
	public MossyCobblestoneSlab() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f, 6f));
	}
}
