package net.justmili.leftforgotten.mixin.fabric.client;

import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class VersionOverlay {
    private boolean inAlpha() {
        return this.minecraft.player != null && this.minecraft.player.level().dimension() == LFResources.Levels.ALPHA_MINECRAFT;
    }

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(at = @At("TAIL"), method = "render")
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!inAlpha()) return;
        this.minecraft.getProfiler().push("demo");
        Component component = Component.literal(net.justmili.leftforgotten.client.VersionOverlay.currentText);

        final int fontSize = 32;
        float guiScaleFactor = (float) this.minecraft.getWindow().getScreenWidth() / (float) this.minecraft.getWindow().getGuiScaledWidth(),
            baseFontHeight = (float) this.minecraft.font.lineHeight,
            userScale = fontSize / baseFontHeight;

        int x = 6,
            y = 6,
            textColor = 0xFFFFFF,
            textShadowColor = 0xFF3F3F3F,
            drawX = Math.round(x / userScale),
            drawY = Math.round(y / userScale);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        guiGraphics.pose().scale((int) userScale, (int) userScale, 1f);

        guiGraphics.drawString(minecraft.font, component, drawX+1, drawY+1, textShadowColor, false);
        guiGraphics.drawString(minecraft.font, component, drawX, drawY, textColor, false);

        guiGraphics.pose().popPose();
    }
}