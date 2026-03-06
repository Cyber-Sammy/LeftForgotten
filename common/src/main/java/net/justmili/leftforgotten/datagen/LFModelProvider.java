package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {

    }

    // To-do helper methods:
    //      Blocks
    // "createWoodFamily" - Planks -> Stairs, Slab, Fence, Fence Gate, Door, Trapdoor
    // "createStoneFamily" - Stone -> Stairs, Slab, Wall (toggle with bool cuz normal stone doesn't have it)
    // "createRedstoneFamily" - Planks/Stone -> Pressure Plate, Button
    // "createCubeAll" - Basic cube (parent: block/cube_all) + Rotation mode
    //                 (No rot., S/W/N/E y-axis, D/U/N/S/W/E, log XYZ)
    // "createCube" - Basic cube but can put specific textures on a block face (parent: block/cube)
    //       Items
    // "createFlatItem" - Basic flat item
    // "createFlatBlockItem" - overwrites item model of blocks to be a flat item
}
