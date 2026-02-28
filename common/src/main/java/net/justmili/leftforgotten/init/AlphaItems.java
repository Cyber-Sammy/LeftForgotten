package net.justmili.leftforgotten.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.item.Brick;
import net.justmili.leftforgotten.item.ClayBall;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AlphaItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(LeftForgotten.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> RED_FLOWER = block(AlphaBlocks.RED_FLOWER);
    public static final RegistrySupplier<Item> YELLOW_FLOWER = block(AlphaBlocks.YELLOW_FLOWER);
    public static final RegistrySupplier<Item> GRASS_BLOCK = block(AlphaBlocks.GRASS_BLOCK);
    public static final RegistrySupplier<Item> DIRT = block(AlphaBlocks.DIRT);
    public static final RegistrySupplier<Item> FARMLAND = block(AlphaBlocks.FARMLAND);
    public static final RegistrySupplier<Item> STONE = block(AlphaBlocks.STONE);
    public static final RegistrySupplier<Item> STONE_STAIRS = block(AlphaBlocks.STONE_STAIRS);
    public static final RegistrySupplier<Item> STONE_SLAB = block(AlphaBlocks.STONE_SLAB);
    public static final RegistrySupplier<Item> STONE_PRESSURE_PLATE = block(AlphaBlocks.STONE_PRESSURE_PLATE);
    public static final RegistrySupplier<Item> STONE_BUTTON = block(AlphaBlocks.STONE_BUTTON);
    public static final RegistrySupplier<Item> GRAVEL = block(AlphaBlocks.GRAVEL);
    public static final RegistrySupplier<Item> SAND = block(AlphaBlocks.SAND);
    public static final RegistrySupplier<Item> GLASS = block(AlphaBlocks.GLASS);
    public static final RegistrySupplier<Item> COAL_ORE = block(AlphaBlocks.COAL_ORE);
    public static final RegistrySupplier<Item> IRON_ORE = block(AlphaBlocks.IRON_ORE);
    public static final RegistrySupplier<Item> GOLD_ORE = block(AlphaBlocks.GOLD_ORE);
    public static final RegistrySupplier<Item> REDSTONE_ORE = block(AlphaBlocks.REDSTONE_ORE);
    public static final RegistrySupplier<Item> DIAMOND_ORE = block(AlphaBlocks.DIAMOND_ORE);
    public static final RegistrySupplier<Item> OBSIDIAN = block(AlphaBlocks.OBSIDIAN);
    public static final RegistrySupplier<Item> WOOD = block(AlphaBlocks.WOOD);
    public static final RegistrySupplier<Item> WOOD_6_SIDED = block(AlphaBlocks.WOOD_6_SIDED);
    public static final RegistrySupplier<Item> LEAVES = block(AlphaBlocks.LEAVES);
    public static final RegistrySupplier<Item> SAPLING = block(AlphaBlocks.SAPLING);
    public static final RegistrySupplier<Item> WOODEN_PLANKS = block(AlphaBlocks.WOODEN_PLANKS);
    public static final RegistrySupplier<Item> WOODEN_STAIRS = block(AlphaBlocks.WOODEN_STAIRS);
    public static final RegistrySupplier<Item> WOODEN_SLAB = block(AlphaBlocks.WOODEN_SLAB);
    public static final RegistrySupplier<Item> FENCE = block(AlphaBlocks.FENCE);
    public static final RegistrySupplier<Item> FENCE_GATE = block(AlphaBlocks.FENCE_GATE);
    public static final RegistrySupplier<Item> TRAPDOOR = block(AlphaBlocks.TRAPDOOR);
    public static final RegistrySupplier<Item> DOOR = doubleBlock(AlphaBlocks.DOOR);
    public static final RegistrySupplier<Item> PRESSURE_PLATE = block(AlphaBlocks.PRESSURE_PLATE);
    public static final RegistrySupplier<Item> BUTTON = block(AlphaBlocks.BUTTON);
    public static final RegistrySupplier<Item> COBBLESTONE = block(AlphaBlocks.COBBLESTONE);
    public static final RegistrySupplier<Item> COBBLESTONE_STAIRS = block(AlphaBlocks.COBBLESTONE_STAIRS);
    public static final RegistrySupplier<Item> COBBLESTONE_SLAB = block(AlphaBlocks.COBBLESTONE_SLAB);
    public static final RegistrySupplier<Item> COBBLESTONE_WALL = block(AlphaBlocks.COBBLESTONE_WALL);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE = block(AlphaBlocks.MOSSY_COBBLESTONE);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_STAIRS = block(AlphaBlocks.MOSSY_COBBLESTONE_STAIRS);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_SLAB = block(AlphaBlocks.MOSSY_COBBLESTONE_SLAB);
    public static final RegistrySupplier<Item> MOSSY_COBBLESTONE_WALL = block(AlphaBlocks.MOSSY_COBBLESTONE_WALL);
    public static final RegistrySupplier<Item> CLAY = block(AlphaBlocks.CLAY);
    public static final RegistrySupplier<Item> BRICKS = block(AlphaBlocks.BRICKS);
    public static final RegistrySupplier<Item> BOOKSHELF = block(AlphaBlocks.BOOKSHELF);
    public static final RegistrySupplier<Item> TNT = block(AlphaBlocks.TNT);
    public static final RegistrySupplier<Item> IRON_DOOR = doubleBlock(AlphaBlocks.IRON_DOOR);
    public static final RegistrySupplier<Item> GLASS_PANE = block(AlphaBlocks.GLASS_PANE);

    public static final RegistrySupplier<Item> CLAY_BALL = REGISTRY.register("clay_ball", ClayBall::new);
    public static final RegistrySupplier<Item> BRICK = REGISTRY.register("brick", Brick::new);

    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistrySupplier<Item> doubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        REGISTRY.register();
    }
}
