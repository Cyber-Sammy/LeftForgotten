package net.justmili.leftforgotten.init;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.block.building.Farmland;
import net.justmili.leftforgotten.block.building.Glass;
import net.justmili.leftforgotten.block.building.iron.IronDoor;
import net.justmili.leftforgotten.block.building.stone.*;
import net.justmili.leftforgotten.block.building.cobble.*;
import net.justmili.leftforgotten.block.building.wood.*;
import net.justmili.leftforgotten.block.building.*;
import net.justmili.leftforgotten.block.building.bricks.*;
import net.justmili.leftforgotten.block.nature.*;
import net.justmili.leftforgotten.block.stone.*;
import net.justmili.leftforgotten.block.wood.Wood;
import net.justmili.leftforgotten.block.wood.Wood6Sided;
import net.justmili.leftforgotten.block.wood.WoodenPlanks;
import net.justmili.leftforgotten.item.Brick;
import net.justmili.leftforgotten.item.ClayBall;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import dev.architectury.registry.registries.RegistrySupplier;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LFResources {
    public static final List<BlockEntry> BLOCK_ENTRIES = List.of(
        new BlockEntry("RED_FLOWER", "red_flower", RedFlower::new),
        new BlockEntry("YELLOW_FLOWER", "yellow_flower", YellowFlower::new),
        new BlockEntry("GRASS_BLOCK", "grass_block", GrassBlock::new),
        new BlockEntry("DIRT", "dirt", Dirt::new),
        new BlockEntry("FARMLAND", "farmland", Farmland::new),
        new BlockEntry("STONE", "stone", Stone::new),
        new BlockEntry("STONE_STAIRS", "stone_stairs", StoneStairs::new),
        new BlockEntry("STONE_SLAB", "stone_slab", StoneSlab::new),
        new BlockEntry("STONE_PRESSURE_PLATE", "stone_pressure_plate", StonePressurePlate::new),
        new BlockEntry("STONE_BUTTON", "stone_button", StoneButton::new),
        new BlockEntry("GRAVEL", "gravel", Gravel::new),
        new BlockEntry("SAND", "sand", Sand::new),
        new BlockEntry("GLASS", "glass", Glass::new),
        new BlockEntry("COAL_ORE", "coal_ore", CoalOre::new),
        new BlockEntry("IRON_ORE", "iron_ore", IronOre::new),
        new BlockEntry("GOLD_ORE", "gold_ore", GoldOre::new),
        new BlockEntry("REDSTONE_ORE", "redstone_ore", RedstoneOre::new),
        new BlockEntry("DIAMOND_ORE", "diamond_ore", DiamondOre::new),
        new BlockEntry("OBSIDIAN", "obsidian", Obsidian::new),
        new BlockEntry("WOOD", "wood", Wood::new),
        new BlockEntry("WOOD_6_SIDED", "wood_6_sided", Wood6Sided::new),
        new BlockEntry("LEAVES", "leaves", Leaves::new),
        new BlockEntry("SAPLING", "sapling", Sapling::new),
        new BlockEntry("WOODEN_PLANKS", "wooden_planks", WoodenPlanks::new),
        new BlockEntry("WOODEN_STAIRS", "wooden_stairs", WoodenStairs::new),
        new BlockEntry("WOODEN_SLAB", "wooden_slab", WoodenSlab::new),
        new BlockEntry("FENCE", "fence", WoodenFence::new),
        new BlockEntry("FENCE_GATE", "fence_gate", WoodenFenceGate::new),
        new BlockEntry("TRAPDOOR", "trapdoor", WoodenTrapdoor::new),
        new BlockEntry("DOOR", "door", WoodenDoor::new),
        new BlockEntry("PRESSURE_PLATE", "pressure_plate", WoodenPressurePlate::new),
        new BlockEntry("BUTTON", "button", WoodenButton::new),
        new BlockEntry("COBBLESTONE", "cobblestone", Cobblestone::new),
        new BlockEntry("COBBLESTONE_STAIRS", "cobblestone_stairs", CobblestoneStairs::new),
        new BlockEntry("COBBLESTONE_SLAB", "cobblestone_slab", CobblestoneSlab::new),
        new BlockEntry("COBBLESTONE_WALL", "cobblestone_wall", CobblestoneWall::new),
        new BlockEntry("MOSSY_COBBLESTONE", "mossy_cobblestone", MossyCobblestone::new),
        new BlockEntry("MOSSY_COBBLESTONE_STAIRS", "mossy_cobblestone_stairs", MossyCobblestoneStairs::new),
        new BlockEntry("MOSSY_COBBLESTONE_SLAB", "mossy_cobblestone_slab", MossyCobblestoneSlab::new),
        new BlockEntry("MOSSY_COBBLESTONE_WALL", "mossy_cobblestone_wall", MossyCobblestoneWall::new),
        new BlockEntry("CLAY", "clay", Clay::new),
        new BlockEntry("BRICKS", "bricks", Bricks::new),
        new BlockEntry("BRICK_STAIRS", "brick_stairs", BrickStairs::new),
        new BlockEntry("BRICK_SLAB", "brick_slab", BrickSlab::new),
        new BlockEntry("BRICK_WALL", "brick_wall", BrickWall::new),
        new BlockEntry("BOOKSHELF", "bookshelf", Bookshelf::new),
        new BlockEntry("TNT", "tnt", Tnt::new),
        new BlockEntry("IRON_DOOR", "iron_door", IronDoor::new),
        new BlockEntry("GLASS_PANE", "glass_pane", GlassPane::new)
    );
    public static final List<ItemEntry> ITEM_ENTRIES = List.of(
        new ItemEntry("CLAY_BALL", "clay_ball", ClayBall::new),
        new ItemEntry("BRICK", "brick", Brick::new)
    );

    public record BlockEntry(String codeID, String path, Supplier<Block> constructor) {
        public ResourceLocation asResource() {
            return LeftForgotten.asResource(path);
        }
        public ResourceKey<Block> resourceKey() {
            return ResourceKey.create(Registries.BLOCK, asResource());
        }
    }
    public record ItemEntry(String codeID, String path, Supplier<Item> constructor) {
        public ResourceLocation asResource() {
            return LeftForgotten.asResource(path);
        }
        public ResourceKey<Item> resourceKey() {
            return ResourceKey.create(Registries.ITEM, asResource());
        }
    }

    private static final Map<String, RegistrySupplier<Block>> BLOCK_SUPPLIERS = new LinkedHashMap<>();
    private static final Map<String, RegistrySupplier<Item>> ITEM_SUPPLIERS = new LinkedHashMap<>();

    public static void registerBlockSupplier(String codeID, RegistrySupplier<Block> supplier) {
        BLOCK_SUPPLIERS.put(codeID, supplier);
    }
    public static void registerItemSupplier(String codeID, RegistrySupplier<Item> supplier) {
        ITEM_SUPPLIERS.put(codeID, supplier);
    }

    public static RegistrySupplier<Block> block(String codeID) {
        return BLOCK_SUPPLIERS.get(codeID);
    }
    public static RegistrySupplier<Item> item(String codeID) {
        return ITEM_SUPPLIERS.get(codeID);
    }

    public static Collection<RegistrySupplier<Block>> getBlocks() {
        return BLOCK_SUPPLIERS.values();
    }
    public static Collection<RegistrySupplier<Item>> getBlockItems() {
        return ITEM_SUPPLIERS.entrySet().stream()
            .filter(entry -> BLOCK_SUPPLIERS.containsKey(entry.getKey()))
            .map(Map.Entry::getValue).collect(Collectors.toList());
    }
    public static Collection<RegistrySupplier<Item>> getItems() {
        return ITEM_SUPPLIERS.entrySet().stream()
            .filter(entry -> !BLOCK_SUPPLIERS.containsKey(entry.getKey()))
            .map(Map.Entry::getValue).collect(Collectors.toList());
    }
    public static Collection<RegistrySupplier<Item>> getAllItems() {
        return ITEM_SUPPLIERS.values();
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