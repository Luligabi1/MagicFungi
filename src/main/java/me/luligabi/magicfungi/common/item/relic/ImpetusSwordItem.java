package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ImpetusSwordItem extends SwordItem implements SpecialChargeRelic {

    public ImpetusSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);

        // TODO: Finish charge attack
        //if (user.isSneaking() && isChargeFull(stack)) {
            SmallFireballEntity fireball = new SmallFireballEntity(world, user, user.getRotationVector().x, user.getRotationVector().y, user.getRotationVector().z);
            fireball.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(fireball);
            resetCharge(stack);
        //}
        return TypedActionResult.success(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!isChargeFull(stack)) increaseCharge(stack);

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