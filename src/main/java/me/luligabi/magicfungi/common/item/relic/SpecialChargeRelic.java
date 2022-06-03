package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtByte;

public interface SpecialChargeRelic {

    default int getCharge(ItemStack stack) {
        return Byte.toUnsignedInt(stack.getOrCreateNbt().getByte("Charge"));
    }

    default void setCharge(ItemStack stack, int charge) {
        stack.setSubNbt("Charge", NbtByte.of((byte) charge));
    }

    int getMaxCharge();

    default void increaseCharge(ItemStack stack) {
        setCharge(stack, getCharge(stack) + 1);
    }

    default void resetCharge(ItemStack stack) {
        setCharge(stack, 0);
    }

    default boolean isChargeFull(ItemStack stack) {
        return getCharge(stack) >= getMaxCharge(); // >= in case this value changes on the future
    }

    // Charge bar's rendering
    default int getChargeBarStep(ItemStack stack) {
        return Math.min(Math.round(((float) getCharge(stack) / (float) getMaxCharge()) * 16.0F), 16);
    }

    int getChargeBarColor(ItemStack stack);

}