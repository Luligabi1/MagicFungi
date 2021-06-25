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
        setSound(SoundEvents.ITEM_FIRECHARGE_USE);
    }

    @Override
    protected void executeSpell(PlayerEntity playerEntity, World world) {
        double e = playerEntity.getX() - playerEntity.getX();
        double f = playerEntity.getBodyY(0.5D) - playerEntity.getBodyY(0.5D);
        double g = playerEntity.getZ() - playerEntity.getZ();
        double h = Math.sqrt(Math.sqrt(playerEntity.getX())) * 0.5D;

        SmallFireballEntity smallFireballEntity = new SmallFireballEntity(world, playerEntity, e + playerEntity.getRandom().nextGaussian() * h, f, g + playerEntity.getRandom().nextGaussian() * h);
        smallFireballEntity.setPosition(smallFireballEntity.getX(), playerEntity.getBodyY(0.5D) + 0.5D, smallFireballEntity.getZ());
        world.spawnEntity(smallFireballEntity);
        super.executeSpell(playerEntity, world);
    }
}
