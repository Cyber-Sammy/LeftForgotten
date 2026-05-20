package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFItems;
import net.justmili.leftforgotten.core.util.DatagenDataUtil.Recipes.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class LFRecipeProvider extends RecipeProvider {
    public LFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> writer) {
        // Wood & Planks
        Building.planks(writer, LFItems.WOOD.get(), LFItems.WOODEN_PLANKS.get());
        Building.stairs(writer, LFItems.WOODEN_PLANKS.get(), LFItems.WOODEN_STAIRS.get());
        Building.slab(writer, LFItems.WOODEN_PLANKS.get(), LFItems.WOODEN_SLAB.get());
        Building.fence(writer, LFItems.WOODEN_PLANKS.get(), LFItems.FENCE.get());
        Building.fenceGate(writer, LFItems.WOODEN_PLANKS.get(), LFItems.FENCE_GATE.get());
        Building.door(writer, LFItems.WOODEN_PLANKS.get(), LFItems.DOOR.get());
        Building.trapdoor(writer, LFItems.WOODEN_PLANKS.get(), LFItems.TRAPDOOR.get());
        Redstone.pressurePlate(writer, LFItems.WOODEN_PLANKS.get(), LFItems.PRESSURE_PLATE.get());
        Redstone.button(writer, LFItems.WOODEN_PLANKS.get(), LFItems.BUTTON.get());

        // Stone
        Processing.smelt(writer, LFItems.COBBLESTONE.get(), LFItems.STONE.get(), 0.1f);
        Building.stairs(writer, LFItems.STONE.get(), LFItems.STONE_STAIRS.get());
        Building.slab(writer, LFItems.STONE.get(), LFItems.STONE_SLAB.get());
        Redstone.pressurePlate(writer, LFItems.STONE.get(), LFItems.STONE_PRESSURE_PLATE.get());
        Redstone.button(writer, LFItems.STONE.get(), LFItems.STONE_BUTTON.get());

        // Ores
        Processing.smelt(writer, LFItems.COAL_ORE.get(), Items.COAL, 0.1f);
        Processing.smelt(writer, LFItems.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        Processing.smelt(writer, LFItems.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        Processing.smelt(writer, LFItems.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        Processing.smelt(writer, LFItems.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);
        Processing.blast(writer, LFItems.COAL_ORE.get(), Items.COAL, 0.1f);
        Processing.blast(writer, LFItems.IRON_ORE.get(), Items.IRON_INGOT, 0.7f);
        Processing.blast(writer, LFItems.GOLD_ORE.get(), Items.GOLD_INGOT, 1.0f);
        Processing.blast(writer, LFItems.REDSTONE_ORE.get(), Items.REDSTONE, 0.7f);
        Processing.blast(writer, LFItems.DIAMOND_ORE.get(), Items.DIAMOND, 1.0f);

        // Cobblestone
        Building.stairs(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_STAIRS.get());
        Building.slab(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_SLAB.get());
        Building.wall(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_WALL.get());
        Processing.cut(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_STAIRS.get(), 1);
        Processing.cut(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_SLAB.get(), 2);
        Processing.cut(writer, LFItems.COBBLESTONE.get(), LFItems.COBBLESTONE_WALL.get());

        // Mossy Cobblestone
        Crafting.shapeless(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.MOSSY_COBBLESTONE.get(), LFItems.COBBLESTONE.get(), Items.VINE);
        Crafting.shapeless(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.MOSSY_COBBLESTONE.get(), LFItems.COBBLESTONE.get(), Items.MOSS_BLOCK);
        Building.stairs(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_STAIRS.get());
        Building.slab(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_SLAB.get());
        Building.wall(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_WALL.get());
        Processing.cut(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_STAIRS.get(), 1);
        Processing.cut(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_SLAB.get(), 2);
        Processing.cut(writer, LFItems.MOSSY_COBBLESTONE.get(), LFItems.MOSSY_COBBLESTONE_WALL.get());

        // Bricks
        Crafting.shaped2x2(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.BRICKS.get(), LFItems.BRICK.get(), 1);
        Building.stairs(writer, LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get());
        Building.slab(writer, LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get());
        Building.wall(writer, LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get(), 1);
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get(), 2);
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());

        // Clay & Brick item
        Crafting.shaped2x2(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.CLAY.get(), LFItems.CLAY_BALL.get(), 1);
        Processing.smelt(writer, LFItems.CLAY_BALL.get(), LFItems.BRICK.get(), 0.3f);

        // Glass
        Processing.smelt(writer, LFItems.SAND.get(), LFItems.GLASS.get(), 0.1f);
        Building.bars(writer, LFItems.GLASS.get(), LFItems.GLASS_PANE.get());

        // TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, LFItems.TNT.get())
            .define('#', Items.GUNPOWDER)
            .define('X', LFItems.SAND.get())
            .pattern("#X#")
            .pattern("X#X")
            .pattern("#X#")
            .unlockedBy(getHasName(LFItems.SAND.get()), has(LFItems.SAND.get()))
            .save(writer);

        // Wood 6-sided
        Crafting.shaped2x2(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.WOOD.get(), LFItems.WOOD_6_SIDED.get(), 3);

        // Furnace and Crafting Table
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.FURNACE, 1)
            .define('#', LFItems.COBBLESTONE.get())
            .pattern("###")
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(LFItems.COBBLESTONE.get()), has(LFItems.COBBLESTONE.get()))
            .save(writer, LeftForgotten.asResource("furnace"));

        // Resource Blocks
        Crafting.shaped3x3(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.IRON_BLOCK.get(), Items.IRON_INGOT, 1);
        Crafting.shaped3x3(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.GOLD_BLOCK.get(), Items.GOLD_INGOT, 1);
        Crafting.shaped3x3(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.DIAMOND_BLOCK.get(), Items.DIAMOND, 1);
        Crafting.shapeless(writer, RecipeCategory.MISC, Items.IRON_INGOT, 9, LFItems.IRON_BLOCK.get());
        Crafting.shapeless(writer, RecipeCategory.MISC, Items.GOLD_INGOT, 9, LFItems.GOLD_BLOCK.get());
        Crafting.shapeless(writer, RecipeCategory.MISC, Items.DIAMOND, 9, LFItems.DIAMOND_BLOCK.get());

        // Building / Iron
        Building.door(writer, LFItems.IRON_ORE.get(), LFItems.IRON_DOOR.get());

        // Boat
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, LFItems.BOAT.get())
            .define('#', LFItems.WOODEN_PLANKS.get())
            .pattern("# #")
            .pattern("###")
            .unlockedBy(getHasName(LFItems.WOODEN_PLANKS.get()), has(LFItems.WOODEN_PLANKS.get()))
            .save(writer, LeftForgotten.asResource("boat"));
    }
}
