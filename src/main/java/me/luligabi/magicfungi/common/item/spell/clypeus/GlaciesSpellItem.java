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

public class GlaciesSpellItem extends AbstractSpellItem {

    public GlaciesSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.CLYPEUS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.BLOCK_SNOW_PLACE;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.glaciesSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.PLAYER;
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.RESISTANCE, MagicFungi.CONFIG.glaciesSpellEffectTime, 4);
        Util.applyEffectIfNotPresent(playerEntity, StatusEffects.SLOWNESS,  MagicFungi.CONFIG.glaciesSpellEffectTime, 2);
        playerEntity.setFrozenTicks((MagicFungi.CONFIG.glaciesSpellEffectTime+8)*20); //Additional 8 seconds to ensure the visual effects end alongside the buffs.
        super.executeSpell(playerEntity, world);
    }

}
