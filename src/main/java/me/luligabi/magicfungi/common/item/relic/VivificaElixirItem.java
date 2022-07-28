package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VivificaElixirItem extends Item implements Chargeable {

    public VivificaElixirItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof PlayerEntity) {
            user.eatFood(world, stack);
            ((PlayerEntity) user).getItemCooldownManager().set(this, 150*20);
            increaseCharge(stack);
        }
        return stack.copy();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if(!world.isClient()) {
            if(user.isSneaking() && isChargeFull(stack)) {
                for(Entity entities : world.getOtherEntities(null, new Box(user.getX() - 5, user.getY() - 5, user.getZ() - 5, user.getX() + 5, user.getY() + 5, user.getZ() + 5))) {
                    if(entities instanceof PlayerEntity || entities instanceof TameableEntity) {
                        ((LivingEntity) entities).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2, false, true, true));
                        ((LivingEntity) entities).addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 18*20, 0, false, true, true));
                    }
                }
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1F, 1F);
                resetCharge(stack, user);
                user.getItemCooldownManager().set(this, 16*20);
                return TypedActionResult.success(stack);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        appendQuote(tooltip, MushroomType.VIVIFICA, Text.translatable("tooltip.magicfungi.vivifica_elixir.author"),
                Text.translatable("tooltip.magicfungi.vivifica_elixir.1"),
                Text.translatable("tooltip.magicfungi.vivifica_elixir.2"));
        appendChargeLevel(tooltip, stack, MushroomType.VIVIFICA);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
    }

    @Override
    public int getMaxCharge() {
        return MagicFungi.CONFIG.vivificaElixirMaxCharge;
    }

    @Override
    public int getChargeBarColor(ItemStack stack) {
        return 0x55FF55;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return isChargeFull(stack); }

}