package me.luligabi.magicfungi.common.item.spell.impetus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.AbstractSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScintillamSpellItem extends AbstractSpellItem {

    public ScintillamSpellItem(Settings settings) {
        super(settings);
    }


    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.IMPETUS;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_FIRECHARGE_USE;
    }

    @Override
    public int getCooldown() {
        return MagicFungi.CONFIG.scintillamSpellCooldown*20;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.WORLD;
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        FireballEntity fireball = new FireballEntity(world, playerEntity, playerEntity.getRotationVector().x, playerEntity.getRotationVector().y, playerEntity.getRotationVector().z, 3);
        fireball.setPos(fireball.getX(), fireball.getY() + 0.75F, fireball.getZ());
        fireball.setOwner(playerEntity);
        world.spawnEntity(fireball);
        super.executeSpell(playerEntity, world);
    }

}