package net.justmili.leftforgotten.init;

import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.justmili.leftforgotten.mechanics.gameplay.AlphaFoodSystem;
import net.justmili.leftforgotten.mechanics.gameplay.NoCooldown;
import net.justmili.leftforgotten.mechanics.gameplay.NoSprint;
import net.justmili.leftforgotten.mechanics.logic.FoodLvlReset;

public class Events {
    public static void register() {
        TickEvent.PLAYER_POST.register(NoSprint::onPlayerTick);
        PlayerEvent.CHANGE_DIMENSION.register(FoodLvlReset::onChangeDimension);

        PlayerEvent.PLAYER_RESPAWN.register(NoCooldown::onPlayerRespawn);

        InteractionEvent.RIGHT_CLICK_ITEM.register(AlphaFoodSystem::onRightClickItem);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(AlphaFoodSystem::onRightClickBlock);
    }
}
