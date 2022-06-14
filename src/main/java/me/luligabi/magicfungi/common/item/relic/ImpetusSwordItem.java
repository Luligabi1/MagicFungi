package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ImpetusSwordItem extends SwordItem implements Chargeable {

    public ImpetusSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);

        if(user.isSneaking() && isChargeFull(stack)) {
            double x, z;
            for (int i = 0; i < 360; i += 30) {
                x = 2 * Math.sin(i);
                z = 2 * Math.cos(i);

                SmallFireballEntity fireball = new SmallFireballEntity(world, user, x, 0, z) {

                    @Override
                    protected void onEntityHit(EntityHitResult entityHitResult) {
                        if (!world.isClient()) {
                            Entity entity = entityHitResult.getEntity();
                            if (entity.isFireImmune()) return;
                            Entity owner = this.getOwner();
                            int fireTicks = entity.getFireTicks();
                            entity.setOnFireFor(18);
                            boolean isFireResistant = entity.damage(DamageSource.fireball(this, owner), 5.0F);
                            if (!isFireResistant) {
                                entity.setFireTicks(fireTicks);
                            } else if (owner instanceof LivingEntity) {
                                this.applyDamageEffects((LivingEntity) owner, entity);
                            }
                        }
                    }
                };
                world.spawnEntity(fireball);
            }
        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1F, 1F);
        resetCharge(stack, user);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        increaseCharge(stack);
        return super.postHit(stack, target, attacker);
    }


    @Override
    public int getMaxCharge() {
        return 96;
    }

    @Override
    public int getChargeBarColor(ItemStack stack) {
        return 0xFF5555;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return isChargeFull(stack); }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}