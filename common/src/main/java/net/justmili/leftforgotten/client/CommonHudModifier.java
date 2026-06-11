package net.justmili.leftforgotten.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.justmili.leftforgotten.core.util.ResourceUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.joml.Matrix4f;

public class CommonHudModifier {
    private static final Minecraft minecraft = Minecraft.getInstance();
    public static int getWidth() {
        return minecraft.getWindow().getGuiScaledWidth();
    }
    public static int getHeight() {
        return minecraft.getWindow().getGuiScaledHeight();
    }

    public static class Common {
        public static final ResourceLocation ARMOR_EMPTY_SPRITE = ResourceUtil.minecraftResource("hud/armor_empty");
        public static final ResourceLocation ARMOR_HALF_SPRITE = ResourceUtil.minecraftResource("hud/armor_half");
        public static final ResourceLocation ARMOR_FULL_SPRITE = ResourceUtil.minecraftResource("hud/armor_full");

        public static int mirrorX(int x) {
            return 2 * (getWidth() / 2-91)+72-x;
        }

        public static void renderFlippedSprite(GuiGraphics graphics, TextureAtlasSprite atlasSprite,
                                               int x1, int y1, int width, int height) {
            int x2 = x1+width,
                y2 = y1+height,
                blitOffset = 0;
            float minU = atlasSprite.getU1(),
                maxU = atlasSprite.getU0(),
                minV = atlasSprite.getV0(),
                maxV = atlasSprite.getV1();

            RenderSystem.setShaderTexture(0, atlasSprite.atlasLocation());
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            Matrix4f matrix4f = graphics.pose().last().pose();
            BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferBuilder.addVertex(matrix4f, x1, y1, blitOffset).setUv(minU, minV);
            bufferBuilder.addVertex(matrix4f, x1, y2, blitOffset).setUv(minU, maxV);
            bufferBuilder.addVertex(matrix4f, x2, y2, blitOffset).setUv(maxU, maxV);
            bufferBuilder.addVertex(matrix4f, x2, y1, blitOffset).setUv(maxU, minV);
            BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
        }
    }

    public static class Fabric {
        public static final int // Defined widths and heights (X-Y pos)
            playerHpH = 7,    // Player HP Y offset
            armorW = 101,     // Armor X offset
            armorH = 17,      // Armor Y offset
            airLvlW = 101,    // Air level X offset
            airLvlH = 2,      // Air level Y offset
            horseBar = 7,     // Horse bar
            mountHpH = 2,     // Mount HP Y offset
            mountHpH_na = 7;  // Mount HP Y offset without Armor

        public static int yOffset() { // Account for horse bar and Creative, Fabric doesn't need to account for fullscreen
            int inCreative = minecraft.player.isCreative() ? -9 : 0;
            return (minecraft.player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled()) ? horseBar+inCreative : inCreative;
        }
        public static int mountHpOffset() {
            return getHeight() - 39 - yOffset() - mountHpH;
        }
    }

    public static class NeoForge {
        public static final int // Defined widths and heights (X-Y pos)
            playerHpH = 6,    // Player HP Y offset
            armorW = 101,     // Armor X offset
            armorH = 6,       // Armor Y offset
            airLvlW = 202,    // Air level X offset
            airLvlH = 3,      // Air level Y offset
            horseBar = 7,     // Horse bar
            mountHpH = 3,     // Mount HP Y offset
            mountHpW = 0,     // Mount HP X offset - useless, but I say let it stay just in case
            mountHpH_na = 9,  // Mount HP Y offset without Armor
            fullscreenOffset = 1; // Fullscreen accountability because Forge is weird

        private static boolean hasSaddle() {
            return minecraft.player.getVehicle() instanceof AbstractHorse horse && horse.isSaddled();
        }
        public static int yOffset() {
            int horseBarOffset = hasSaddle() ? horseBar : 0,
                inCreative = (minecraft.player.isCreative() && hasSaddle()) ? -9 : 0;
            return horseBarOffset + inCreative - fullscreenOffset;
        }
        public static int mountHpOffset() {
            int creativeNoSaddleFix = (minecraft.player.isCreative() && !hasSaddle()) ? horseBar + 2 : 0;
            return -mountHpH - yOffset() + creativeNoSaddleFix;
        }
    }
}
