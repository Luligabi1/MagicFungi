package me.luligabi.magicfungi.rei.client.displaycategory;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.GlyphRecipeDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GlyphDisplayCategory implements DisplayCategory<GlyphRecipeDisplay> {

    @Override
    public List<Widget> setupDisplay(GlyphRecipeDisplay display, Rectangle bounds) {
        Point origin = new Point(bounds.getCenterX() - 58, bounds.getCenterY() - 27);

        List<Widget> widgets = new ArrayList<>();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createResultSlotBackground(new Point(origin.x + 20 + 1.75 * 18, origin.y + 1 * 18)));

        widgets.add(Widgets.createSlot(new Point(origin.x + 20 + 1.75 * 18, origin.y + -1 * 18)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(origin.x + 20 + -0.25 * 18, origin.y + 1 * 18)).entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(origin.x + 20 + 3.75 * 18, origin.y + 1 * 18)).entries(display.getInputEntries().get(2)).markInput());
        widgets.add(Widgets.createSlot(new Point(origin.x + 20 + 1.75 * 18, origin.y + 3 * 18)).entries(display.getInputEntries().get(3)).markInput());

        widgets.add(Widgets.createSlot(new Point(origin.x + 20 + 1.75 * 18, origin.y + 1 * 18)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        return widgets;
    }

    @Override
    public Renderer getIcon() { return EntryStacks.of(BlockRegistry.GLYPH_CARVING_BLOCK); }

    @Override
    public Text getTitle() {
        return Text.translatable("title.magicfungi.glyph_carving");
    }

    @Override
    public int getDisplayHeight() {
        return 100;
    }

    @Override
    public CategoryIdentifier<? extends GlyphRecipeDisplay> getCategoryIdentifier() { return CommonReiPlugin.GLYPH_CARVING; }

}