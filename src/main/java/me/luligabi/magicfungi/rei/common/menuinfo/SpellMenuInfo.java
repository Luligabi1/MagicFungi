package me.luligabi.magicfungi.rei.common.menuinfo;

import me.luligabi.magicfungi.common.screenhandler.spell.SpellDiscoveryScreenHandler;
import me.luligabi.magicfungi.rei.common.display.SpellRecipeDisplay;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.clean.InputCleanHandler;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;

import java.util.ArrayList;
import java.util.List;

public class SpellMenuInfo implements SimplePlayerInventoryMenuInfo<SpellDiscoveryScreenHandler, SpellRecipeDisplay> {


    public SpellMenuInfo(SpellRecipeDisplay display) {
        this.display = display;
    }

    protected final SpellRecipeDisplay display;


    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<SpellDiscoveryScreenHandler, ?, SpellRecipeDisplay> context) {

        List<SlotAccessor> list = new ArrayList<>(8);
        for (int i = 0; i <= 7; i++) {
            list.add(SlotAccessor.fromContainer(context.getMenu().input, i));
        }

        return list;
    }

    public InputCleanHandler<SpellDiscoveryScreenHandler, SpellRecipeDisplay> getInputCleanHandler() {
        return context -> {
            SpellDiscoveryScreenHandler handler = context.getMenu();
            for (SlotAccessor gridStack : getInputSlots(context)) {
                InputCleanHandler.returnSlotsToPlayerInventory(context, getDumpHandler(), gridStack);
            }

            clearInputSlots(handler);
        };
    }

    @Override
    public SpellRecipeDisplay getDisplay() {
        return display;
    }

}