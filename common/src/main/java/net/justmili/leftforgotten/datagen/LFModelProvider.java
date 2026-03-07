package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

public class LFModelProvider extends ImprovedModelProvider {
    public LFModelProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockGen) {
        createPlant(blockGen, LFBlocks.RED_FLOWER.get());
        createPlant(blockGen, LFBlocks.YELLOW_FLOWER.get());
        createPlant(blockGen, LFBlocks.SAPLING.get());
        createCube(blockGen, LFBlocks.GRASS_BLOCK.get(), CubeRotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.GRASS_BLOCK.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.DIRT.get()))
        );

        createCubeAll(blockGen, LFBlocks.DIRT.get());
        // TODO: Fix farmland model bcuz it's using a generic cube model instead of the lower custom model that is like 15px tall
        createCube(blockGen, LFBlocks.FARMLAND.get(), CubeRotationType.NONE,
            ModelTemplates.CUBE_BOTTOM_TOP,
            new TextureMapping()
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(LFBlocks.FARMLAND.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.DIRT.get()))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(LFBlocks.DIRT.get()))
        );
        createCube(blockGen, LFBlocks.WOOD.get(), CubeRotationType.LOG_XYZ,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_top"))
        );
        createCube(blockGen, LFBlocks.WOOD_6_SIDED.get(), CubeRotationType.NONE,
            ModelTemplates.CUBE_ALL,
            new TextureMapping()
                .put(TextureSlot.ALL, TextureMapping.getBlockTexture(LFBlocks.WOOD.get(), "_side"))
        );

        createCube(blockGen, LFBlocks.LEAVES.get(), CubeRotationType.NONE);
        // TODO: Fix Issue #5 listen in changelog-todo
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
        createCubeAll(blockGen, LFBlocks.GLASS.get());
        // TODO: GLASS_PANE - I haven't made the method for pane/bars-like models
        createStoneFamily(blockGen, LFBlocks.BRICKS.get(), LFBlocks.BRICK_STAIRS.get(), LFBlocks.BRICK_SLAB.get(), LFBlocks.BRICK_WALL.get());
        createCube(blockGen, LFBlocks.BOOKSHELF.get(), CubeRotationType.NONE,
            ModelTemplates.CUBE_COLUMN,
            new TextureMapping()
                .put(TextureSlot.END, TextureMapping.getBlockTexture(LFBlocks.WOODEN_PLANKS.get()))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(LFBlocks.BOOKSHELF.get()))
        );
        createCube(blockGen, LFBlocks.TNT.get(), CubeRotationType.NONE,
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

    public static void createWoodFamily(BlockModelGenerators blockGen, Block planks, Block stairs, Block slab,
                                        Block fence, Block fenceGate, Block door, Block trapdoor) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(planks);
        TextureMapping mapping = texturedModel.getMapping();

        ResourceLocation fullBlock = texturedModel.create(planks, blockGen.modelOutput);

        // Stairs
        ResourceLocation stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        // Slab
        ResourceLocation slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        ResourceLocation slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);

        // Fence
        ResourceLocation fencePost = ModelTemplates.FENCE_POST.create(fence, mapping, blockGen.modelOutput);
        ResourceLocation fenceSide = ModelTemplates.FENCE_SIDE.create(fence, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFence(fence, fencePost, fenceSide));
        blockGen.delegateItemModel(fence, ModelTemplates.FENCE_INVENTORY.create(fence, mapping, blockGen.modelOutput));

        // Fence gate
        ResourceLocation fgOpen = ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgClosed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgWallOpen = ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, mapping, blockGen.modelOutput);
        ResourceLocation fgWallClosed = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGate, fgOpen, fgClosed, fgWallOpen, fgWallClosed, true));

        // Door & Trapdoor
        blockGen.createDoor(door);
        blockGen.createOrientableTrapdoor(trapdoor);
    }
    public static void createStoneFamily(BlockModelGenerators blockGen, Block block, Block stairs, Block slab) {
        TexturedModel texturedModel = TexturedModel.CUBE.get(block);
        TextureMapping mapping = texturedModel.getMapping();

        ResourceLocation fullBlock = texturedModel.create(block, blockGen.modelOutput);

        ResourceLocation stairsInner = ModelTemplates.STAIRS_INNER.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsStraight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, blockGen.modelOutput);
        ResourceLocation stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairs, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, stairsInner, stairsStraight, stairsOuter));
        blockGen.delegateItemModel(stairs, stairsStraight);

        ResourceLocation slabBottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, blockGen.modelOutput);
        ResourceLocation slabTop = ModelTemplates.SLAB_TOP.create(slab, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabBottom, slabTop, fullBlock));
        blockGen.delegateItemModel(slab, slabBottom);
    }
    public static void createStoneFamily(BlockModelGenerators blockGen, Block block, Block stairs, Block slab, Block wall) {
        createStoneFamily(blockGen, block, stairs, slab);

        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();
        ResourceLocation wallPost = ModelTemplates.WALL_POST.create(wall, mapping, blockGen.modelOutput);
        ResourceLocation wallLow = ModelTemplates.WALL_LOW_SIDE.create(wall, mapping, blockGen.modelOutput);
        ResourceLocation wallTall = ModelTemplates.WALL_TALL_SIDE.create(wall, mapping, blockGen.modelOutput);
        blockGen.blockStateOutput.accept(BlockModelGenerators.createWall(wall, wallPost, wallLow, wallTall));
        blockGen.delegateItemModel(wall, ModelTemplates.WALL_INVENTORY.create(wall, mapping, blockGen.modelOutput));
    }
    public static void createRedstoneFamily(BlockModelGenerators blockGen, Block block, Block pressurePlate, Block button) {
        TextureMapping mapping = TexturedModel.CUBE.get(block).getMapping();

        if (pressurePlate != null) {
            ResourceLocation ppUp = ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, mapping, blockGen.modelOutput);
            ResourceLocation ppDown = ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, ppUp, ppDown));
        }

        if (button != null) {
            ResourceLocation btn = ModelTemplates.BUTTON.create(button, mapping, blockGen.modelOutput);
            ResourceLocation btnPressed = ModelTemplates.BUTTON_PRESSED.create(button, mapping, blockGen.modelOutput);
            blockGen.blockStateOutput.accept(BlockModelGenerators.createButton(button, btn, btnPressed));
            blockGen.delegateItemModel(button, ModelTemplates.BUTTON_INVENTORY.create(button, mapping, blockGen.modelOutput));
        }
    }

    public static void createCubeAll(BlockModelGenerators blockGen, Block block) {
        blockGen.createTrivialCube(block);
    }
    public static void createCube(BlockModelGenerators blockGen, Block block, CubeRotationType rotationType) {
        createCube(blockGen, block, rotationType, ModelTemplates.CUBE_ALL, TextureMapping.cube(block));
    }
    public static void createCube(BlockModelGenerators blockGen, Block block, CubeRotationType rotationType,
                                  ModelTemplate template, TextureMapping textureMapping) {
        ResourceLocation model = template.create(block, textureMapping, blockGen.modelOutput);
        switch (rotationType) {
            case NONE ->
                blockGen.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
            case HORIZONTAL_Y ->
                blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, model))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch()));
            case ALL_DIRECTIONS ->
                blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, model))
                    .with(BlockModelGenerators.createFacingDispatch()));
            case LOG_XYZ ->
                blockGen.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, model));
        }
    }
    public enum CubeRotationType {
        NONE,
        HORIZONTAL_Y, // S/W/N/E y-axis
        ALL_DIRECTIONS, // D/U/N/S/W/E
        LOG_XYZ // Log XYZ axis
    }

    public static void createHorizontalRotatedBlockState(BlockModelGenerators blockGen, Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, modelLocation))
            .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }
    public static void createFullRotatedBlockState(BlockModelGenerators blockGen, Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                Variant.variant().with(VariantProperties.MODEL, modelLocation))
            .with(BlockModelGenerators.createFacingDispatch()));
    }
    public static void createAxisAlignedBlockState(BlockModelGenerators blockGen, Block block, ResourceLocation modelLocation) {
        blockGen.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, modelLocation));
    }

    public static void createPlant(BlockModelGenerators blockGen, Block block) {
        blockGen.createCrossBlockWithDefaultItem(block, BlockModelGenerators.TintState.NOT_TINTED);
    }
    public static void createTallPlant(BlockModelGenerators blockGen, Block block) {
        blockGen.createDoublePlant(block, BlockModelGenerators.TintState.NOT_TINTED);
    }
    public static void createCrop(BlockModelGenerators blockGen, Block block,
                                  Property<Integer> ageProperty, int... ageToVisualStageMapping) {
        blockGen.createCropBlock(block, ageProperty, ageToVisualStageMapping);
    }

    public static void createFlatItem(ItemModelGenerators itemGen, Item item) {
        itemGen.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
    public static void createFlatBlockItem(ItemModelGenerators itemGen, Block block) {
        itemGen.generateFlatItem(Item.BY_BLOCK.get(block), ModelTemplates.FLAT_ITEM);
    }
}
