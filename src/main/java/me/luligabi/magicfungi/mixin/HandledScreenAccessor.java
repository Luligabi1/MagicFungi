package me.luligabi.magicfungi.mixin;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {

    @Mutable
    @Accessor("playerInventoryTitle")
    void setPlayerInventoryTitle(Text playerInventoryTitle);
}
