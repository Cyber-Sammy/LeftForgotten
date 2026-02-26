package net.justmili.leftforgotten.mechanics.events;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.init.DimKeys;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DimChange {
    public static EventResult attemptDimEntry(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();
        if (v < player.getHealth()) return EventResult.pass();

        ServerLevel newLevel = player.getServer().getLevel(DimKeys.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        player.teleportTo(newLevel, player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
        // make the player face downwards
        // I'll finish it later, at the time of writing this it's 2:30am

        return EventResult.pass();
    }

    public static EventResult attemptDimExit(LivingEntity entity, DamageSource source, float v) {
        return null;
    }
    //Add a similar thing for leaving
}