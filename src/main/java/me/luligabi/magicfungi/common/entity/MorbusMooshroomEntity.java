package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class MorbusMooshroomEntity extends AnimalEntity implements Monster, Angerable {


    private static final TrackedData<Boolean> WARNING;
    private int warningSoundCooldown;
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID targetUuid;

    public MorbusMooshroomEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AttackGoal());
        this.goalSelector.add(1, new MorbusMooshroomEscapeDangerGoal());
        this.goalSelector.add(5, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.targetSelector.add(1, new MorbusMooshroomRevengeGoal());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, LivingEntity.class, 15, true, false, livingEntity ->
                !(livingEntity instanceof MorbusMooshroomEntity) &&
                !(livingEntity instanceof WaterCreatureEntity) &&
                !(livingEntity instanceof FlyingEntity) &&
                !(livingEntity instanceof WitherEntity) &&
                !(livingEntity instanceof WitherSkeletonEntity) &&
                !(livingEntity instanceof EndermanEntity) &&
                !(livingEntity instanceof ArmorStandEntity)));
        this.targetSelector.add(3, new UniversalAngerGoal<>(this, false));
    }


    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.world, nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(BlockRegistry.HOST_GRASS_BLOCK) ? 10.0F : world.getDimension().getBrightness(world.getLightLevel(pos)) - 0.5F;
    }

    public static boolean canSpawn(EntityType<MorbusMooshroomEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(TagRegistry.MORBUS_MOOSHROOMS_SPAWNABLE_ON) && world.getBaseLightLevel(pos, 0) > 8;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Override
    public void setAngerTime(int ticks) {
        this.angerTime = ticks;
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    @Nullable
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    // TODO: Add custom SoundEvents.
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    private void playWarningSound() {
        if (this.warningSoundCooldown <= 0) {
            this.playSound(SoundEvents.ENTITY_POLAR_BEAR_WARNING, 1.0F, this.getSoundPitch());
            this.warningSoundCooldown = 40;
        }

    }

    @Nullable
    @Override // No children :)
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WARNING, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.warningSoundCooldown > 0) {
            --this.warningSoundCooldown;
        }

        if (!this.world.isClient) {
            this.tickAngerLogic((ServerWorld) this.world, true);
        }

    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(DamageSource.mob(this), (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
        if (bl) {
            this.applyDamageEffects(this, target);
        }

        return bl;
    }

    public void setWarning(boolean warning) {
        this.dataTracker.set(WARNING, warning);
    }

    @Override
    protected float getBaseMovementSpeedMultiplier() {
        return 0.98F;
    }

    static {
        WARNING = DataTracker.registerData(MorbusMooshroomEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }


    private class AttackGoal extends MeleeAttackGoal {

        public AttackGoal() {
            super(MorbusMooshroomEntity.this, 1.25D, true);
        }

        @Override
        protected void attack(LivingEntity target, double squaredDistance) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.isCooledDown()) {
                this.resetCooldown();
                this.mob.tryAttack(target);
                MorbusMooshroomEntity.this.setWarning(false);
            } else if (squaredDistance <= d * 2.0D) {
                if (this.isCooledDown()) {
                    MorbusMooshroomEntity.this.setWarning(false);
                    this.resetCooldown();
                }

                if (this.getCooldown() <= 10) {
                    MorbusMooshroomEntity.this.setWarning(true);
                    MorbusMooshroomEntity.this.playWarningSound();
                }
            } else {
                this.resetCooldown();
                MorbusMooshroomEntity.this.setWarning(false);
            }

        }

        @Override
        public void stop() {
            MorbusMooshroomEntity.this.setWarning(false);
            super.stop();
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0F + entity.getWidth();
        }
    }

    class MorbusMooshroomEscapeDangerGoal extends EscapeDangerGoal {

        public MorbusMooshroomEscapeDangerGoal() {
            super(MorbusMooshroomEntity.this, 2.0D);
        }

        @Override
        public boolean canStart() {
            return super.canStart() && MorbusMooshroomEntity.this.isOnFire();
        }
    }

    class MorbusMooshroomRevengeGoal extends RevengeGoal {

        public MorbusMooshroomRevengeGoal() {
            super(MorbusMooshroomEntity.this);
        }

        @Override
        protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
            if (mob instanceof MorbusMooshroomEntity) {
                super.setMobEntityTarget(mob, target);
            }

        }
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return super.canHaveStatusEffect(effect) && effect.getEffectType() != StatusEffects.WITHER;
    }

    @Override
    protected boolean isDisallowedInPeaceful() {
        return false;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL)) {
            player.playSound(SoundEvents.ENTITY_MOOSHROOM_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, ItemRegistry.MORBUS_MUSHROOM_STEW.getDefaultStack());
            player.setStackInHand(hand, itemStack2);
            return ActionResult.success(world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

}