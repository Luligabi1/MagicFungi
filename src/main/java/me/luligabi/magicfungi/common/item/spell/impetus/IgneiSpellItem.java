package me.luligabi.magicfungi.common.item.spell.impetus;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.spell.BaseSpellItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class IgneiSpellItem extends BaseSpellItem {

    public IgneiSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.IMPETUS);
        setCooldown(MagicFungi.CONFIG.igneiSpellCooldown*20);
        setSound(SoundEvents.ITEM_FLINTANDSTEEL_USE);
        setActionType(ActionType.WORLD);
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