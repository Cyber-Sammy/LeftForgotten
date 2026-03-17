package net.justmili.leftforgotten.entity;

import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.init.LFEntities;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LFBoatEntity extends Boat {
    private static final double BREAK_SPEED_THRESHOLD = 0.2;
    private static final float MAX_HEALTH = 4.0F;
    private float health = MAX_HEALTH;

    public LFBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }
    public LFBoatEntity(Level level, double x, double y, double z) {
        this(LFEntities.BOAT.get(), level);
        setPos(x, y, z);
        xo = x; yo = y; zo = z;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(LFItems.BOAT.get());
    }

    @Override
    public boolean getPaddleState(int side) {
        return false;
    }

    private void breakBoat() {
        if (level().isClientSide) {
            discard();
        } else {
            if (isRemoved()) return;
            spawnAtLocation(new ItemStack(LFItems.WOODEN_PLANKS.get(), 3));
            spawnAtLocation(new ItemStack(Items.STICK, 2));
            discard();
        }
    }
    @Override
    public void tick() {
        Vec3 vel = getDeltaMovement();
        double speedBefore = Math.sqrt(vel.x * vel.x + vel.z * vel.z);

        super.tick();

        Vec3 newVel = getDeltaMovement();
        double speedAfter = Math.sqrt(newVel.x * newVel.x + newVel.z * newVel.z);
        if (speedBefore > BREAK_SPEED_THRESHOLD && speedAfter < speedBefore * 0.4) {
            breakBoat();
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) return false;
        if (!level().isClientSide) {
            boolean isCreative = source.getEntity() instanceof Player player && player.getAbilities().instabuild;
            if (isCreative) { discard(); return true; }
            health -= amount;
            if (health <= 0.0F) breakBoat();
        }
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Health", health);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        health = tag.contains("Health") ? tag.getFloat("Health") : MAX_HEALTH;
    }
}