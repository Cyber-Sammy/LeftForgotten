package net.justmili.leftforgotten.forge.client;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LeftForgotten.MOD_ID, value = Dist.CLIENT)
public class VersionOverlay {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        CommonVersionOverlay.onClientTick(Minecraft.getInstance());
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void render(RenderGuiEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
        if (CommonVersionOverlay.inAlpha(client)) return;

        CommonVersionOverlay.render(client, event.getGuiGraphics());
    }
}