package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.misc.PacketRegistry;
import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class UtilisLaserEntity extends ThrownItemEntity {

    public UtilisLaserEntity(EntityType<UtilisLaserEntity> entityType, World world) {
        super(entityType, world);
    }

    public UtilisLaserEntity(World world, LivingEntity owner) {
        super(EntityRegistry.UTILIS_LASER, owner, world);
    }


    private static final TrackedData<Integer> LIVING_TICKS = DataTracker.registerData(UtilisLaserEntity.class, TrackedDataHandlerRegistry.INTEGER);

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
                world.addParticle(ParticleRegistry.UTILIS_LASER, x, y, z, 0, 0, 0);
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
        if(world.getBlockState(blockHitResult.getBlockPos()).getBlock().getHardness() >= 0.01F) {
            world.breakBlock(blockHitResult.getBlockPos(), true);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        entityHitResult.getEntity().damage(DamageSource.MAGIC, 0F);
    }


    @Override
    public void kill() {
        super.kill();
        world.playSound(null, getX(), getY(), getZ(),
                SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.PLAYERS, 1F, 1F);
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
        return PacketRegistry.createS2CUtilisLaserPacket(this);
    }

}