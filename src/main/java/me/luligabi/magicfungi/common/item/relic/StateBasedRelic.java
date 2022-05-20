package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.Collections;

public interface StateBasedRelic<T> {


    T getState(ItemStack stack);

    void sendStateChangeMessage(PlayerEntity player, T state);


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
