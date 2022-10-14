package me.luligabi.magicfungi.rei.client.displaycategory;

import com.google.common.collect.Lists;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.rei.client.widget.TooltippedArrow;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.MoldingCauldronDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Collections;
import java.util.List;

public class MoldingCauldronDisplayCategory implements DisplayCategory<MoldingCauldronDisplay> {

    @Override
    public List<Widget> setupDisplay(MoldingCauldronDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 31, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(new TooltippedArrow(new Point(startPoint.x - 4, startPoint.y + 4),
                List.of(Text.translatable("tooltip.magicfungi.molding_cauldron.input").formatted(Formatting.GRAY))));
        widgets.add(new TooltippedArrow(new Point(startPoint.x + 42, startPoint.y + 4),
                List.of(Text.translatable("tooltip.magicfungi.molding_cauldron.time").formatted(Formatting.GRAY)))
                .animationDurationTicks(30*20));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 73, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x - 23, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 23, startPoint.y + 5)).entries(Collections.singleton(EntryStacks.of(BlockRegistry.MOLDING_CAULDRON_BLOCK))).disableBackground().disableHighlight());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 73, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockRegistry.MOLDING_CAULDRON_BLOCK);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("title.magicfungi.molding");
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }

    @Override
    public CategoryIdentifier<? extends MoldingCauldronDisplay> getCategoryIdentifier() {
        return CommonReiPlugin.MOLDING;
    }

}