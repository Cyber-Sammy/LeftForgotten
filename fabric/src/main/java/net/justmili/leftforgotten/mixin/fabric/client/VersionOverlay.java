package net.justmili.leftforgotten.mixin.fabric.client;

import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class VersionOverlay {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(at = @At("TAIL"), method = "render")
    public void render(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        if (!CommonVersionOverlay.inAlpha(minecraft)) return;
        this.minecraft.getProfiler().push("demo");

        CommonVersionOverlay.render(this.minecraft, graphics);
    }
}