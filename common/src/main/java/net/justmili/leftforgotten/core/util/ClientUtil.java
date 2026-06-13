package net.justmili.leftforgotten.core.util;

import com.mojang.blaze3d.platform.Window;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

@Environment(EnvType.CLIENT)
public class ClientUtil {
    public static Minecraft minecraft = Minecraft.getInstance();

    public static Window getWindow() {
        return minecraft.getWindow();
    }

    public static boolean isDebugScreenOn() {
        return minecraft.getDebugOverlay().showDebugScreen();
    }

    public static int getWidth() {
        return getWindow().getGuiScaledWidth();
    }
    public static int getHeight() {
        return getWindow().getGuiScaledHeight();
    }

    public static Player getPlayer() {
        return minecraft.player;
    }
    public static Level getLevel() {
        return getPlayer().level();
    }

    public static ResourceKey<Level> getDimension() {
        return getLevel().dimension();
    }

    public static boolean inDimension(ResourceKey<Level> dimension) {
        return getDimension() == dimension;
    }
}
