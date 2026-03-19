package net.justmili.leftforgotten.entity;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.init.LFEntities;
import net.justmili.leftforgotten.init.LFItems;
import net.minecraft.nbt.CompoundTag;
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

    public void breakOnImpactOnServer() {
        if (!level().isClientSide && !isRemoved()) {
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
            if (level().isClientSide) {
                BoatImpactPacket.send(getId());
                discard();
            }
        }
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isInvulnerableTo(source)) return false;
        if (!level().isClientSide) {
            boolean isCreative = source.getEntity() instanceof Player player && player.getAbilities().instabuild;
            if (isCreative) { discard(); return true; }
            health -= amount;
            if (health <= 0.0F && !isRemoved()) {
                spawnAtLocation(new ItemStack(LFItems.WOODEN_PLANKS.get(), 3));
                spawnAtLocation(new ItemStack(Items.STICK, 2));
                discard();
            }
        }
        return true;
    }
    @Override
    public boolean isControlledByLocalInstance() {
        if (Platform.isModLoaded("wurst") ||
            Platform.isModLoaded("wurstclient") ||
            Platform.isModLoaded("meteor-client") ||
            Platform.isModLoaded("meteor") ||
            Platform.isModLoaded("liquidbounce") ||
            Platform.isModLoaded("future") ||
            Platform.isModLoaded("impact") ||
            Platform.isModLoaded("ares") ||
            Platform.isModLoaded("sigma") ||
            Platform.isModLoaded("inertia")) {
            return false;
        }
        return super.isControlledByLocalInstance();
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