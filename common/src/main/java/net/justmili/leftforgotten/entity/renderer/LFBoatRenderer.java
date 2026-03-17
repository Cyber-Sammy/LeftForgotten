package net.justmili.leftforgotten.entity.renderer;

import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class LFBoatRenderer extends BoatRenderer {
    public LFBoatRenderer(EntityRendererProvider.Context context) {
        super(context, false);
    }
}