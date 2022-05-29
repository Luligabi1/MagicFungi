package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.misc.PacketRegistry;
import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class UtilisLaserEntity extends ThrownItemEntity {

    public UtilisLaserEntity(EntityType<UtilisLaserEntity> entityType, World world) {
        super(entityType, world);
    }

    public UtilisLaserEntity(World world, LivingEntity owner) {
        super(EntityRegistry.UTILIS_LASER, owner, world);
    }

    public UtilisLaserEntity(World world, double x, double y, double z) {
        super(EntityRegistry.UTILIS_LASER, x, y, z, world);
    }

    @Override // TODO: replace placeholder item
    protected Item getDefaultItem() {
        return Items.EGG;
    }

    public void tick() {
        super.tick();
        Vec3d velocity = getVelocity();
        double x = getX() + velocity.x;
        double y = getY() + velocity.y;
        double z = getZ() + velocity.z;
        float offset = 0.25F;

        if (!this.isTouchingWater()) {
            this.world.addParticle(ParticleRegistry.UTILIS_LASER, x - velocity.x * offset + random.nextDouble() * 0.6 - 0.3, y - velocity.y * offset - 0.5, z - velocity.z * offset + this.random.nextDouble() * 0.6 - 0.3, velocity.x, velocity.y, velocity.z);
        } else {
            for(int i = 0; i < 4; ++i) {
                this.world.addParticle(ParticleTypes.BUBBLE, x - velocity.x * offset, y - velocity.y * offset, z - velocity.z * offset, velocity.x, velocity.y, velocity.z);
            }
        }
    }



    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.magic(this, this.getOwner()), 0F);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if(world.getBlockState(blockHitResult.getBlockPos()).getBlock().getHardness() >= 0.01F) {
            world.breakBlock(blockHitResult.getBlockPos(), true);
        }
    }

    protected void onCollision(HitResult hitResult) { // TODO: Add check to kill laser after hitting a quota of blocks destroyed
        super.onCollision(hitResult);
        /*if(!world.isClient) {
            kill();
        }*/
    }

    @Override
    protected float getGravity() {
        return 0.0F;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return PacketRegistry.createS2CUtilisLaserPacket(this);
    }

}