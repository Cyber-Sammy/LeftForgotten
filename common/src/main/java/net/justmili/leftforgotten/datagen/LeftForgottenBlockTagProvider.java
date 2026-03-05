package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class LeftForgottenBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public LeftForgottenBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BLOCK, lookupProvider, block -> BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.SMALL_FLOWERS)
            .add(LFBlocks.RED_FLOWER.get(), LFBlocks.YELLOW_FLOWER.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(LFBlocks.COBBLESTONE.get(), LFBlocks.COBBLESTONE_WALL.get(), LFBlocks.COBBLESTONE_STAIRS.get(),
                LFBlocks.COBBLESTONE_SLAB.get(),
                LFBlocks.STONE.get(), LFBlocks.STONE_SLAB.get(), LFBlocks.STONE_STAIRS.get(),
                LFBlocks.COAL_ORE.get(), LFBlocks.IRON_ORE.get(), LFBlocks.GOLD_ORE.get(), LFBlocks.DIAMOND_ORE.get(), LFBlocks.REDSTONE_ORE.get(),
                LFBlocks.OBSIDIAN.get(), LFBlocks.BRICKS.get(), LFBlocks.IRON_DOOR.get(),
                LFBlocks.MOSSY_COBBLESTONE.get(), LFBlocks.MOSSY_COBBLESTONE_STAIRS.get(), LFBlocks.MOSSY_COBBLESTONE_SLAB.get(), LFBlocks.MOSSY_COBBLESTONE_WALL.get(),
                LFBlocks.WOODEN_SLAB.get() // intentional, see: old slabs
            );

        this.tag(BlockTags.NEEDS_STONE_TOOL)
            .add(LFBlocks.IRON_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
            .add(LFBlocks.GOLD_ORE.get(), LFBlocks.DIAMOND_ORE.get(), LFBlocks.REDSTONE_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(LFBlocks.OBSIDIAN.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
            .add(LFBlocks.WOODEN_PLANKS.get(), LFBlocks.WOOD.get(), LFBlocks.WOOD_6_SIDED.get()
                // slabs are not included for the reason above, and most things did not break with an axe until Beta 1.9.
            );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(LFBlocks.GRASS_BLOCK.get(), LFBlocks.DIRT.get(), LFBlocks.FARMLAND.get(),
                LFBlocks.SAND.get(), LFBlocks.GRAVEL.get(), LFBlocks.CLAY.get());
    }
}
