package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;

import static net.justmili.leftforgotten.util.DatagenAssetUtil.BlockModels.Families.*;
import static net.justmili.leftforgotten.util.DatagenAssetUtil.BlockModels.Individual.*;
import static net.justmili.leftforgotten.util.DatagenAssetUtil.ItemModels.createFlatItem;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output, LeftForgotten.MOD_ID);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        createCubeAll(blockGen, LFBlocks.BRITTLE_BEDROCK.get());
        createCubeAll(blockGen, LFBlocks.FEATURE_VOID.get());
        createPlant(blockGen, LFBlocks.RED_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        createPlant(blockGen, LFBlocks.YELLOW_FLOWER.get(), BlockModelGenerators.TintState.NOT_TINTED);
        createPlant(blockGen, LFBlocks.SAPLING.get(), BlockModelGenerators.TintState.NOT_TINTED);
        createCube(blockGen, LFBlocks.GRASS_BLOCK.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.DIRT.get()))
        );

        createCubeAll(blockGen, LFBlocks.DIRT.get());
        createFarmland(blockGen, LFBlocks.FARMLAND.get());
        createCube(blockGen, LFBlocks.WOOD.get(), RotationType.LOG_XYZ,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_top"))
        );
        createCube(blockGen, LFBlocks.WOOD_6_SIDED.get(), RotationType.NONE,
            ModelTemplates.CUBE_ALL,
            new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
        );

        createCube(blockGen, LFBlocks.LEAVES.get(), RotationType.NONE);
        createWoodFamily(blockGen,
            LFBlocks.WOODEN_PLANKS.get(), LFBlocks.WOODEN_STAIRS.get(), LFBlocks.WOODEN_SLAB.get(),
            LFBlocks.FENCE.get(), LFBlocks.FENCE_GATE.get(), LFBlocks.DOOR.get(), LFBlocks.TRAPDOOR.get());
        createRedstoneFamily(blockGen, LFBlocks.WOODEN_PLANKS.get(), LFBlocks.PRESSURE_PLATE.get(), LFBlocks.BUTTON.get());
        createStoneFamily(blockGen, LFBlocks.STONE.get(), LFBlocks.STONE_STAIRS.get(), LFBlocks.STONE_SLAB.get());
        createRedstoneFamily(blockGen, LFBlocks.STONE.get(), LFBlocks.STONE_PRESSURE_PLATE.get(), LFBlocks.STONE_BUTTON.get());
        createCubeAll(blockGen, LFBlocks.COAL_ORE.get());
        createCubeAll(blockGen, LFBlocks.IRON_ORE.get());
        createCubeAll(blockGen, LFBlocks.GOLD_ORE.get());
        createCubeAll(blockGen, LFBlocks.REDSTONE_ORE.get());
        createCubeAll(blockGen, LFBlocks.DIAMOND_ORE.get());
        createStoneFamily(blockGen, LFBlocks.COBBLESTONE.get(), LFBlocks.COBBLESTONE_STAIRS.get(), LFBlocks.COBBLESTONE_SLAB.get(), LFBlocks.COBBLESTONE_WALL.get());
        createStoneFamily(blockGen, LFBlocks.MOSSY_COBBLESTONE.get(), LFBlocks.MOSSY_COBBLESTONE_STAIRS.get(), LFBlocks.MOSSY_COBBLESTONE_SLAB.get(), LFBlocks.MOSSY_COBBLESTONE_WALL.get());
        createCubeAll(blockGen, LFBlocks.OBSIDIAN.get());
        createCubeAll(blockGen, LFBlocks.GRAVEL.get());
        createCubeAll(blockGen, LFBlocks.CLAY.get());
        createCubeAll(blockGen, LFBlocks.SAND.get());
        createGlassFamily(blockGen, LFBlocks.GLASS.get(), LFBlocks.GLASS_PANE.get());
        createStoneFamily(blockGen, LFBlocks.BRICKS.get(), LFBlocks.BRICK_STAIRS.get(), LFBlocks.BRICK_SLAB.get(), LFBlocks.BRICK_WALL.get());
        createCube(blockGen, LFBlocks.BOOKSHELF.get(), RotationType.NONE,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOODEN_PLANKS.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.BOOKSHELF.get()))
        );
        createCube(blockGen, LFBlocks.TNT.get(), RotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.TNT.get(), "_bottom"))
        );
        blockGen.createDoor(LFBlocks.IRON_DOOR.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemGen) {
        createFlatItem(itemGen, LFItems.BRICK.get());
        createFlatItem(itemGen, LFItems.CLAY_BALL.get());
    }
}
