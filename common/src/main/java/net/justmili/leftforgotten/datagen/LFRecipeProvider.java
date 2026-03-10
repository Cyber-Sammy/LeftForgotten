package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

import static net.justmili.leftforgotten.util.DatagenDataUtil.Recipes.*;

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
        Processing.smelt(writer, LFItems.COBBLESTONE.get(), LFItems.STONE.get(), 0.1f, 200);
        Building.stairs(writer, LFItems.STONE.get(), LFItems.STONE_STAIRS.get());
        Building.slab(writer, LFItems.STONE.get(), LFItems.STONE_SLAB.get());
        Redstone.pressurePlate(writer, LFItems.STONE.get(), LFItems.STONE_PRESSURE_PLATE.get());
        Redstone.button(writer, LFItems.STONE.get(), LFItems.STONE_BUTTON.get());

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
        twoByTwoPacker(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.BRICKS.get(), LFItems.BRICK.get());
        Building.stairs(writer, LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get());
        Building.slab(writer, LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get());
        Building.wall(writer, LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_STAIRS.get(), 1);
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_SLAB.get(), 2);
        Processing.cut(writer, LFItems.BRICKS.get(), LFItems.BRICK_WALL.get());

        // Clay & Brick item
        twoByTwoPacker(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.CLAY.get(), LFItems.CLAY_BALL.get());
        Processing.smelt(writer, LFItems.CLAY_BALL.get(), LFItems.BRICK.get(), 0.3f, 200);

        // Glass
        Processing.smelt(writer, LFItems.SAND.get(), LFItems.GLASS.get(), 0.1f, 200);
        Building.bars(writer, LFItems.GLASS.get(), LFItems.GLASS_PANE.get());

        // Bookshelf
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LFItems.BOOKSHELF.get())
            .define('#', LFItems.WOODEN_PLANKS.get())
            .define('B', net.minecraft.world.item.Items.BOOK)
            .pattern("###")
            .pattern("BBB")
            .pattern("###")
            .unlockedBy(getHasName(LFItems.WOODEN_PLANKS.get()), has(LFItems.WOODEN_PLANKS.get()))
            .save(writer);

        // TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, LFItems.TNT.get())
            .define('#', net.minecraft.world.item.Items.GUNPOWDER)
            .define('X', LFItems.SAND.get())
            .pattern("#X#")
            .pattern("X#X")
            .pattern("#X#")
            .unlockedBy(getHasName(LFItems.SAND.get()), has(LFItems.SAND.get()))
            .save(writer);

        // Wood 6-sided
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LFItems.WOOD_6_SIDED.get(), 3)
            .define('#', LFItems.WOOD.get())
            .pattern("##")
            .pattern("##")
            .unlockedBy(getHasName(LFItems.WOOD.get()), has(LFItems.WOOD.get()))
            .save(writer);
    }
}
