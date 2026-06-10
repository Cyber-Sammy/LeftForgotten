package net.justmili.leftforgotten.mechanics.events;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MoveToAlpha {
    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();

        ServerLevel newLevel = player.getServer().getLevel(LFResources.Levels.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        player.teleportTo(newLevel, player.getX(), 156, player.getZ(), player.getYRot(), player.getXRot());

        return EventResult.interruptFalse();
    }

    public static EventResult onEntityHurt1(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return EventResult.pass();
        if (v > 512f) return EventResult.pass(); // Let through if damage is high enough, otherwise /kill doesn't work - this will also allow a mace hit to kill you
        if (player.getHealth() - v > 0) return EventResult.pass();

        player.setHealth(2);
        player.hurt(player.damageSources().fall(), 1f);

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player ticking) {
        if (!(ticking instanceof ServerPlayer player)) return;
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;
        if (player.getY() < 196) return;

        ServerLevel overworld = player.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        Vec3 momentum = player.getDeltaMovement();
        player.teleportTo(overworld, player.getX(), -88, player.getZ(), player.getYRot(), player.getXRot());
        player.setDeltaMovement(momentum);
        player.startFallFlying();

        // Schedule effect for next tick
        overworld.getServer().execute(() -> {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, false, false));
        });
    }
}