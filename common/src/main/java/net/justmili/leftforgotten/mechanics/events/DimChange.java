package net.justmili.leftforgotten.mechanics.events;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DimChange {
    public static EventResult attemptDimEntry(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) {
            LeftForgotten.LOGGER.warn("source is null!");
            return EventResult.pass();
        }
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
            LeftForgotten.LOGGER.warn("damage source is not void!");
            return EventResult.pass();
        }
        LeftForgotten.LOGGER.warn("damage source is void!");

        ServerLevel newLevel = player.getServer().getLevel(LFResources.getLevels.ALPHA_MINECRAFT);
        if (newLevel == null) {
            LeftForgotten.LOGGER.warn("newLevel is null!");
            return EventResult.pass();
        }

        player.setHealth(player.getMaxHealth());
        player.teleportTo(newLevel, player.getX(), 150, player.getZ(), player.getYRot(), 90f);
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));

        return EventResult.interruptFalse();
    }
}