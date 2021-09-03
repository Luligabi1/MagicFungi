package me.luligabi.magicfungi.common.item.spell.clypeus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.BaseSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class CadereSpellItem extends BaseSpellItem {

    public CadereSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.CLYPEUS);
        setSound(SoundEvents.ENTITY_ENDER_DRAGON_FLAP);
        setCooldown(MagicFungi.CONFIG.cadereSpellCooldown*20);
        setActionType(ActionType.PLAYER);
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.SLOW_FALLING, MagicFungi.CONFIG.cadereSpellEffectTime, 1);
        super.executeSpell(playerEntity, world);
    }
}