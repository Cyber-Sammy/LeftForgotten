package net.justmili.leftforgotten.init;

import dev.architectury.event.events.common.EntityEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.justmili.leftforgotten.mechanics.advancements.OnARail;
import net.justmili.leftforgotten.mechanics.advancements.WhenPigsFly;
import net.justmili.leftforgotten.mechanics.compatibility.NostalgicTweaksCompatibiliy;
import net.justmili.leftforgotten.mechanics.events.DimChange;
import net.justmili.leftforgotten.mechanics.gameplay.AlphaFoodSystem;
import net.justmili.leftforgotten.mechanics.gameplay.NoCooldown;
import net.justmili.leftforgotten.mechanics.gameplay.NoSprint;
import net.justmili.leftforgotten.mechanics.gameplay.WoolDrop;
import net.justmili.leftforgotten.mechanics.logic.FoodLvlReset;

public class Events {
    public static void register() {
        TickEvent.PLAYER_POST.register(NoSprint::onPlayerTick);
        TickEvent.PLAYER_POST.register(OnARail::onPlayerTick);
        TickEvent.PLAYER_POST.register(NostalgicTweaksCompatibiliy::onPlayerTick);

        PlayerEvent.CHANGE_DIMENSION.register(FoodLvlReset::onChangeDimension);
        PlayerEvent.CHANGE_DIMENSION.register(NoCooldown::onChangedDimension);
        PlayerEvent.PLAYER_RESPAWN.register(NoCooldown::onPlayerRespawn);
        PlayerEvent.PLAYER_JOIN.register(NoCooldown::onPlayerJoin);
        EntityEvent.LIVING_DEATH.register(WhenPigsFly::onEntityDeath);
        EntityEvent.LIVING_HURT.register(WoolDrop::onEntityHurt);

        EntityEvent.LIVING_HURT.register(DimChange::onEntityHurt);
        EntityEvent.LIVING_HURT.register(DimChange::onEntityHurt1);
        TickEvent.PLAYER_POST.register(DimChange::onPlayerTick);

        InteractionEvent.RIGHT_CLICK_ITEM.register(AlphaFoodSystem::onRightClickItem);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(AlphaFoodSystem::onRightClickBlock);
    }
}
