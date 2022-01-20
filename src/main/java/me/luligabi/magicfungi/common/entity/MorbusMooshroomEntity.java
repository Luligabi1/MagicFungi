package me.luligabi.magicfungi.common.entity;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MorbusMooshroomEntity extends PathAwareEntity {

    public MorbusMooshroomEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
        this.goalSelector.add(8, new LookAroundGoal(this));
        initCustomGoals();
    }

    private void initCustomGoals() { // TODO: Add attack goal
        //this.goalSelector.add(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SkeletonEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, EndermanEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, ZombieVillagerEntity.class, true));
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL) && !this.isBaby()) {
            player.playSound(SoundEvents.ENTITY_MOOSHROOM_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, ItemRegistry.MORBUS_MUSHROOM_STEW.getDefaultStack());
            player.setStackInHand(hand, itemStack2);
            return ActionResult.success(world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    // TODO: Add custom SoundEvents.
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) { this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F); }

    protected float getSoundVolume() {
        return 0.4F;
    }
}