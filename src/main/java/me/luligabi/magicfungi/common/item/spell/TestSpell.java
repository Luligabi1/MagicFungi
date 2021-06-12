package me.luligabi.magicfungi.common.item.spell;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.sound.SoundEvents;

public class TestSpell extends SpellBaseItem {

    public TestSpell(Settings settings) {
        super(settings);
        setSoundEvent(SoundEvents.BLOCK_BEACON_ACTIVATE);
        setCooldown(40);
        setMushroomType(MushroomType.IMPETUS);
    }

    @Override
    public void executeSpell() {
        System.out.println(getMushroomType().getFancyName());
    }
}