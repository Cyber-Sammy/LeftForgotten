package net.justmili.leftforgotten.datagen;

import com.google.common.collect.Streams;
import net.justmili.leftforgotten.datagen.extensions.KnownBlocksLootProvider;
import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LFLootTableProvider extends LootTableProvider {
    public LFLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(LeftForgottenBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }

    public static class LeftForgottenBlockLootProvider extends BlockLootSubProvider implements KnownBlocksLootProvider {
        protected LeftForgottenBlockLootProvider() {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS);
        }

        @Override
        public void generate() {

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
