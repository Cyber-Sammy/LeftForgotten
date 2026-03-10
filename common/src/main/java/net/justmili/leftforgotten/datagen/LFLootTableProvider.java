package net.justmili.leftforgotten.datagen;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.datagen.extensions.KnownBlocksLootProvider;
import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LFLootTableProvider extends LootTableProvider {
    public LFLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(LFBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class LFBlockLootProvider extends BlockLootSubProvider implements KnownBlocksLootProvider {
        protected LFBlockLootProvider() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        public void generate() {
            dropSelf(LFBlocks.RED_FLOWER.get());
            dropSelf(LFBlocks.YELLOW_FLOWER.get());
            add(LFBlocks.GRASS_BLOCK.get(), createSingleItemTableWithSilkTouch(LFBlocks.GRASS_BLOCK.get(), LFBlocks.DIRT.get()));
            dropSelf(LFBlocks.DIRT.get());
            add(LFBlocks.FARMLAND.get(), createSingleItemTableWithSilkTouch(LFBlocks.FARMLAND.get(), LFBlocks.DIRT.get()));
            add(LFBlocks.STONE.get(), createSingleItemTableWithSilkTouch(LFBlocks.STONE.get(), LFBlocks.COBBLESTONE.get()));
            dropSelf(LFBlocks.STONE_STAIRS.get());
            dropSelf(LFBlocks.STONE_SLAB.get());
            dropSelf(LFBlocks.STONE_PRESSURE_PLATE.get());
            dropSelf(LFBlocks.STONE_BUTTON.get());
            add(LFBlocks.GRAVEL.get(), createSilkTouchDispatchTable(LFBlocks.GRAVEL.get(),
                LootItem.lootTableItem(Items.FLINT)
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                    .otherwise(LootItem.lootTableItem(LFBlocks.GRAVEL.get()))));
            dropSelf(LFBlocks.SAND.get());
            add(LFBlocks.GLASS.get(), createSilkTouchOnlyTable(LFBlocks.GLASS.get()));
            add(LFBlocks.COAL_ORE.get(), createOreDrop(LFBlocks.COAL_ORE.get(), Items.COAL));
            dropSelf(LFBlocks.IRON_ORE.get());
            dropSelf(LFBlocks.GOLD_ORE.get());
            add(LFBlocks.REDSTONE_ORE.get(), createRedstoneOreDrops(LFBlocks.REDSTONE_ORE.get()));
            add(LFBlocks.DIAMOND_ORE.get(), createOreDrop(LFBlocks.DIAMOND_ORE.get(), Items.DIAMOND));
            dropSelf(LFBlocks.OBSIDIAN.get());
            dropSelf(LFBlocks.WOOD.get());
            dropSelf(LFBlocks.WOOD_6_SIDED.get());
            add(LFBlocks.LEAVES.get(), createLeavesDrops(LFBlocks.LEAVES.get(), LFBlocks.SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            dropSelf(LFBlocks.SAPLING.get());
            dropSelf(LFBlocks.WOODEN_PLANKS.get());
            dropSelf(LFBlocks.WOODEN_STAIRS.get());
            dropSelf(LFBlocks.WOODEN_SLAB.get());
            dropSelf(LFBlocks.FENCE.get());
            dropSelf(LFBlocks.FENCE_GATE.get());
            dropSelf(LFBlocks.TRAPDOOR.get());
            add(LFBlocks.DOOR.get(), createDoorTable(LFBlocks.DOOR.get()));
            dropSelf(LFBlocks.PRESSURE_PLATE.get());
            dropSelf(LFBlocks.BUTTON.get());
            dropSelf(LFBlocks.COBBLESTONE.get());
            dropSelf(LFBlocks.COBBLESTONE_STAIRS.get());
            dropSelf(LFBlocks.COBBLESTONE_SLAB.get());
            dropSelf(LFBlocks.COBBLESTONE_WALL.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_STAIRS.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_SLAB.get());
            dropSelf(LFBlocks.MOSSY_COBBLESTONE_WALL.get());
            add(LFBlocks.CLAY.get(), createSilkTouchDispatchTable(LFBlocks.CLAY.get(),
                LootItem.lootTableItem(LFItems.CLAY_BALL.get())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))));
            dropSelf(LFBlocks.BRICKS.get());
            dropSelf(LFBlocks.BRICK_STAIRS.get());
            dropSelf(LFBlocks.BRICK_SLAB.get());
            dropSelf(LFBlocks.BRICK_WALL.get());
            add(LFBlocks.BOOKSHELF.get(), createSilkTouchDispatchTable(LFBlocks.BOOKSHELF.get(),
                LootItem.lootTableItem(Items.BOOK)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))));
            dropSelf(LFBlocks.TNT.get());
            add(LFBlocks.IRON_DOOR.get(), createDoorTable(LFBlocks.IRON_DOOR.get()));
            add(LFBlocks.GLASS_PANE.get(), createSilkTouchOnlyTable(LFBlocks.GLASS_PANE.get()));


        }

        // this exact method exists on Forge, and is implemented via mixin by us on Fabric.
        // this makes it so any blocks in the environment that we don't datagen (e.g. Vanilla blocks) doesn't stop us from generating.
        // a similar thing needs to exist for any additional sub-providers later on.
        @Override
        public Stream<Block> getKnownBlocks() {
            return Streams.stream(LFBlocks.REGISTRY).map(Supplier::get);
        }
    }
}
