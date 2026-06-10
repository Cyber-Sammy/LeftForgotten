package net.justmili.leftforgotten.neoforge.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.joml.Matrix4f;

@EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    private static final ResourceLocation GUI_ICONS_LOCATION = LeftForgotten.asPath("textures/gui/icons.png");

    @SubscribeEvent
    public static void onGuiOverlayPre(RenderGuiLayerEvent.Pre event) {
        Minecraft minecarft = Minecraft.getInstance();
        Player player = minecarft.player;
        if (player == null) return;

        LayeredDraw.Layer overlay = event.getLayer();
        ResourceLocation id = event.getName();
        GuiGraphics graphics = event.getGuiGraphics();
        DeltaTracker partTick = event.getPartialTick();

        if (player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT) && !minecarft.options.hideGui) {
            int w = minecarft.getWindow().getGuiScaledWidth();
            int h = minecarft.getWindow().getGuiScaledHeight();

            // Defined widths and heights (X-Y pos)
            int playerHpH = 6,    // Player HP Y offset
                armorW = 101,     // Armor X offset
                armorH = 6,       // Armor Y offset
                airLvlW = 202,    // Air level X offset
                airLvlH = 3,      // Air level Y offset
                horseBar = 7,     // Horse bar
                mountHpH = 3,     // Mount HP Y offset
                mountHpW = 0,     // Mount HP X offset - useless, but I say let it stay just in case
                mountHpH_na = 9,  // Mount HP Y offset without Armor
                fullscreenOffset = 1, // Fullscreen accountability because Forge is weird

            // Account for AbstractHorse jump bar when saddled and fullscreen
                horseBarOffset = player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled() ? horseBar : 0,
                yOffset = horseBarOffset - fullscreenOffset;

            // Food disable
            if (id.equals(VanillaGuiLayers.FOOD_LEVEL)) event.setCanceled(true);
            // Experience disable
            if (id.equals(VanillaGuiLayers.EXPERIENCE_BAR)) event.setCanceled(true);

            // Armor move right and down, flip armor sprites
            if (id.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
                event.setCanceled(true);

                int level = player.getArmorValue();
                for (int i = 1; level > 0 && i < 20; i += 2) {
                    int uOffset = i < level ? 34 : i == level ? 25 : 16,
                        origX = w / 2 - 91 + ((i - 1) / 2) * 8,
                        barStart = w / 2 - 91,
                        mirroredX = 2 * barStart + 72 - origX,

                        x1 = mirroredX + armorW,
                        x2 = x1 + 9,
                        y1 = h - 39 + armorH - yOffset,
                        y2 = y1 + 9,
                        blitOffset = 0;
                    float minU = (uOffset + 9f) / 256f,
                          maxU = (uOffset + 0.0f) / 256f,
                          minV = 9f / 256f,
                          maxV = 18f / 256f;

                    // Flip the sprites via Blaze3D engine
                    RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);
                    RenderSystem.setShader(GameRenderer::getPositionTexShader);
                    Matrix4f matrix4f = graphics.pose().last().pose();
                    BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                    bufferBuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV);
                    bufferBuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV);
                    bufferBuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV);
                    bufferBuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV);
                    BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
                }
            }
            // Player HP move down
            if (id.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
                event.setCanceled(true);
                render(graphics, overlay, partTick, 0, playerHpH - yOffset, 0);
            }
            // Air level move left and down, account for AbstractHorse jump bar when saddled
            if (id.equals(VanillaGuiLayers.AIR_LEVEL)) {
                event.setCanceled(true);
                int air = Math.min(player.getAirSupply(), player.getMaxAirSupply()),
                    maxAir = player.getMaxAirSupply();
                if (!player.isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

                int full = net.minecraft.util.Mth.ceil((double)(air - 2) * 10.0 / maxAir),
                    partial = net.minecraft.util.Mth.ceil((double)air * 10.0 / maxAir) - full,
                    rh = minecarft.gui.rightHeight,
                    top = h - rh - airLvlH - yOffset,
                    barEnd = w / 2 + 51;

                for (int i = 0; i < full + partial; ++i) {
                    int origX = w / 2 - 9 - i * 8 - 9,
                        mirroredX = 2 * barEnd - 9 - origX - airLvlW;
                    graphics.blit(GUI_ICONS_LOCATION, mirroredX, top, (i < full ? 16 : 25), 18, 9, 9);
                }
            }
            // Mount HP move down, account for AbstractHorse jump bar when saddled and Armor
            if (id.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
                event.setCanceled(true);
                if (player.getArmorValue() > 0) {
                    //Armor on
                    render(graphics, overlay, partTick, -mountHpW, -mountHpH - yOffset, 0);
                } else {
                    //Armor off
                    render(graphics, overlay, partTick, -mountHpW, -mountHpH - yOffset + mountHpH_na, 0);
                }
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
                String ns = id.getNamespace();
                String path = id.getPath().toLowerCase();
                if (!("nostalgic_tweaks".equals(ns))) return; // "Is it from NT?"
                if (path.contains("stamina")) event.setCanceled(true); // Get rid of the stamina bar
                // Get rid of NT's version overlay
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }

    private static void render(GuiGraphics graphics, LayeredDraw.Layer overlay, DeltaTracker partialTick, int x, int y, int z) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, z);
        overlay.render(graphics, partialTick);
        graphics.pose().popPose();
    }
}