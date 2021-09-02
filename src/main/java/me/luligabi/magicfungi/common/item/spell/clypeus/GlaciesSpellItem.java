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

public class GlaciesSpellItem extends BaseSpellItem {

    public GlaciesSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.CLYPEUS);
        setSound(SoundEvents.BLOCK_SNOW_PLACE);
        setCooldown(MagicFungi.CONFIG.glaciesSpellCooldown*20);
        setActionType(ActionType.PLAYER);
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) { //TODO: Add config setting to customize duration of effects.
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.RESISTANCE, 10, 4);
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.SLOWNESS, 10, 2);
        playerEntity.setFrozenTicks(18*20);
        super.executeSpell(playerEntity, world);
    }
}
