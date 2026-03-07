package net.justmili.leftforgotten.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block : LFResources.getBlocks()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }
        CommonClient.register();
    }
}