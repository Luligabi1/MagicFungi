package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.Collections;

public interface StateBased<T> extends Relic {


    T getState(ItemStack stack);

    default void sendStateChangeMessage(PlayerEntity player, T state) {
        player.world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_NOTE_BLOCK_HARP,
                SoundCategory.PLAYERS, 200F, 1.5F);
    }

    default void applyDefaultEnchantment(ItemStack stack, T defaultState, Enchantment enchantment, int enchantmentLevel) {
        if(getState(stack) == defaultState && EnchantmentHelper.getLevel(enchantment, stack) != enchantmentLevel) {
            addEnchantment(stack, enchantment, enchantmentLevel);
        }
    }

    default void addEnchantment(ItemStack itemStack, Enchantment enchantment, int level) {
        EnchantmentHelper.set(Collections.singletonMap(enchantment, level), itemStack);
    }

    default ItemStack removeEnchantments(ItemStack itemStack, int damage) {
        ItemStack copyStack = itemStack.copy();
        copyStack.removeSubNbt("Enchantments");
        copyStack.removeSubNbt("StoredEnchantments");

        if (damage > 0) {
            copyStack.setDamage(damage);
        } else {
            copyStack.removeSubNbt("Damage");
        }

        return copyStack;
    }

}