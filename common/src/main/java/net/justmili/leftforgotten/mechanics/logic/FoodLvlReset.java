package net.justmili.leftforgotten.mechanics.logic;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import static net.justmili.leftforgotten.init.DimKeys.ALPHA_MINECRAFT;

public class FoodLvlReset {
    public static void onChangeDimension(ServerPlayer player, ResourceKey<Level> fromDimension, ResourceKey<Level> toDimension) {
        if (fromDimension == ALPHA_MINECRAFT && toDimension == Level.OVERWORLD) {
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(10.0f);
        }
    }
}
