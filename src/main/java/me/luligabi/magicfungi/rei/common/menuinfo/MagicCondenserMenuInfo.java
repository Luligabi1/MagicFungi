package me.luligabi.magicfungi.rei.common.menuinfo;

import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import me.luligabi.magicfungi.rei.common.display.MagicCondenserDisplay;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.clean.InputCleanHandler;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;

import java.util.List;

public class MagicCondenserMenuInfo implements SimplePlayerInventoryMenuInfo<MagicCondenserScreenHandler, MagicCondenserDisplay> {


    public MagicCondenserMenuInfo(MagicCondenserDisplay display) {
        this.display = display;
    }

    protected final MagicCondenserDisplay display;


    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<MagicCondenserScreenHandler, ?, MagicCondenserDisplay> context) {
        return List.of(SlotAccessor.fromContainer(context.getMenu().inventory, 0));
    }

    public InputCleanHandler<MagicCondenserScreenHandler, MagicCondenserDisplay> getInputCleanHandler() {
        return context -> {
            MagicCondenserScreenHandler handler = context.getMenu();
            for (SlotAccessor gridStack : getInputSlots(context)) {
                InputCleanHandler.returnSlotsToPlayerInventory(context, getDumpHandler(), gridStack);
            }

            clearInputSlots(handler);
        };
    }

    @Override
    public MagicCondenserDisplay getDisplay() {
        return display;
    }

}