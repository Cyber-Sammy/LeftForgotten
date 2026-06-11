package net.justmili.leftforgotten.mixin.fabric.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Stack;

import static net.justmili.leftforgotten.client.CommonHudModifier.Common.mirrorX;
import static net.justmili.leftforgotten.client.CommonHudModifier.Common.renderFlippedSprite;
import static net.justmili.leftforgotten.client.CommonHudModifier.Fabric.*;
import static net.justmili.leftforgotten.client.CommonHudModifier.getHeight;
import static net.justmili.leftforgotten.client.CommonHudModifier.getWidth;

@Mixin(value = Gui.class, priority = 2500)
public abstract class HudModifier {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    // Draw identifier for renderPlayerHealth's redirectBlit profiler section
    @Unique
    private Stack<String> currentProfiler = new Stack<>();
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void logProfilePushes(ProfilerFiller filler, String name, Operation<Void> original) {
        currentProfiler.push(name);
        original.call(filler, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"))
    private void logProfilePopPushes(ProfilerFiller filler, String name, Operation<Void> original) {
        currentProfiler.pop();
        currentProfiler.push(name);
        original.call(filler, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"))
    private void logProfilePops(ProfilerFiller filler, Operation<Void> original) {
        currentProfiler.pop();
        original.call(filler);
    }

    // Player HP - move down, account for AbstractHorse jump bar when saddled
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (!CommonVersionOverlay.inAlpha(minecraft)) return y;

        return y+playerHpH-yOffset();
    }

    // Food disable
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int disableFoodBar(Gui gui, LivingEntity vehicle, Operation<Integer> original) {
        if (CommonVersionOverlay.inAlpha(minecraft)) return -1;

        return this.getVehicleMaxHearts(vehicle);
    }

    // Armor - flip sprites, move right and down, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderArmor(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIII)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private static void redirectArmorBlit(GuiGraphics graphics, ResourceLocation sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (!CommonVersionOverlay.inAlpha(Minecraft.getInstance())) {
            original.call(graphics, sprite, x, y, width, height);
            return;
        }

        TextureAtlasSprite atlasSprite = Minecraft.getInstance().getGuiSprites().getSprite(sprite);
        int x1 = mirrorX(x)+armorW,
            y1 = y+armorH-yOffset();

        renderFlippedSprite(graphics, atlasSprite, x1, y1, width, height);
    }

    // Air Level - move left and down, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void redirectAirBlit(GuiGraphics graphics, ResourceLocation sprite, int x, int y, int width, int height, Operation<Void> original) {
        if (!CommonVersionOverlay.inAlpha(minecraft)) {
            original.call(graphics, sprite, x, y, width, height);
            return;
        }

        if (this.currentProfiler.peek().equals("air")) { // Air level move left and down
            // Flip the way it goes
            int barEnd = getWidth() / 2+51,
                mirroredX = 2 * barEnd-9-x;
            graphics.blitSprite(sprite, mirroredX-airLvlW, y-airLvlH+yOffset(), width, height);
        } else {
            original.call(graphics, sprite, x, y, width, height);
        }
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (CommonVersionOverlay.inAlpha(minecraft)) ci.cancel();
    }

    // Mount HP move, account for AbstractHorse jump bar when saddled and Armor
    @ModifyVariable(method = "renderVehicleHealth", at = @At("STORE"), ordinal = 2)
    private int moveMountHealthY(int y) {
        if (!CommonVersionOverlay.inAlpha(minecraft)) return y;

        int base = getHeight()-39-yOffset();
        if (this.minecraft.player.getArmorValue() > 0) {
            return base-mountHpH;
        } else {
            return base+mountHpH_na;
        }
    }
}