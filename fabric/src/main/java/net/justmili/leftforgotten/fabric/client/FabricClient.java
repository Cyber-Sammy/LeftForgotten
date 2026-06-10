package net.justmili.leftforgotten.fabric.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.content.entity.renderer.LFBoatRenderer;
import net.justmili.leftforgotten.registries.LFEntities;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block block : LFResources.getBlocksFromRegistry()) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        EntityRendererRegistry.register(LFEntities.BOAT, LFBoatRenderer::new);

        CommonClient.register();

        ModelLoadingPlugin.register(context -> {
            context.modifyModelAfterBake().register((model, ctx) -> {
                if (CommonClient.shouldReplaceBakedModel(ctx.topLevelId())) {
                    return new ClassicBlocksModelFabric(model);
                }

                return model;
            });
        });
    }
}