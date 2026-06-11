package net.justmili.leftforgotten.neoforge.client;

import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.core.util.ResourceUtil;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.NeoForge.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.getHeight;
import static net.justmili.leftforgotten.client.CommonHudModifier.getWidth;

@EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    private static final ResourceLocation GUI_ICONS_LOCATION = ResourceUtil.asPath("textures/gui/icons.png");

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
            
            // Food disable
            if (id.equals(VanillaGuiLayers.FOOD_LEVEL)) event.setCanceled(true);
            // Experience disable
            if (id.equals(VanillaGuiLayers.EXPERIENCE_BAR)) event.setCanceled(true);

            // Armor move right and down, flip armor sprites
            if (id.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
                event.setCanceled(true);

                int level = player.getArmorValue();
                for (int i = 1; level > 0 && i < 20; i += 2) {
                    ResourceLocation sprite = i < level ? ARMOR_FULL_SPRITE : i == level ? ARMOR_HALF_SPRITE : ARMOR_EMPTY_SPRITE;
                    TextureAtlasSprite atlasSprite = Minecraft.getInstance().getGuiSprites().getSprite(sprite);
                    int origX = getWidth() / 2-91+((i-1) / 2) * 8,
                        x1 = mirrorX(origX)+armorW,
                        y1 = getHeight()-39+armorH-yOffset();

                    renderFlippedSprite(graphics, atlasSprite, x1, y1, 9, 9);
                }
            }
            // Player HP move down
            if (id.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
                event.setCanceled(true);
                render(graphics, overlay, partTick, 0, playerHpH-yOffset());
            }
            // Air level move left and down, account for AbstractHorse jump bar when saddled
            if (id.equals(VanillaGuiLayers.AIR_LEVEL)) {
                event.setCanceled(true);
                int air = Math.min(player.getAirSupply(), player.getMaxAirSupply()),
                    maxAir = player.getMaxAirSupply();
                if (!player.isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

                int full = net.minecraft.util.Mth.ceil((air-2) * 10.0 / maxAir),
                    partial = net.minecraft.util.Mth.ceil(air * 10.0 / maxAir)-full,
                    rh = minecarft.gui.rightHeight,
                    top = getHeight()-rh-airLvlH-yOffset(),
                    barEnd = getWidth() / 2+51;

                for (int i = 0; i < full+partial; ++i) {
                    int origX = getWidth() / 2-9-i * 8-9,
                        mirroredX = 2 * barEnd-9-origX-airLvlW;
                    graphics.blit(GUI_ICONS_LOCATION, mirroredX, top, (i < full ? 16 : 25), 18, 9, 9);
                }
            }
            // Mount HP move down, account for AbstractHorse jump bar when saddled and Armor
            if (id.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
                event.setCanceled(true);
                if (player.getArmorValue() > 0) {
                    //Armor on
                    render(graphics, overlay, partTick, -mountHpW, -mountHpH-yOffset());
                } else {
                    //Armor off
                    render(graphics, overlay, partTick, -mountHpW, -mountHpH-yOffset()+mountHpH_na);
                }
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
                String ns = id.getNamespace(),
                    path = id.getPath().toLowerCase();
                if (!("nostalgic_tweaks".equals(ns))) return; // "Is it from NT?"
                if (path.contains("stamina")) event.setCanceled(true); // Get rid of the stamina bar
                // Get rid of NT's version overlay
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }

    private static void render(GuiGraphics graphics, LayeredDraw.Layer overlay, DeltaTracker partialTick, int x, int y) {
        graphics.pose().pushPose();
        graphics.pose().translate(x, y, 0);
        overlay.render(graphics, partialTick);
        graphics.pose().popPose();
    }
}