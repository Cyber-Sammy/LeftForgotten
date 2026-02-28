package net.justmili.leftforgotten.init;

import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import dev.architectury.registry.registries.RegistrySupplier;

import java.util.List;
import java.util.stream.Collectors;

public class LFResources {
    public static BlockRegistry getBlocks() {
        return BlockRegistry.INSTANCE;
    }
    public static ItemRegistry getItems() {
        return ItemRegistry.INSTANCE;
    }

    public static class BlockRegistry {
        static final BlockRegistry INSTANCE = new BlockRegistry();
        private final List<RegistrySupplier<Block>> all = List.of(
            LFBlocks.RED_FLOWER, LFBlocks.YELLOW_FLOWER,
            LFBlocks.GRASS_BLOCK, LFBlocks.DIRT, LFBlocks.FARMLAND,
            LFBlocks.STONE, LFBlocks.STONE_STAIRS, LFBlocks.STONE_SLAB, LFBlocks.STONE_PRESSURE_PLATE, LFBlocks.STONE_BUTTON,
            LFBlocks.GRAVEL, LFBlocks.SAND, LFBlocks.GLASS,
            LFBlocks.COAL_ORE, LFBlocks.IRON_ORE, LFBlocks.GOLD_ORE, LFBlocks.REDSTONE_ORE, LFBlocks.DIAMOND_ORE, LFBlocks.OBSIDIAN,
            LFBlocks.WOOD, LFBlocks.WOOD_6_SIDED, LFBlocks.LEAVES, LFBlocks.SAPLING,
            LFBlocks.WOODEN_PLANKS, LFBlocks.WOODEN_STAIRS, LFBlocks.WOODEN_SLAB, LFBlocks.FENCE, LFBlocks.FENCE_GATE, LFBlocks.TRAPDOOR, LFBlocks.DOOR, LFBlocks.PRESSURE_PLATE, LFBlocks.BUTTON,
            LFBlocks.COBBLESTONE, LFBlocks.COBBLESTONE_STAIRS, LFBlocks.COBBLESTONE_SLAB, LFBlocks.COBBLESTONE_WALL,
            LFBlocks.MOSSY_COBBLESTONE, LFBlocks.MOSSY_COBBLESTONE_STAIRS, LFBlocks.MOSSY_COBBLESTONE_SLAB, LFBlocks.MOSSY_COBBLESTONE_WALL,
            LFBlocks.CLAY, LFBlocks.BRICKS, LFBlocks.BRICK_STAIRS, LFBlocks.BRICK_SLAB, LFBlocks.BRICK_WALL,
            LFBlocks.BOOKSHELF, LFBlocks.TNT, LFBlocks.IRON_DOOR, LFBlocks.GLASS_PANE
        );

        public List<RegistrySupplier<Block>> suppliers() {
            return all;
        }
        public List<String> codeIDs() {
            return all.stream().map(s -> s.getId().getPath().toUpperCase().replace('-', '_')).collect(Collectors.toList());
        }
        public List<String> IDs() {
            return all.stream().map(s -> s.getId().getPath()).collect(Collectors.toList());
        }
        public List<String> inGameIDs() {
            return all.stream().map(s -> s.getId().toString()).collect(Collectors.toList());
        }
        public List<ResourceKey<Block>> resourceKeys() {
            return all.stream().map(s -> ResourceKey.create(Registries.BLOCK, s.getId())).collect(Collectors.toList());
        }
    }

    public static class ItemRegistry {
        static final ItemRegistry INSTANCE = new ItemRegistry();
        private final List<RegistrySupplier<Item>> all = List.of(
            LFItems.RED_FLOWER, LFItems.YELLOW_FLOWER,
            LFItems.GRASS_BLOCK, LFItems.DIRT, LFItems.FARMLAND,
            LFItems.STONE, LFItems.STONE_STAIRS, LFItems.STONE_SLAB, LFItems.STONE_PRESSURE_PLATE, LFItems.STONE_BUTTON,
            LFItems.GRAVEL, LFItems.SAND, LFItems.GLASS,
            LFItems.COAL_ORE, LFItems.IRON_ORE, LFItems.GOLD_ORE, LFItems.REDSTONE_ORE, LFItems.DIAMOND_ORE, LFItems.OBSIDIAN,
            LFItems.WOOD, LFItems.WOOD_6_SIDED, LFItems.LEAVES, LFItems.SAPLING,
            LFItems.WOODEN_PLANKS, LFItems.WOODEN_STAIRS, LFItems.WOODEN_SLAB, LFItems.FENCE, LFItems.FENCE_GATE, LFItems.TRAPDOOR, LFItems.DOOR, LFItems.PRESSURE_PLATE, LFItems.BUTTON,
            LFItems.COBBLESTONE, LFItems.COBBLESTONE_STAIRS, LFItems.COBBLESTONE_SLAB, LFItems.COBBLESTONE_WALL,
            LFItems.MOSSY_COBBLESTONE, LFItems.MOSSY_COBBLESTONE_STAIRS, LFItems.MOSSY_COBBLESTONE_SLAB, LFItems.MOSSY_COBBLESTONE_WALL,
            LFItems.CLAY, LFItems.BRICKS, LFItems.BRICK_STAIRS, LFItems.BRICK_SLAB, LFItems.BRICK_WALL,
            LFItems.BOOKSHELF, LFItems.TNT, LFItems.IRON_DOOR, LFItems.GLASS_PANE,
            LFItems.CLAY_BALL, LFItems.BRICK
        );

        public List<RegistrySupplier<Item>> suppliers() {
            return all;
        }
        public List<String> codeIDs() {
            return all.stream().map(s -> s.getId().getPath().toUpperCase().replace('-', '_')).collect(Collectors.toList());
        }
        public List<String> IDs() {
            return all.stream().map(s -> s.getId().getPath()).collect(Collectors.toList());
        }
        public List<String> inGameIDs() {
            return all.stream().map(s -> s.getId().toString()).collect(Collectors.toList());
        }
        public List<ResourceKey<Item>> resourceKeys() {
            return all.stream().map(s -> ResourceKey.create(Registries.ITEM, s.getId())).collect(Collectors.toList());
        }
    }

    public static final class getLevels {
        public static final ResourceKey<Level> ALPHA_MINECRAFT = ResourceKey.create(
            Registries.DIMENSION, LeftForgotten.asResource("alpha_minecraft"));
    }

    public static final class tabs {
        public static final String creativeTabID = "left_forgotten";
        public static final String transKey = "left_forgotten.tab";
    }
}