package me.luligabi.magicfungi.common.item.glyph;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface GlyphExecutor {

    void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack);

    void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity);

}