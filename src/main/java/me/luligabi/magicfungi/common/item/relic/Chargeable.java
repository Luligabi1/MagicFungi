package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtByte;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public interface Chargeable extends Relic {

    default int getCharge(ItemStack stack) {
        return Byte.toUnsignedInt(stack.getOrCreateNbt().getByte("Charge"));
    }

    default void setCharge(ItemStack stack, int charge) {
        stack.setSubNbt("Charge", NbtByte.of((byte) charge));
    }

    int getMaxCharge();

    default void increaseCharge(ItemStack stack) {
        if(!isChargeFull(stack)) increaseChargeUnsafe(stack);
    }

    default void increaseChargeUnsafe(ItemStack stack) {
        setCharge(stack, getCharge(stack) + 1);
    }

    default void resetCharge(ItemStack stack, PlayerEntity user) {
        if(!user.isCreative()) setCharge(stack, 0);
    }

    default boolean isChargeFull(ItemStack stack) {
        return getCharge(stack) >= getMaxCharge(); // >= in case this value changes on the future
    }

    default int getChargeBarStep(ItemStack stack) {
        return Math.min(Math.round(((float) getCharge(stack) / (float) getMaxCharge()) * 16.0F), 16);
    }

    int getChargeBarColor(ItemStack stack);

    default void appendChargeLevel(List<Text> tooltip, ItemStack stack, MushroomType mushroomType) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.charge_info.1")
                    .formatted(MushroomType.getDarkColor(mushroomType), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.charge_info.2", getCharge(stack), getMaxCharge())
                        .formatted(MushroomType.getLightColor(mushroomType))));
    }

}