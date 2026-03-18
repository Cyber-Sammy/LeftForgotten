package net.justmili.leftforgotten.block.dev;

import net.justmili.leftforgotten.block.CommonBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class RemodelFurnace extends FurnaceBlock {
    public RemodelFurnace() {
        super(Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(CommonBlock.litBlockEmission(13)));
    }
}
