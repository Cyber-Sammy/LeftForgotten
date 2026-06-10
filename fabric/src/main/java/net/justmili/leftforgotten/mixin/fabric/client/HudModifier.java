package net.justmili.leftforgotten.mixin.fabric.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.justmili.leftforgotten.client.CommonVersionOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
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
    @Unique
    private static final int // Defined widths and heights (X-Y pos)
        playerHpH = 7,    // Player HP Y offset
        armorW = 101,     // Armor X offset
        armorH = 17,      // Armor Y offset
        airLvlW = 101,    // Air level X offset
        airLvlH = 2,      // Air level Y offset
        horseBar = 7,     // Horse bar
        mountHpH = 2,     // Mount HP Y offset
        mountHpH_na = 7;  // Mount HP Y offset without Armor

    // Account for horse bar, Fabric doesn't need to account for fullscreen
    @Unique
    private int yOffset() {
        return (this.minecraft.player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled()) ? horseBar : 0;
    }

    @Shadow
    @Final
    private Minecraft minecraft;
    @Shadow
    private int screenWidth, screenHeight;

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

    // Armor and Air Level, flip armor sprites, account for AbstractHorse jump bar when saddled
    @WrapOperation(method = "renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void redirectBlit(GuiGraphics graphics, ResourceLocation atlasLocation, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight, Operation<Void> original) {
        if (!CommonVersionOverlay.inAlpha(minecraft)) {
            original.call(graphics, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
            return;
        }

        if (this.currentProfiler.peek().equals("armor")) { // Armor move right and down
            // Mirror the entire HUD element
            int barStart = this.screenWidth / 2-91,
                mirroredX = 2 * barStart+72-x,

                // Math before flipping sprites
                x1 = mirroredX+armorW,
                x2 = x1+uWidth,
                y1 = y+armorH-yOffset(),
                y2 = y1+vHeight,
                blitOffset = 0;
            float minU = (uOffset+uWidth) / 256f,
                maxU = (uOffset+0.0F) / 256f,
                minV = (vOffset+0.0F) / 256f,
                maxV = (vOffset+vHeight) / 256f;

            // Flip the sprites via Blaze3D engine
            RenderSystem.setShaderTexture(0, atlasLocation);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            Matrix4f matrix4f = graphics.pose().last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.vertex(matrix4f, x1, y1, blitOffset).uv(minU, minV).endVertex();
            bufferBuilder.vertex(matrix4f, x1, y2, blitOffset).uv(minU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, x2, y2, blitOffset).uv(maxU, maxV).endVertex();
            bufferBuilder.vertex(matrix4f, x2, y1, blitOffset).uv(maxU, minV).endVertex();
            BufferUploader.drawWithShader(bufferBuilder.end());

        } else if (this.currentProfiler.peek().equals("air")) { // Air level move left and down
            // Flip the way it goes
            int barEnd = this.screenWidth / 2+51,
                mirroredX = 2 * barEnd-9-x;
            graphics.blit(atlasLocation, mirroredX-airLvlW, y-airLvlH+yOffset(), uOffset, vOffset, uWidth, vHeight);
        } else {
            original.call(graphics, atlasLocation, x, y, uOffset, vOffset, uWidth, vHeight);
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

        int base = this.screenHeight-39-yOffset();
        if (this.minecraft.player.getArmorValue() > 0) {
            return base-mountHpH;
        } else {
            return base+mountHpH_na;
        }
    }
}