package net.justmili.leftforgotten.gui.neoforge;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.event.events.common.TickEvent;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.util.Random;

@EventBusSubscriber(modid = LeftForgotten.MOD_ID, value = Dist.CLIENT)
public class VersionOverlay {

    private static final String BASE_TEXT = "Minecraft Alpha v1.1.2_01";
    private static final String[] VERSIONS = { // List of texts to glitch between
        "Minecraft v1.20.1",
        "Minecraft v1.21.1",
        "Minecraft v1.21.4",
        "Minecraft v1.21.5",
        "Minecraft v1.21.6",
        "Minecraft v1.21.7",
        "Minecraft v1.21.8",
        "Minecraft v1.21.9",
        "Minecraft v1.21.10",
        "Minecraft v1.21.11",
        "Minecraft v26.1",
        "Minecraft v26.1.1"
    };

    private static String currentText = BASE_TEXT;
    private static int flashTicks = 4;
    private static final Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onClientTick(ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.level.dimension() != LFResources.Levels.ALPHA_MINECRAFT) {
            currentText = BASE_TEXT;
            flashTicks = 0;
            return;
        }

        if (flashTicks > 0) {
            flashTicks--;
            if (flashTicks == 0) {
                currentText = BASE_TEXT;
            }
            // Dynamic String Change
            // 6000 - ticks between each random "glitch"
            // "//2-6 ticks" - "glitch" string show time
        } else if (random.nextInt(6000) == 0) {
            currentText = VERSIONS[random.nextInt(VERSIONS.length)];
            flashTicks = 2 + random.nextInt(5); // 2–4 ticks
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.level.dimension() != LFResources.Levels.ALPHA_MINECRAFT) return;

        GuiGraphics graphics = event.getGuiGraphics();
        PoseStack pose = graphics.pose();

        final int fontSize = 32;
        float guiScaleFactor = (float) mc.getWindow().getScreenWidth() / mc.getWindow().getGuiScaledWidth(),
            baseFontHeight = mc.font.lineHeight,
            userScale = fontSize / baseFontHeight;

        pose.pushPose();
        pose.scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        pose.scale(userScale, userScale, 1f);

        int x = 6,
            y = 6,
            textColor = 0xFFFFFF,
            textShadowColor = 0xFF3F3F3F,
            drawX = Math.round(x / userScale),
            drawY = Math.round(y / userScale);

        graphics.drawString(mc.font, Component.literal(currentText), drawX + 1, drawY + 1, textShadowColor, false);
        graphics.drawString(mc.font, Component.literal(currentText), drawX, drawY, textColor, false);

        pose.popPose();
    }
}