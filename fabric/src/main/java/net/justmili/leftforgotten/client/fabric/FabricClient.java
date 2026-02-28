package net.justmili.leftforgotten.client.fabric;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (RegistrySupplier<Block> block : LFResources.getBlocks().suppliers()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.cutout());
        }
    }
}