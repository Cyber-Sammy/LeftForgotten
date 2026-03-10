package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class LFRecipeProvider extends RecipeProvider {
    public LFRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, LFItems.WOODEN_PLANKS.get(), 4)
            .requires(LFItems.WOOD.get())
            .unlockedBy("has_log", has(LFItems.WOOD.get()))
            .save(writer);

        // these two are the same, just left the bottom one here as an example.
        twoByTwoPacker(writer, RecipeCategory.BUILDING_BLOCKS, LFItems.BRICKS.get(), LFItems.BRICK.get());
        /*ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LFItems.BRICKS.get())
            .define('#', LFItems.BRICK.get())
            .pattern("##")
            .pattern("##")
            .unlockedBy("has_brick", has(LFItems.BRICK.get()))
            .save(writer);*/

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LFItems.CLAY_BALL.get()), RecipeCategory.BUILDING_BLOCKS, LFItems.BRICK.get(), 0.3f, 200)
            .unlockedBy("has_clay_ball", has(LFItems.CLAY_BALL.get()))
            .save(writer);
    }
}
