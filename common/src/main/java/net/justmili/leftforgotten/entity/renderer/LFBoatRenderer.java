package net.justmili.leftforgotten.entity.renderer;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.world.entity.vehicle.Boat;

public class LFBoatRenderer extends BoatRenderer {
    public LFBoatRenderer(EntityRendererProvider.Context context) {
        super(context, false);

        boatResources.put(Boat.Type.OAK, Pair.of(
            LeftForgotten.asResource("textures/entity/boat/boat.png"),
            boatResources.get(Boat.Type.OAK).getSecond()
        ));
    }
}