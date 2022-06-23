package me.luligabi.magicfungi.common.item.spell.utilis;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CibusSpellItem extends AbstractSpellItem {

    public CibusSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.UTILIS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.ENTITY_PLAYER_BURP;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.cibusSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.PLAYER;
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