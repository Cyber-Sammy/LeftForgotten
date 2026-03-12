package net.justmili.leftforgotten.mixin.fabric;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Stack;

@Mixin(value = Gui.class, priority = 2500)
public abstract class HudModifier {
    private boolean inAlpha() {
        return this.minecraft.player != null && this.minecraft.player.level().dimension() == LFResources.Levels.ALPHA_MINECRAFT;
    }

    // Defined heights and weights
    @Unique private static final int playerHpH = 7;    // Player HP Y offset
    @Unique private static final int armorW = 101;     // Armor X offset
    @Unique private static final int armorH = 17;      // Armor Y offset
    @Unique private static final int airLvlW = 101;    // Air level X offset
    @Unique private static final int airLvlH = 2;      // Air level Y offset
    @Unique private static final int horseBar = 7;     // Horse bar
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
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void logProfilePushes(ProfilerFiller instance, String name, Operation<Void> original) {
        currentProfiler.push(name);
        original.call(instance, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"))
    private void logProfilePopPushes(ProfilerFiller instance, String name, Operation<Void> original) {
        currentProfiler.pop();
        currentProfiler.push(name);
        original.call(instance, name);
    }
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V"))
    private void logProfilePops(ProfilerFiller instance, Operation<Void> original) {
        currentProfiler.pop();
        original.call(instance);
    }

    // Player HP - move down, move down with NT, account for horse bar
    @ModifyVariable(method = "renderHearts", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int moveHeartsDown(int y) {
        if (!inAlpha()) return y;

        return y + playerHpH - yOffset();
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
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void redirectBlit(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, Operation<Void> original) {
        if (!inAlpha()) {
            original.call(instance, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
            return;
        }

        if (this.currentProfiler.peek().equals("armor")) { // Armor move right and down
            // Flip the way it goes
            // Fuck this, I'm not flipping the sprites edit by eetgeenappels: YEAH WE ARE FLIPPING THE SPRITES!!!!!!
            //instance.blit(atlasLocation, x + armorW, y + armorH - yOffset() - 20, uOffset, vOffset, uWidth, vHeight); <- old boring code

            int barStart = this.screenWidth / 2 - 91;
            int mirroredX = 2 * barStart + 72 - x;

            int x1 = mirroredX + armorW;
            int x2 = x1 + uWidth;
            int y1 = y + armorH - yOffset();
            int y2 = y1 + vHeight;
            int blitOffset = 0;
            float minU = (uOffset + (float)uWidth) / 256f;
            float maxU = (uOffset + 0.0F) / 256f;
            float minV = (vOffset + 0.0F) / 256f;
            float maxV = (vOffset + (float)vHeight) / 256f;

            RenderSystem.setShaderTexture(0, atlasLocation);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            Matrix4f matrix4f = instance.pose().last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.vertex(matrix4f, (float)x1, (float)y1, (float)blitOffset).uv(minU, minV).endVertex();
            bufferBuilder.vertex(matrix4f, (float)x1, (float)y2, (float)blitOffset).uv(minU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, (float)x2, (float)y2, (float)blitOffset).uv(maxU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, (float)x2, (float)y1, (float)blitOffset).uv(maxU, minV).endVertex();
            BufferUploader.drawWithShader(bufferBuilder.end());

        } else if (this.currentProfiler.peek().equals("air")) { // Air level move left and down
            // Flip the way it goes
            int barEnd = this.screenWidth / 2 + 51;
            int mirroredX = 2 * barEnd - 9 - x;
            instance.blit(atlasLocation, mirroredX - airLvlW, y - airLvlH + yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else {
            original.call(instance, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
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