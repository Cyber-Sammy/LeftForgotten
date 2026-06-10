package net.justmili.leftforgotten.neoforge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

@EventBusSubscriber(modid = LeftForgotten.MOD_ID, value = Dist.CLIENT)
public class VersionOverlay {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onClientTick(ClientTickEvent.Post event) {

        CommonVersionOverlay.onClientTick(Minecraft.getInstance());
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void render(RenderGuiEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (CommonVersionOverlay.inAlpha(minecraft)) return;

        CommonVersionOverlay.render(minecraft, event.getGuiGraphics());
    }
}