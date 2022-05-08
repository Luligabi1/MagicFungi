package me.luligabi.magicfungi.common.screenhandler.misc;

import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class MorbusClockScreenHandler extends ScreenHandler {

    public MorbusClockScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ScreenHandlingRegistry.MORBUS_CLOCK_SCREEN_HANDLER, syncId);
        this.playerInventory = playerInventory;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    /*public void refresh(TranslatableText isEnabled, TranslatableText daysLeftText) {
        World world = playerInventory.player.getWorld();
        if (world.isClient()) return;
        if (world.getTimeOfDay() % 24000L != 0) return;

    }*/


    private final PlayerInventory playerInventory;

}
