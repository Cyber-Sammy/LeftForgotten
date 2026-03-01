package net.justmili.leftforgotten.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class LFBlocks {
    public static final DeferredRegister<Block> REGISTRY =
        DeferredRegister.create(LeftForgotten.MOD_ID, Registries.BLOCK);

    static {
        for (LFResources.BlockEntry entry : LFResources.BLOCK_ENTRIES) {
            RegistrySupplier<Block> supplier = REGISTRY.register(entry.path(), entry.constructor());
            LFResources.registerBlockSupplier(entry.codeID(), supplier);
        }
    }

    public static RegistrySupplier<Block> GRASS_BLOCK() { return LFResources.block("GRASS_BLOCK"); }
    public static RegistrySupplier<Block> DIRT() { return LFResources.block("DIRT"); }
    public static RegistrySupplier<Block> FARMLAND() { return LFResources.block("FARMLAND"); }
    public static RegistrySupplier<Block> STONE() { return LFResources.block("STONE"); }
    public static RegistrySupplier<Block> WOOD() { return LFResources.block("WOOD"); }
    public static RegistrySupplier<Block> LEAVES() { return LFResources.block("LEAVES"); }

    public static void register() {
        REGISTRY.register();
    }
}