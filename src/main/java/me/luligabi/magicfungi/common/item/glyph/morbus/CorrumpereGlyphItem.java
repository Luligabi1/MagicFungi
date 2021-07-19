package me.luligabi.magicfungi.common.item.glyph.morbus;

import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

public class CorrumpereGlyphItem extends BaseGlyphItem {

    public CorrumpereGlyphItem(Item.Settings settings) {
        super(settings);
        setMushroomType(MushroomType.MORBUS);
        setSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
    }

    @Override
    protected boolean executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {
        if(livingEntity instanceof VillagerEntity) { //TODO: Fix this not working
            ((VillagerEntity) livingEntity).convertTo(EntityType.ZOMBIE_VILLAGER, true);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
            return true;
        }
        if(livingEntity instanceof PiglinEntity) {
            ((PiglinEntity) livingEntity).convertTo(EntityType.ZOMBIFIED_PIGLIN, true);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
            return true;
        }
        if(livingEntity instanceof HoglinEntity) {
            ((HoglinEntity) livingEntity).convertTo(EntityType.ZOGLIN, true);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
            return true;
        }
        return false;
    }

    @Override
    protected boolean executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        return false;
    }
}