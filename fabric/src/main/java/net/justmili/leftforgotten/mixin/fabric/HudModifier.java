package net.justmili.leftforgotten.mixin.fabric;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.client.VersionOverlay;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Stack;

@Mixin(Gui.class)
public abstract class HudModifier {
    private boolean inAlpha() {
        return this.minecraft.player != null && this.minecraft.player.level().dimension() == LFResources.Levels.ALPHA_MINECRAFT;
    }

    private int yOffset() {
        Player player = this.minecraft.player;
        int horseBar = (player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled()) ? 7 : 0;
        return horseBar;
    }

    @Shadow @Final private Minecraft minecraft;
    @Shadow private int screenWidth;
    @Shadow private int screenHeight;
    @Shadow protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int wrapVehicleHearts(Gui instance, LivingEntity vehicle, Operation<Integer> original) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {
            return this.getVehicleMaxHearts(vehicle);
        }
        return -1;
    }

    private Stack<String> currentProfiler = new Stack<>();

    @Redirect(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void logProfilePushes(ProfilerFiller instance, String name) {
        currentProfiler.push(name);
        instance.push(name);
    }

    @Redirect(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"))
    private void logProfilePopPushes(ProfilerFiller instance, String name) {
        currentProfiler.pop();
        currentProfiler.push(name);
        instance.pop();
        instance.push(name);
    }

    @Redirect(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"))
    private void logProfilePops(ProfilerFiller instance) {
        currentProfiler.pop();
        instance.pop();
    }

    @Redirect(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void redirectBlit(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        if (!inAlpha()) {
            instance.blit(atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
            return;
        }

        if (this.currentProfiler.peek().equals("armor")) {
            instance.blit(atlasLocation, x + 101, y - 7 - yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else if (this.currentProfiler.peek().equals("air")) {
            instance.blit(atlasLocation, x - 1, y - 26 + yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else {
            instance.blit(atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
        }
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (inAlpha()) ci.cancel();
    }

    // Player HP - move down, move down with NT, account for horse bar
    // Higher value = higher position
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (!inAlpha()) return y;
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            return y + 7 - yOffset();
        } else {
            return y - 17 - yOffset();
        }
    }

    @ModifyVariable(method = "renderPlayerHealth", at = @At("STORE"), ordinal = 4)
    private int modifyBubblesX(int original) {
        if (!inAlpha()) return original;

        return original - 100;
    }

    @ModifyVariable(method = "renderPlayerHealth", at = @At("STORE"), ordinal = 5)
    private int modifyBubblesY(int original) {
        if (!inAlpha()) return original;

        if (Platform.isModLoaded("nostalgic_tweaks")) {
            return original;
        } else {
            return original + 24;
        }
    }

    // Mount HP move, account for armor and horse bar
    @ModifyVariable(method = "renderVehicleHealth", at = @At("STORE"), ordinal = 2)
    private int moveMountHealthY(int y) {
        if (!inAlpha()) return y;

        int base = this.screenHeight - 39 - yOffset();
        if (this.minecraft.player.getArmorValue() > 0) {
            return base - 2;
        } else {
            return base + 7;
        }
    }
}