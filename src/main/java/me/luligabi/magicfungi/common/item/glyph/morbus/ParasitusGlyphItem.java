package me.luligabi.magicfungi.common.item.glyph.morbus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

public class ParasitusGlyphItem extends BaseGlyphItem {

    public ParasitusGlyphItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.MORBUS);
        setSound(SoundEvents.ENTITY_WITHER_DEATH);
        setActionType(ActionType.ENTITY);
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {
        if(livingEntity instanceof PassiveEntity) {
            if (playerEntity.getHealth() <= playerEntity.getMaxHealth()) {
                Util.applyEffectIfNotPresent(livingEntity, StatusEffects.WITHER, MagicFungi.CONFIG.parasitusGlyphEffectTime, 0);
                Util.applyEffectIfNotPresent(playerEntity, StatusEffects.REGENERATION, MagicFungi.CONFIG.parasitusGlyphEffectTime, 0);
                super.executeGlyph(playerEntity, itemStack);
            }
        }
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {}

}