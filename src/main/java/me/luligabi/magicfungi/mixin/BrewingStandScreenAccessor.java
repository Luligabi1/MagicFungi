package me.luligabi.magicfungi.mixin;

import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BrewingStandScreen.class)
public interface BrewingStandScreenAccessor {

    @Accessor("BUBBLE_PROGRESS")
    static int[] getBubbleProgress() {
        throw new AssertionError();
    }

}