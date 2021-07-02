package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class IgneiSpellItem extends SpellBaseItem {

    public IgneiSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.IMPETUS);
        setCooldown(15*20);
        setSound(SoundEvents.ENTITY_BLAZE_AMBIENT);
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        SmallFireballEntity fireball = new SmallFireballEntity(world, playerEntity, playerEntity.getRotationVector().x, playerEntity.getRotationVector().y, playerEntity.getRotationVector().z);
        fireball.setPos(fireball.getX(), fireball.getY() + 0.75F, fireball.getZ());
        fireball.setOwner(playerEntity);
        world.spawnEntity(fireball);
        super.executeSpell(playerEntity, world);
    }
}