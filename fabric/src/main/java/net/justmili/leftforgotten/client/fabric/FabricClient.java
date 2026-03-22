package net.justmili.leftforgotten.client.fabric;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.entity.renderer.LFBoatRenderer;
import net.justmili.leftforgotten.init.LFEntities;
import net.justmili.leftforgotten.init.LFResources;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Block;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block : LFResources.getBlocks()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        EntityRendererRegistry.register(LFEntities.BOAT, LFBoatRenderer::new);

        CommonClient.register();

        ModelLoadingPlugin.register(context -> {
            context.modifyModelAfterBake().register((model, ctx) -> {
                if (ctx.id() instanceof ModelResourceLocation resourceLocation && ctx.id().getNamespace().equals("minecraft") && !resourceLocation.getVariant().equals("inventory") && (ctx.id().getPath().equals("furnace") || ctx.id().getPath().equals("crafting_table"))) {
                    return new ClassicBlocksModel(model);
                }

                return model;
            });
        });
    }
}