package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.misc.PacketRegistry;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MorbusProjectileEntity extends ThrownItemEntity {

    public MorbusProjectileEntity(EntityType<MorbusProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MorbusProjectileEntity(World world, LivingEntity owner, double x, double y, double z) {
        super(EntityRegistry.MORBUS_PROJECTILE, owner, world);
        updateVelocity(0.35F, new Vec3d(x, y, z));
        refreshPosition();
    }


    private static final TrackedData<Integer> LIVING_TICKS = DataTracker.registerData(MorbusProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(LIVING_TICKS, 0);
    }

    public void tick() {
        super.tick();
        if(!world.isClient()) {
            if(getDataTracker().get(LIVING_TICKS) > 12*20) {
                kill();
            }
            getDataTracker().set(LIVING_TICKS, getDataTracker().get(LIVING_TICKS) + 1);
        } else {
            Vec3d velocity = getVelocity();
            double x = getX() + velocity.x;
            double y = getY() + velocity.y;
            double z = getZ() + velocity.z;

            if (!isTouchingWater()) {
                world.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0, 0);
            } else {
                for(int i = 0; i < 4; ++i) {
                    world.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0, 0);
                }
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) { // TODO: Add config for effect values
        if(!(entityHitResult.getEntity() instanceof LivingEntity) || entityHitResult.getEntity() instanceof ArmorStandEntity) return;
        Util.applyEffectIfNotPresent((LivingEntity) entityHitResult.getEntity(), StatusEffects.WITHER, 12, 2);
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    @Override
    public boolean shouldRender(double distance) {
        return false;
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return PacketRegistry.createS2CThrownItemPacket(this, PacketRegistry.MORBUS_PROJECTILE_ID);
    }

}