package me.luligabi.magicfungi.common.item.spell.impetus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class IgneiSpellItem extends AbstractSpellItem {

    public IgneiSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.IMPETUS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_FLINTANDSTEEL_USE;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.igneiSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.WORLD;
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        SmallFireballEntity fireball = new SmallFireballEntity(world, playerEntity, playerEntity.getRotationVector().x, playerEntity.getRotationVector().y, playerEntity.getRotationVector().z);
        fireball.setPos(fireball.getX(), fireball.getY() + 1.25F, fireball.getZ());
        fireball.setOwner(playerEntity);
        world.spawnEntity(fireball);
        super.executeSpell(playerEntity, world);
    }

}