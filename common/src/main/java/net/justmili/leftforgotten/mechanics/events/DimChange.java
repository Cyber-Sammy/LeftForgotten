package net.justmili.leftforgotten.mechanics.events;

import dev.architectury.event.EventResult;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
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
        LeftForgotten.LOGGER.info(String.format("Logged damage from %d at x%d-y%d-z%d", source.getMsgId(), entity.getX(), entity.getY(), entity.getZ()));

        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(Level.OVERWORLD)) return EventResult.pass();
        if (!source.is(DamageTypes.FELL_OUT_OF_WORLD)) return EventResult.pass();

        ServerLevel newLevel = player.getServer().getLevel(LFResources.Levels.ALPHA_MINECRAFT);
        if (newLevel == null) return EventResult.pass();

        player.teleportTo(newLevel, player.getX(), 150, player.getZ(), player.getYRot(), player.getXRot());
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, false));

        return EventResult.interruptFalse();
    }

    public static EventResult onEntityHurt1(LivingEntity entity, DamageSource source, float v) {
        if (!(entity instanceof ServerPlayer player)) return EventResult.pass();
        if (source == null) return EventResult.pass();
        if (!player.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return EventResult.pass();
        if (player.getHealth() - v > 0) return EventResult.pass();

        ServerLevel overworld = player.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return EventResult.pass();

        player.setHealth(player.getMaxHealth());

        BlockPos spawnPos = player.getRespawnPosition();
        ServerLevel spawnLevel = player.getServer().getLevel(player.getRespawnDimension());
        if (spawnPos == null || spawnLevel == null) {
            BlockPos worldSpawn = overworld.getSharedSpawnPos();
            player.teleportTo(overworld, worldSpawn.getX() + 0.5, worldSpawn.getY(), worldSpawn.getZ() + 0.5, player.getYRot(), player.getXRot());
        } else {
            player.teleportTo(spawnLevel, spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, player.getYRot(), player.getXRot());
        }

        return EventResult.interruptFalse();
    }

    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        if (!serverPlayer.level().dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;
        if (!serverPlayer.gameMode.isSurvival()) return; // for testing
        if (serverPlayer.getY() < 196) return;

        ServerLevel overworld = serverPlayer.getServer().getLevel(Level.OVERWORLD);
        if (overworld == null) return;

        Vec3 momentum = serverPlayer.getDeltaMovement();
        serverPlayer.teleportTo(overworld, serverPlayer.getX(), -88, serverPlayer.getZ(), serverPlayer.getYRot(), serverPlayer.getXRot());
        serverPlayer.setDeltaMovement(momentum);
        serverPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1, 0, false, false));
        serverPlayer.startFallFlying();
    }
}