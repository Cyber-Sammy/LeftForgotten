package net.justmili.leftforgotten.entity;

import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFEntities;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LFBoatEntity extends Boat {
    private static final double BREAK_SPEED_THRESHOLD = 0.2;

    public LFBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    public LFBoatEntity(Level level, double x, double y, double z) {
        this(LFEntities.BOAT.get(), level);
        setPos(x, y, z);
        xo = x; yo = y; zo = z;
    }

    @Override
    public Item getDropItem() {
        return LFItems.WOODEN_PLANKS.get();
    }

    private void breakBoat() {
        if (!level().isClientSide) {
            spawnAtLocation(LFItems.WOODEN_PLANKS.get(), 3);
            spawnAtLocation(Items.STICK, 2);
        }
        discard();
    }

    @Override
    public boolean getPaddleState(int side) {
        return false;
    }

    @Override
    public void tick() {
        Vec3 vel = getDeltaMovement();
        double speedBefore = Math.sqrt(vel.x * vel.x + vel.z * vel.z);

        super.tick();

        Vec3 newVel = getDeltaMovement();
        double speedAfter = Math.sqrt(newVel.x * newVel.x + newVel.z * newVel.z);

        // getStatus() is private — isOnGround() + speed loss is our best proxy for land impact
        boolean hardLandImpact = speedBefore > BREAK_SPEED_THRESHOLD
            && speedAfter < speedBefore * 0.4
            && onGround();

        if (hardLandImpact) {
            if (level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                    getX(), getY() + 0.5, getZ(), 3, 0.3, 0.1, 0.3, 0.0);
            }
            breakBoat();
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) return false;
        if (!level().isClientSide) breakBoat();
        return true;
    }

    // getVariant() returns OAK by default from defineSynchedData, no override needed

    @Override
    public void addAdditionalSaveData(CompoundTag tag) { super.addAdditionalSaveData(tag); }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) { super.readAdditionalSaveData(tag); }
}