package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ScintillamSpellItem extends SpellBaseItem {

    public ScintillamSpellItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.IMPETUS);
        setCooldown(22*20);
        setSound(SoundEvents.ITEM_FIRECHARGE_USE);
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