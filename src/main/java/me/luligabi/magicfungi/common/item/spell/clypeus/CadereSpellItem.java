package me.luligabi.magicfungi.common.item.spell.clypeus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CadereSpellItem extends AbstractSpellItem {

    public CadereSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.CLYPEUS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.ENTITY_ENDER_DRAGON_FLAP;
    }


    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.cadereSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.PLAYER;
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.SLOW_FALLING, MagicFungi.CONFIG.cadereSpellEffectTime, 1);
        super.executeSpell(playerEntity, world);
    }

}