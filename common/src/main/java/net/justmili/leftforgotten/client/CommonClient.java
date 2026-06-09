package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.mixin.accessors.DimSpecialEffectsAccessor;
import net.justmili.leftforgotten.client.dimension.AlphaMinecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);

        DimSpecialEffectsAccessor.getEffects().put(LeftForgotten.asResource("alpha_minecraft"), new AlphaMinecraft());
    }

    public static boolean shouldReplaceBakedModel(ModelResourceLocation modelLocation) {
        return modelLocation.id().getNamespace().equals("minecraft")
            && !modelLocation.getVariant().equals("inventory")
            && (modelLocation.id().getPath().equals("furnace")
            || modelLocation.id().getPath().equals("crafting_table"));
    }
}