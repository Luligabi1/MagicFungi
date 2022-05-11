package me.luligabi.magicfungi.common.screenhandler.misc;

import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;

public class MorbusClockScreenHandler extends ScreenHandler {

    public MorbusClockScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory);
        isImminent = buf.readBoolean();
        daysLeft = buf.readLong();
        startingDay = buf.readInt();
    }

    public MorbusClockScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ScreenHandlingRegistry.MORBUS_CLOCK_SCREEN_HANDLER, syncId);
        this.playerInventory = playerInventory;
        isImminent = false;
        daysLeft = 0;
        startingDay = 0;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


    public boolean isImminent() {
        return isImminent;
    }

    public long getDaysLeft() {
        return daysLeft;
    }

    public int getStartingDay() {
        return startingDay;
    }


    private final PlayerInventory playerInventory;
    private boolean isImminent;
    private long daysLeft;
    private int startingDay;

}
