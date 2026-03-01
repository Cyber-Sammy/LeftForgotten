package net.justmili.leftforgotten.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LFItems {
    public static final DeferredRegister<Item> REGISTRY =
        DeferredRegister.create(LeftForgotten.MOD_ID, Registries.ITEM);

    static {
        for (LFResources.BlockEntry entry : LFResources.BLOCK_ENTRIES) {
            RegistrySupplier<Block> blockSupplier = LFResources.block(entry.codeID());
            RegistrySupplier<Item> supplier = REGISTRY.register(
                entry.path(), () -> new BlockItem(blockSupplier.get(), new Item.Properties())
            );

            LFResources.registerItemSupplier(entry.codeID(), supplier);
        }
        for (LFResources.ItemEntry entry : LFResources.ITEM_ONLY_ENTRIES) {
            RegistrySupplier<Item> supplier = REGISTRY.register(entry.path(), entry.constructor());
            LFResources.registerItemSupplier(entry.codeID(), supplier);
        }
    }

    public static void register() {
        REGISTRY.register();
    }
}