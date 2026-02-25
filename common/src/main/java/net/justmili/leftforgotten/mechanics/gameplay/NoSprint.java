package net.justmili.leftforgotten.mechanics.gameplay;

import net.minecraft.world.entity.player.Player;

import static net.justmili.leftforgotten.init.DimKeys.ALPHA_MINECRAFT;

public class NoSprint {
    public static void onPlayerTick(Player player) {
        if (player.level().dimension() == ALPHA_MINECRAFT) {
            player.getFoodData().setFoodLevel(4);
            player.getFoodData().setSaturation(0.0F);
        }
    }
}