package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.entity.renderer.LFBoatRenderer;
import net.justmili.leftforgotten.init.LFEntities;
import net.justmili.leftforgotten.mixin.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.world.dimension.AlphaMinecraft;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);

        EntityRendererRegistry.register(LFEntities.BOAT, LFBoatRenderer::new);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }
}
