package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;

public class TestSpell extends SpellBaseItem {

    public TestSpell(Settings settings) {
        super(settings);
        setSound(SoundEvents.BLOCK_BEACON_ACTIVATE);
        setCooldown(40*90);
        setMushroomType(MushroomType.IMPETUS);
    }

    @Override
    public void executeSpell(PlayerEntity playerEntity) {
        System.out.println(getMushroomType().getFancyName());
    }
}