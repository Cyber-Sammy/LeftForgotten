package net.justmili.leftforgotten.mixin.fabric;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.client.VersionOverlay;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow private int screenWidth;
    @Shadow protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    /*
    Replication of Minecraft Alpha's HUD
     */
    // Mount HP move
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int wrapVehicleHearts(Gui instance, LivingEntity vehicle, Operation<Integer> original) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {return this.getVehicleMaxHearts(vehicle);}
        return -1;
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (minecraft.player.level().dimension() == LFResources.Levels.ALPHA_MINECRAFT) {
            ci.cancel();
        }
    }

    // Player HP - move down, move down with NT
    // Higher value = higher position
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {return y;}
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            return y +7 ;
        } else {
            return y - 17;
        }
    }

    // Player Air - move right
    /// To fix
    @ModifyVariable(method = "renderPlayerHealth", at = @At("STORE"), ordinal = 4)
    private int modifyBubblesX(int original) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {return original;}
        return original - 100;
    }

    // Player Air - move down, move down with NT
    /// To fix
    @ModifyVariable(method = "renderPlayerHealth", at = @At("STORE"), ordinal = 5)
    private int modifyBubblesY(int original) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {return original;}
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            return original;
        } else {
            return original + 24;
        }
    }

    /*
    Version Overlay, Fabric renderer
     */
    @Shadow() public abstract Font getFont();
    @Inject(at = @At("TAIL"), method = "render")
    public void render(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        if (this.minecraft.player == null) return;
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) return;
        this.minecraft.getProfiler().push("demo");
        Component component = Component.literal(VersionOverlay.currentText);

        final int fontSize = 32;
        float guiScaleFactor = (float) this.minecraft.getWindow().getScreenWidth() / (float) this.minecraft.getWindow().getGuiScaledWidth();
        float baseFontHeight = (float) this.minecraft.font.lineHeight;
        float userScale = fontSize / baseFontHeight;

        int i = this.getFont().width(component);
        int x = 6;
        int y = 6;
        int textColor = 0xFFFFFF;
        int textShadowColor = 0xFF3F3F3F;
        int drawX = Math.round(x / userScale);
        int drawY = Math.round(y / userScale);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1f / guiScaleFactor, 1f / guiScaleFactor, 1f);
        guiGraphics.pose().scale(userScale, userScale, 1f);

        guiGraphics.drawString(minecraft.font, component, drawX + 1, drawY + 1, textShadowColor, false);
        guiGraphics.drawString(minecraft.font, component, drawX, drawY, textColor, false);

        guiGraphics.pose().popPose();
    }
}
