package me.luligabi.magicfungi.rei.common.menuinfo;

import me.luligabi.magicfungi.rei.common.display.GlyphRecipeDisplay;
import me.luligabi.magicfungi.common.screenhandler.glyph.GlyphCarvingScreenHandler;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.clean.InputCleanHandler;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;

import java.util.ArrayList;
import java.util.List;

public class GlyphMenuInfo implements SimplePlayerInventoryMenuInfo<GlyphCarvingScreenHandler, GlyphRecipeDisplay> {


    public GlyphMenuInfo(GlyphRecipeDisplay display) {
        this.display = display;
    }

    protected final GlyphRecipeDisplay display;


    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<GlyphCarvingScreenHandler, ?, GlyphRecipeDisplay> context) {
        List<SlotAccessor> list = new ArrayList<>(4);
        for (int i = 0; i <= 3; i++) {
            list.add(SlotAccessor.fromContainer(context.getMenu().input, i));
        }

        return list;
    }

    public InputCleanHandler<GlyphCarvingScreenHandler, GlyphRecipeDisplay> getInputCleanHandler() {
        return context -> {
            GlyphCarvingScreenHandler handler = context.getMenu();
            for (SlotAccessor gridStack : getInputSlots(context)) {
                InputCleanHandler.returnSlotsToPlayerInventory(context, getDumpHandler(), gridStack);
            }

            clearInputSlots(handler);
        };
    }

    @Override
    public GlyphRecipeDisplay getDisplay() {
        return display;
    }

}
