package net.justmili.leftforgotten.client.fabric;

import net.fabricmc.api.ClientModInitializer;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Later add BlockRenderLayerMap to cutout for all the blocks with translucent/transparent textures
    }
}
