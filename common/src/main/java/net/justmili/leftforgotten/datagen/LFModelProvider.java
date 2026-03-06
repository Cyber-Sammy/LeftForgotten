package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        createFlatItem(itemGen, LFItems.BRICK.get());
        createFlatItem(itemGen, LFItems.CLAY_BALL.get());
    }

    // Helper methods
    public static void createWoodFamily(BlockModelGenerators blockGen, Block block) {
        // Creates stairs, slabs, fences, fence gates, doors, trapdoors FROM planks
    }
    public static void createStoneFamily(BlockModelGenerators blockGen, Block block) {
        // Creates stairs, slabs and OPTIONALLY walls from a stone(-like) block
    }
    public static void createRedstoneFamily(BlockModelGenerators blockGen, Block block) {
        // Creates pressure plates and buttons
        // Both having the option to be disabled but one always has to exist in this method
    }
    public static void createCubeAll(BlockModelGenerators blockGen, Block block) {
        // Creates a basic all-faces one-texture cube block
    }
    public static void createCube(BlockModelGenerators blockGen, Block block) {
        // Creates a basic cube block with option for per-face textures and rotation.
        // Rotation options: No rot., S/W/N/E y-axis, D/U/N/S/W/E, log XYZ

        // Note - Also make: methods for just generating blockstates for rotational blocks with custom models
    }
    public static void createPlant(BlockModelGenerators blockGen, Block block) {
        // For stuff like plants like flowers, saplings, grass
    }
    public static void createTallPlant(BlockModelGenerators blockGen, Block block) {
        // For stuff like tall grass, tall ferns
    }
    public static void createCrop(BlockModelGenerators blockGen, Block block) {
        // For crops like carrots, potatoes and so on
    }

    public static void createFlatItem(ItemModelGenerators itemGen, Item item) {
        itemGen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
    public static void createFlatBlockItem(ItemModelGenerators itemGen, Block block) {
        itemGen.generateFlatItem(Item.BY_BLOCK.get(block), ModelTemplates.FLAT_ITEM);
    }
}
