
package net.justmili.leftforgotten.block.building.cobble;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class CobblestoneSlab extends SlabBlock {
	public CobblestoneSlab() {
		super(Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f, 6f));
	}
}
