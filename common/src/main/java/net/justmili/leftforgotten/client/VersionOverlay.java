package net.justmili.leftforgotten.client;

import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;

import java.util.Random;

public class VersionOverlay {
    private static final String BASE_TEXT = "Minecraft Alpha v1.1.2_10";
    private static final String[] VERSIONS = {
        "Minecraft Alpha v1.1.2_01",
        "Minecraft v1.20.1",
    };

    public static String currentText = BASE_TEXT;
    private static int flashTicks = 4;
    private static final Random random = new Random();

    public static void onClientTick(Minecraft mc) {
        if (mc.level == null || mc.level.dimension() != LFResources.getLevels.ALPHA_MINECRAFT) {
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
}
