package net.justmili.leftforgotten.mechanics.events;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.registries.LFResources;
import net.minecraft.core.BlockPos;
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

public class DimChange {
    public static EventResult onEntityHurt(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();

        ServerLevel newLevel = player.getServer().getLevel(LFResources.Levels.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        player.teleportTo(newLevel, player.getX(), 156, player.getZ(), player.getYRot(), player.getXRot());
        // Schedule effect for next tick
        player.getServer().execute(() -> {
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));
        });

        return EventResult.interruptFalse();
    }

    public static EventResult onEntityHurt1(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return EventResult.pass();
        if (player.getHealth() - v > 0) return EventResult.pass();

        player.setHealth(1);

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        if (!serverPlayer.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;
        if (serverPlayer.getY() < 196) return;

        ServerLevel overworld = serverPlayer.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        Vec3 momentum = serverPlayer.getDeltaMovement();
        serverPlayer.teleportTo(overworld, serverPlayer.getX(), -88, serverPlayer.getZ(), serverPlayer.getYRot(), serverPlayer.getXRot());
        serverPlayer.setDeltaMovement(momentum);
        serverPlayer.startFallFlying();

        // Schedule effect for next tick
        overworld.getServer().execute(() -> {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, false, false));
        });
    }
}