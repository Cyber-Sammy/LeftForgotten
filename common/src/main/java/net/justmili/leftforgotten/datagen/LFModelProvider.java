package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.data.models.BlockModelGenerators.TintState;

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

    // To-do helper methods:
    //      Blocks
    // "createWoodFamily" - Planks -> Stairs, Slab, Fence, Fence Gate, Door, Trapdoor
    // "createStoneFamily" - Stone -> Stairs, Slab, Wall (toggle with bool cuz normal stone doesn't have it)
    // "createRedstoneFamily" - Planks/Stone -> Pressure Plate, Button
    // "createCubeAll" - Basic cube (parent: block/cube_all)
    // "createCube" - Basic cube but can put specific textures on a block face (parent: block/cube) + Rotation mode
    //                (No rot., S/W/N/E y-axis, D/U/N/S/W/E, log XYZ)
    // "createPlant" - Low flowers etc
    // "createTallPlant" - Tall flowers
    // "createCrossPlant" - I don't even know anymore
    //       Items
    // "createFlatItem" - Basic flat item
    // "createFlatBlockItem" - overwrites item model of blocks to be a flat item

    // Helper methods
    public static void createWoodFamily(BlockModelGenerators blockGen, Block block) {

    }
    public static void createStoneFamily(BlockModelGenerators blockGen, Block block) {

    }
    public static void createRedstoneFamily(BlockModelGenerators blockGen, Block block) {

    }
    public static void createCubeAll(BlockModelGenerators blockGen, Block block) {
        blockGen.createTrivialCube(block);
    }
    public static void createCube(BlockModelGenerators blockGen, Block block) {

    }
    /// What the fuck is TintState????? Why is it needed??
    public static void createPlant(BlockModelGenerators blockGen, Block block, Block pottedBlock, TintState tintState) {
        blockGen.createPlant();
    }
    public static void createTallPlant(BlockModelGenerators blockGen, Block block, Block pottedPlantBlock, TintState tintState) {
        blockGen.createDoublePlant(block, tintState);
    }
    public static void createCrossPlant(BlockModelGenerators blockGen, Block block, TintState tintState) {
        blockGen.createCrossBlock(block, tintState);
    }
    public static void createCrossPlant(BlockModelGenerators blockGen, Block block, TintState tintState, TextureMapping txtMapping) {
        blockGen.createCrossBlock(block, tintState, txtMapping);
    }
    public static void createCrossPlant(BlockModelGenerators blockGen, Block block, TintState tintState, TextureMapping txtMapping, Property<Integer> property, int... propertyValues) {
        blockGen.createCrossBlock(block, tintState, txtMapping, property, propertyValues);
    }

    public static void createFlatItem(ItemModelGenerators itemGen, Item item) {
        itemGen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
    public static void createFlatBlockItem(ItemModelGenerators itemGen, Block block) {
        itemGen.generateFlatItem(Item.BY_BLOCK.get(block), ModelTemplates.FLAT_ITEM);
    }
}
