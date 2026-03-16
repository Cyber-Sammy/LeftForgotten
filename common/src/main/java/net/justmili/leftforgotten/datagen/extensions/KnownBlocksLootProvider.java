package net.justmili.leftforgotten.datagen.extensions;

import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public interface KnownBlocksLootProvider {
    Iterable<Block> getKnownBlocks();
}
