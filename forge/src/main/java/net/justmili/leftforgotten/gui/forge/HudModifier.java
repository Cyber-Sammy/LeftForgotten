package net.justmili.leftforgotten.gui.forge;

import dev.architectury.platform.Platform;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HudModifier {
    private static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/icons.png");

    @SubscribeEvent
    public static void onGuiOverlayPre(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        NamedGuiOverlay getOverlay = event.getOverlay();
        IGuiOverlay overlay = getOverlay.overlay();
        ResourceLocation id = getOverlay.id();
        GuiGraphics gui = event.getGuiGraphics();
        float pt = event.getPartialTick();

        if (player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            int w = mc.getWindow().getGuiScaledWidth();
            int h = mc.getWindow().getGuiScaledHeight();

            // Defined heights and weights
            int playerHpH = 6;
            int armorW = 202;
            int armorH = 16;
            int airLvlW = 202;
            int airLvlH = 3;
            int horseBar = 7;
            int mountHpH = 3;
            int mountHpW = 0; // useless, but I say let it stay just in case
            int mountHpH_na = 9;
            int fullscreenOffset = 1;

            // Account for horse bar and fullscreen
            int horseBarOffset = player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled() ? horseBar : 0;
            int yOffset = horseBarOffset - fullscreenOffset;

            // Food disable
            if (id.equals(VanillaGuiOverlay.FOOD_LEVEL.id())) event.setCanceled(true);
            // Experience disable
            if (id.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) event.setCanceled(true);
            // Armor move down
            if (id.equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
                event.setCanceled(true);
                overlay.render((ForgeGui) mc.gui, gui, pt, w + armorW, h + armorH - yOffset);
            }
            // Player HP move down
            if (id.equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
                event.setCanceled(true);
                overlay.render((ForgeGui) mc.gui, gui, pt, w, h + playerHpH - yOffset);
            }
            // Air lvl move right and down
            if (id.equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
                event.setCanceled(true);
                int air = Math.min(player.getAirSupply(), player.getMaxAirSupply());
                int maxAir = player.getMaxAirSupply();
                if (!player.isEyeInFluid(FluidTags.WATER) && air >= maxAir) return;

                int full = net.minecraft.util.Mth.ceil((double)(air - 2) * 10.0 / maxAir);
                int partial = net.minecraft.util.Mth.ceil((double)air * 10.0 / maxAir) - full;
                int rh = ((ForgeGui) mc.gui).rightHeight;
                int top = h - rh - airLvlH - yOffset;
                int barEnd = w / 2 + 51;

                for (int i = 0; i < full + partial; ++i) {
                    int origX = w / 2 - 9 - i * 8 - 9;
                    int mirroredX = 2 * barEnd - 9 - origX - airLvlW;
                    gui.blit(GUI_ICONS_LOCATION, mirroredX, top, (i < full ? 16 : 25), 18, 9, 9);
                }
            }
            // Mount HP move down, account for armor
            if (id.equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
                event.setCanceled(true);
                if (player.getArmorValue() > 0) {
                    //Armor on
                    overlay.render((ForgeGui) mc.gui, gui, pt, w - mountHpW, h - mountHpH - yOffset);
                } else {
                    //Armor off
                    overlay.render((ForgeGui) mc.gui, gui, pt, w - mountHpW, h - mountHpH - yOffset + mountHpH_na);
                }
            }
        }

        // Get rid of NT's version overlay and stamina bar when in dimension
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            if (player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
                String ns = id.getNamespace();
                String path = id.getPath().toLowerCase();
                if (!("nostalgic_tweaks".equals(ns))) return;
                if (path.contains("stamina")) event.setCanceled(true);
                if (CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(false);
            } else {
                if (!CandyTweak.OLD_VERSION_OVERLAY.get()) CandyTweak.OLD_VERSION_OVERLAY.setCacheAndDiskThenSave(true);
            }
        }
    }
}
