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
import org.spongepowered.asm.mixin.Unique;
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

    // Defined heights and weights
    @Unique private static final int horseBar = 7;     // Horse bar
    @Unique private static final int armorW = 101;     // Armor X offset
    @Unique private static final int armorH = 17;      // Armor Y offset
    @Unique private static final int playerHpH = 7;    // Player HP Y offset
    @Unique private static final int playerHpH_nt = 7; /// why the hell do we need the NT thing? NT Stamina should be hidden so nothing gets moved
    @Unique private static final int airLvlW = 101;    // Air level X offset
    @Unique private static final int airLvlH = 2;      // Air level Y offset
    @Unique private static final int mountHpH_na = 7;  // Mount HP Y offset with Armor
    @Unique private static final int mountHpH = 2;     // Mount HP Y offset
    // Account for horse bar, Fabric doesn't need to account for fullscreen
    private int yOffset() {
        Player player = this.minecraft.player;
        int horseBarOffset = (player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled()) ? horseBar : 0;
        return horseBarOffset;
    }

    @Shadow @Final private Minecraft minecraft;
    @Shadow private int screenWidth;
    @Shadow private int screenHeight;
    @Shadow protected abstract int getVehicleMaxHearts(LivingEntity vehicle);

    // I don't know what to comment on this
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

    // Player HP - move down, move down with NT, account for horse bar
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (!inAlpha()) return y;
        if (Platform.isModLoaded("nostalgic_tweaks")) {
            return y + playerHpH_nt - yOffset();
        } else {
            return y + playerHpH - yOffset();
        }
    }

    // Food disable
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/Gui;getVehicleMaxHearts(Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int wrapVehicleHearts(Gui instance, LivingEntity vehicle, Operation<Integer> original) {
        if (this.minecraft.player.level().dimension() != LFResources.Levels.ALPHA_MINECRAFT) {
            return this.getVehicleMaxHearts(vehicle);
        }
        return -1;
    }

    // Armor and Air Level
    @Redirect(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void redirectBlit(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
        if (!inAlpha()) {
            instance.blit(atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
            return;
        }

        if (this.currentProfiler.peek().equals("armor")) { // Armor move right and down
            instance.blit(atlasLocation, x + armorW, y + armorH - yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else if (this.currentProfiler.peek().equals("air")) { // Air level move left and
            instance.blit(atlasLocation, x - airLvlW, y - airLvlH + yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else {
            instance.blit(atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
        }
    }

    // EXP bar disable
    @Inject(at = @At("HEAD"), method = "renderExperienceBar", cancellable = true)
    private void renderExperienceBar(CallbackInfo ci) {
        if (inAlpha()) ci.cancel();
    }

    // Mount HP move, account for armor and horse bar
    @ModifyVariable(method = "renderVehicleHealth", at = @At("STORE"), ordinal = 2)
    private int moveMountHealthY(int y) {
        if (!inAlpha()) return y;

        int base = this.screenHeight - 39 - yOffset();
        if (this.minecraft.player.getArmorValue() > 0) {
            return base - mountHpH;
        } else {
            return base + mountHpH_na;
        }
    }
}