package me.luligabi.magicfungi.common.item.spell.utilis;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.BaseSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class CibusSpellItem extends BaseSpellItem {

    public CibusSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.UTILIS);
        setSound(SoundEvents.ENTITY_PLAYER_BURP);
        setCooldown(MagicFungi.CONFIG.cibusSpellCooldown*20);
        setActionType(ActionType.PLAYER);
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        if(!playerEntity.getHungerManager().isNotFull()) return;
        playerEntity.getHungerManager().add(
                MagicFungi.CONFIG.cibusSpellHungerModifier,
                MagicFungi.CONFIG.cibusSpellSaturationModifier);
        super.executeSpell(playerEntity, world);
    }
}