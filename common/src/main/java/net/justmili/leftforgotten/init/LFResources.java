package net.justmili.leftforgotten.init;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@SuppressWarnings("NullableProblems")
public class LFResources {
    public static Block[] getBlocks() {
        return Streams.stream(LFBlocks.REGISTRY).map(Supplier::get).toArray(Block[]::new);
    }
    public static Block[] getTranslucentBlocks() {
        return new Block[]{
            LFBlocks.GLASS.get(),
            LFBlocks.GLASS_PANE.get(),
            LFBlocks.LEAVES.get(),
            LFBlocks.RED_FLOWER.get(),
            LFBlocks.YELLOW_FLOWER.get(),
            LFBlocks.SAPLING.get(),
            LFBlocks.TRAPDOOR.get(),
            LFBlocks.DOOR.get(),
            LFBlocks.IRON_DOOR.get()
        };
    }

    // ResourceKeys - Blocks
    public static final ResourceKey<Block> RED_FLOWER_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("red_flower"));
    public static final ResourceKey<Block> YELLOW_FLOWER_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("yellow_flower"));
    public static final ResourceKey<Block> GRASS_BLOCK_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("grass_block"));
    public static final ResourceKey<Block> DIRT_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("dirt"));
    public static final ResourceKey<Block> FARMLAND_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("farmland"));
    public static final ResourceKey<Block> STONE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("stone"));
    public static final ResourceKey<Block> STONE_STAIRS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("stone_stairs"));
    public static final ResourceKey<Block> STONE_SLAB_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("stone_slab"));
    public static final ResourceKey<Block> STONE_PRESSURE_PLATE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("stone_pressure_plate"));
    public static final ResourceKey<Block> STONE_BUTTON_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("stone_button"));
    public static final ResourceKey<Block> GRAVEL_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("gravel"));
    public static final ResourceKey<Block> SAND_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("sand"));
    public static final ResourceKey<Block> GLASS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("glass"));
    public static final ResourceKey<Block> COAL_ORE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("coal_ore"));
    public static final ResourceKey<Block> IRON_ORE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("iron_ore"));
    public static final ResourceKey<Block> GOLD_ORE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("gold_ore"));
    public static final ResourceKey<Block> REDSTONE_ORE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("redstone_ore"));
    public static final ResourceKey<Block> DIAMOND_ORE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("diamond_ore"));
    public static final ResourceKey<Block> OBSIDIAN_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("obsidian"));
    public static final ResourceKey<Block> WOOD_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("wood"));
    public static final ResourceKey<Block> WOOD_6_SIDED_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("wood_6_sided"));
    public static final ResourceKey<Block> LEAVES_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("leaves"));
    public static final ResourceKey<Block> SAPLING_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("sapling"));
    public static final ResourceKey<Block> WOODEN_PLANKS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("wooden_planks"));
    public static final ResourceKey<Block> WOODEN_STAIRS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("wooden_stairs"));
    public static final ResourceKey<Block> WOODEN_SLAB_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("wooden_slab"));
    public static final ResourceKey<Block> FENCE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("fence"));
    public static final ResourceKey<Block> FENCE_GATE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("fence_gate"));
    public static final ResourceKey<Block> TRAPDOOR_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("trapdoor"));
    public static final ResourceKey<Block> DOOR_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("door"));
    public static final ResourceKey<Block> PRESSURE_PLATE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("pressure_plate"));
    public static final ResourceKey<Block> BUTTON_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("button"));
    public static final ResourceKey<Block> COBBLESTONE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("cobblestone"));
    public static final ResourceKey<Block> COBBLESTONE_STAIRS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("cobblestone_stairs"));
    public static final ResourceKey<Block> COBBLESTONE_SLAB_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("cobblestone_slab"));
    public static final ResourceKey<Block> COBBLESTONE_WALL_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("cobblestone_wall"));
    public static final ResourceKey<Block> MOSSY_COBBLESTONE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("mossy_cobblestone"));
    public static final ResourceKey<Block> MOSSY_COBBLESTONE_STAIRS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("mossy_cobblestone_stairs"));
    public static final ResourceKey<Block> MOSSY_COBBLESTONE_SLAB_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("mossy_cobblestone_slab"));
    public static final ResourceKey<Block> MOSSY_COBBLESTONE_WALL_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("mossy_cobblestone_wall"));
    public static final ResourceKey<Block> CLAY_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("clay"));
    public static final ResourceKey<Block> BRICKS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("bricks"));
    public static final ResourceKey<Block> BRICK_STAIRS_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("brick_stairs"));
    public static final ResourceKey<Block> BRICK_SLAB_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("brick_slab"));
    public static final ResourceKey<Block> BRICK_WALL_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("brick_wall"));
    public static final ResourceKey<Block> BOOKSHELF_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("bookshelf"));
    public static final ResourceKey<Block> TNT_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("tnt"));
    public static final ResourceKey<Block> IRON_DOOR_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("iron_door"));
    public static final ResourceKey<Block> GLASS_PANE_BLOCK = ResourceKey.create(
        Registries.BLOCK, LeftForgotten.asResource("glass_pane"));

    // ResourceKeys - Items
    public static final ResourceKey<Item> RED_FLOWER_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("red_flower"));
    public static final ResourceKey<Item> YELLOW_FLOWER_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("yellow_flower"));
    public static final ResourceKey<Item> GRASS_BLOCK_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("grass_block"));
    public static final ResourceKey<Item> DIRT_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("dirt"));
    public static final ResourceKey<Item> FARMLAND_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("farmland"));
    public static final ResourceKey<Item> STONE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("stone"));
    public static final ResourceKey<Item> STONE_STAIRS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("stone_stairs"));
    public static final ResourceKey<Item> STONE_SLAB_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("stone_slab"));
    public static final ResourceKey<Item> STONE_PRESSURE_PLATE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("stone_pressure_plate"));
    public static final ResourceKey<Item> STONE_BUTTON_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("stone_button"));
    public static final ResourceKey<Item> GRAVEL_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("gravel"));
    public static final ResourceKey<Item> SAND_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("sand"));
    public static final ResourceKey<Item> GLASS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("glass"));
    public static final ResourceKey<Item> COAL_ORE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("coal_ore"));
    public static final ResourceKey<Item> IRON_ORE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("iron_ore"));
    public static final ResourceKey<Item> GOLD_ORE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("gold_ore"));
    public static final ResourceKey<Item> REDSTONE_ORE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("redstone_ore"));
    public static final ResourceKey<Item> DIAMOND_ORE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("diamond_ore"));
    public static final ResourceKey<Item> OBSIDIAN_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("obsidian"));
    public static final ResourceKey<Item> WOOD_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("wood"));
    public static final ResourceKey<Item> WOOD_6_SIDED_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("wood_6_sided"));
    public static final ResourceKey<Item> LEAVES_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("leaves"));
    public static final ResourceKey<Item> SAPLING_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("sapling"));
    public static final ResourceKey<Item> WOODEN_PLANKS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("wooden_planks"));
    public static final ResourceKey<Item> WOODEN_STAIRS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("wooden_stairs"));
    public static final ResourceKey<Item> WOODEN_SLAB_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("wooden_slab"));
    public static final ResourceKey<Item> FENCE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("fence"));
    public static final ResourceKey<Item> FENCE_GATE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("fence_gate"));
    public static final ResourceKey<Item> TRAPDOOR_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("trapdoor"));
    public static final ResourceKey<Item> DOOR_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("door"));
    public static final ResourceKey<Item> PRESSURE_PLATE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("pressure_plate"));
    public static final ResourceKey<Item> BUTTON_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("button"));
    public static final ResourceKey<Item> COBBLESTONE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("cobblestone"));
    public static final ResourceKey<Item> COBBLESTONE_STAIRS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("cobblestone_stairs"));
    public static final ResourceKey<Item> COBBLESTONE_SLAB_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("cobblestone_slab"));
    public static final ResourceKey<Item> COBBLESTONE_WALL_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("cobblestone_wall"));
    public static final ResourceKey<Item> MOSSY_COBBLESTONE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("mossy_cobblestone"));
    public static final ResourceKey<Item> MOSSY_COBBLESTONE_STAIRS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("mossy_cobblestone_stairs"));
    public static final ResourceKey<Item> MOSSY_COBBLESTONE_SLAB_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("mossy_cobblestone_slab"));
    public static final ResourceKey<Item> MOSSY_COBBLESTONE_WALL_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("mossy_cobblestone_wall"));
    public static final ResourceKey<Item> CLAY_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("clay"));
    public static final ResourceKey<Item> BRICKS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("bricks"));
    public static final ResourceKey<Item> BRICK_STAIRS_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("brick_stairs"));
    public static final ResourceKey<Item> BRICK_SLAB_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("brick_slab"));
    public static final ResourceKey<Item> BRICK_WALL_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("brick_wall"));
    public static final ResourceKey<Item> BOOKSHELF_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("bookshelf"));
    public static final ResourceKey<Item> TNT_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("tnt"));
    public static final ResourceKey<Item> IRON_DOOR_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("iron_door"));
    public static final ResourceKey<Item> GLASS_PANE_ITEM = ResourceKey.create(
        Registries.ITEM, LeftForgotten.asResource("glass_pane"));

    public static final class Levels {
        public static final ResourceKey<Level> ALPHA_MINECRAFT = ResourceKey.create(
            Registries.DIMENSION, LeftForgotten.asResource("alpha_minecraft"));
    }

    public static final class Tabs {
        public static final String creativeTabID = "left_forgotten";
        public static final String transKey = "left_forgotten.tab";
    }
}