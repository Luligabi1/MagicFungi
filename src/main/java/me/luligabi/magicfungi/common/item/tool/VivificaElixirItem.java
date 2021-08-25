package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class VivificaElixirItem extends Item {

    public VivificaElixirItem(Settings settings) {
        super(settings);
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
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof PlayerEntity) {
            user.eatFood(world, stack);
            ((PlayerEntity) user).getItemCooldownManager().set(this, 150*20);
        }
        return new ItemStack(ItemRegistry.VIVIFICA_ELIXIR);
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }
}