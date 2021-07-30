package me.luligabi.magicfungi.common.item.glyph.vivifica;

import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.mixin.ZombieVillagerEntityInvoker;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;

public class PudicitiamGlyphItem extends BaseGlyphItem {

    public PudicitiamGlyphItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.VIVIFICA);
        setSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
        setActionType(ActionType.ENTITY);
    }

    @Override
    protected void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {
        if(livingEntity instanceof  ZombieVillagerEntity) {
            ((ZombieVillagerEntityInvoker) livingEntity).invokeSetConverting(playerEntity.getUuid(), 0);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
            return;
        }
        if(livingEntity instanceof ZombifiedPiglinEntity) {
            ((ZombifiedPiglinEntity) livingEntity).convertTo(EntityType.PIGLIN, true);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
            return;
        }
        if(livingEntity instanceof ZoglinEntity) {
            ((ZoglinEntity) livingEntity).convertTo(EntityType.HOGLIN, true);
            super.executeEntityGlyph(playerEntity, itemStack, livingEntity);
        }
    }

}