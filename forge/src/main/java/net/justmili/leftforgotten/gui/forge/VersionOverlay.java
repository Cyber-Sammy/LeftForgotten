package net.justmili.leftforgotten.gui.forge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = LeftForgotten.MOD_ID, value = Dist.CLIENT)
public class VersionOverlay {

    private static final String BASE_TEXT = "Minecraft Alpha v1.1.2_01";
    private static final String[] VERSIONS = { // List of texts to glitch between
        "Cave Game",
        "Minecraft Classic v0.0.11a",
        "Minecraft Infdev v20100227", // Infinite terrain
        "Minecraft Alpha v1.0.16", // Survival Multiplayer
        "Minecraft Alpha v1.2.0", // Halloween Update
        "Minecraft Beta v1.5", // Redstone Update
        "Minecraft Beta v1.8.1", // Adventure Update
        "Minecraft v1.0.0", // Official Release
        "Minecraft v1.4.2", // Pretty Scary Update
        "Minecraft v1.5.2", // Redstone Update
        "Minecraft v1.7.2", // The Update that Changed the World
        "Minecraft v1.8.9", // Bountiful Update
        "Minecraft v1.9.4", // Combat Update
        "Minecraft v1.12.2", // World of Color
        "Minecraft v1.13.2", // Aquatic Update
        "Minecraft v1.14.4", // Village & Pillage
        "Minecraft v1.15.2", // Buzzy Bees
        "Minecraft v1.16.5", // Nether Update
        "Minecraft v1.18.2", // Caves & Cliffs pt.2
        "Minecraft v1.19.2", // The Wild Update
        "Minecraft v1.19.4",
        "Minecraft v1.20.1", // Trails & Tales
        "Minecraft v1.20.3", // Bats and Pots
        "Minecraft v1.21.1", // Tricky Trials
        "Minecraft v1.21.2", // Bundles of Bravery
        "Minecraft v1.21.4", // The Garden Awakens
        "Minecraft v1.21.5", // Spring to Life
        "Minecraft v1.21.6", // Chase the Skies
        "Minecraft v1.21.9", // Copper Age
        "Minecraft v1.21.11", // Mounts of Mayhem
        "Minecraft v26.1", // Tiny Takeover
        "Minecraft v26.1.2",
        "Minecraft v26.2" // Chaos Cubed
    };

    private static String currentText = BASE_TEXT;
    private static int flashTicks = 4;
    private static final Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onClientTick(TickEvent.ClientTickEvent event) {
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
            flashTicks = 2+random.nextInt(5); // 2–4 ticks
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.level.dimension() != LFResources.Levels.ALPHA_MINECRAFT) return;
        if (mc.options.renderDebug) return;

        GuiGraphics gui = event.getGuiGraphics();
        PoseStack pose = gui.pose();

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

        gui.drawString(mc.font, Component.literal(currentText), drawX+1, drawY+1, textShadowColor, false);
        gui.drawString(mc.font, Component.literal(currentText), drawX, drawY, textColor, false);

        pose.popPose();
    }
}