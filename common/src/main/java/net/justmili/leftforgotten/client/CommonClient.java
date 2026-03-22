package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.world.dimension.AlphaMinecraft;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ResourceLocation id) {
        return id instanceof ModelResourceLocation resourceLocation && id.getNamespace().equals("minecraft") && !resourceLocation.getVariant().equals("inventory") &&
            (id.getPath().equals("furnace") || id.getPath().equals("crafting_table"));
    }
}
