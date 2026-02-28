package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.mechanics.advancements.TakingInventory;
import net.justmili.leftforgotten.mixin.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.world.dimension.AlphaMinecraft;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);
        ClientTickEvent.CLIENT_POST.register(TakingInventory::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }
}
