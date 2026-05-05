package net.justmili.leftforgotten.core.datagen;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class LFItemTagProvider extends IntrinsicHolderTagsProvider<Item> {
    public static final TagKey<Item> FORGE_COBBLE = TagKey.create(Registries.ITEM, LeftForgotten.asForgeResource("cobblestone"));
    public static final TagKey<Item> FORGE_STONE = TagKey.create(Registries.ITEM, LeftForgotten.asForgeResource("stone"));

    public LFItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, item -> BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /**
         * Vanilla tags
         */
        this.tag(ItemTags.BOATS)
            .add(LFItems.BOAT.get());

        /**
         * Forge and Fabric tags
         */
        // FORGE
        this.tag(FORGE_COBBLE)
            .add(LFItems.COBBLESTONE.get());
        this.tag(FORGE_STONE)
            .add(LFItems.STONE.get());
        // FABRIC
        // idk does Fabric have additional tags like forge
    }
}
