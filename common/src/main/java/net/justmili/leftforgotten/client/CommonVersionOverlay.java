package net.justmili.leftforgotten.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class CommonVersionOverlay {
    public static final String BASE_TEXT = "Minecraft Alpha v1.1.2_01";
    public static final String[] VERSIONS = { // List of texts to glitch between
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

    public static String currentText = BASE_TEXT;
    public static int flashTicks = 4;
    public static final Random random = new Random();

    public static boolean inAlpha(Minecraft client) {
        return client.player != null && client.player.level().dimension() == LFResources.Levels.ALPHA_MINECRAFT;
    }
    public static boolean notInAlpha(Minecraft client) {
        return !inAlpha(client);
    }

    public static void onClientTick(Minecraft client) {
        if (client.level == null || client.level.dimension() != LFResources.Levels.ALPHA_MINECRAFT) {
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
    
    public static void render(Minecraft client, GuiGraphics graphics) {
        final int fontSize = 32;
        float guiScaleFactor = (float) client.getWindow().getScreenWidth() / client.getWindow().getGuiScaledWidth(),
            baseFontHeight = client.font.lineHeight,
            userScale = fontSize / baseFontHeight;

        int x = 6,
            y = 6,
            textColor = 0xFFFFFF,
            textShadowColor = 0xFF3F3F3F,
            drawX = Math.round(x / userScale),
            drawY = Math.round(y / userScale);

        graphics.pose().pushPose();
        graphics.pose().scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        graphics.pose().scale(userScale, userScale, 1f);

        graphics.drawString(client.font, Component.literal(currentText), drawX+1, drawY+1, textShadowColor, false);
        graphics.drawString(client.font, Component.literal(currentText), drawX, drawY, textColor, false);

        graphics.pose().popPose();
    }
}