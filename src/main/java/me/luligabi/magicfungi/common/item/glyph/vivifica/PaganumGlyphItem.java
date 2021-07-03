package me.luligabi.magicfungi.common.item.glyph.vivifica;

import me.luligabi.magicfungi.common.item.glyph.GlyphBaseItem;
import me.luligabi.magicfungi.common.mixin.ZombieVillagerEntityInvoker;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;

public class PaganumGlyphItem extends GlyphBaseItem {

    public PaganumGlyphItem(Settings settings) { //TODO: Probably change the name
        super(settings);
        setMushroomType(MushroomType.VIVIFICA);
        setSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
    }

    @Override //TODO: Add more convertable-mobs for glyph action
    protected boolean executeEntityGlyph(PlayerEntity playerEntity, LivingEntity livingEntity) {
        if(livingEntity instanceof  ZombieVillagerEntity) {
            ((ZombieVillagerEntityInvoker) livingEntity).invokeSetConverting(
                    playerEntity.getUuid(), livingEntity.getRandom().nextInt(2401) + 3600);
            super.executeEntityGlyph(playerEntity, livingEntity);
            return true;
        }
        return false;
    }

    @Override
    protected boolean executeBlockGlyph(PlayerEntity playerEntity) {
        return false;
    }
}