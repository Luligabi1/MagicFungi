package me.luligabi.magicfungi.rei.client.displaycategory;

import com.google.common.collect.Lists;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.rei.client.widget.TooltippedArrow;
import me.luligabi.magicfungi.rei.client.widget.magiccondenser.EssenceCostWidget;
import me.luligabi.magicfungi.rei.client.widget.magiccondenser.NetherStarBarWidget;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.MagicCondenserDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class MagicCondenserDisplayCategory extends DrawableHelper implements DisplayCategory<MagicCondenserDisplay> {

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public List<Widget> setupDisplay(MagicCondenserDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(new TooltippedArrow(new Point(startPoint.x + 32, startPoint.y + 4),
                List.of(Text.translatable("category.rei.campfire.time", 32)))
                .animationDurationTicks(32*20));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 66, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 66, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        if(display.usesEssences()) {
            widgets.add(EssenceCostWidget.create(new Point(startPoint.x - 30, startPoint.y + 15), display, startPoint, this));
        }
        if(display.getNetherStarFuelCost() > 0) {
            widgets.add(NetherStarBarWidget.create(new Point(startPoint.x + 92, startPoint.y + 21), display, startPoint, this));
        }
        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockRegistry.MAGIC_CONDENSER_BLOCK);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("title.magicfungi.magic_condenser.2");
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }

    @Override
    public CategoryIdentifier<? extends MagicCondenserDisplay> getCategoryIdentifier() {
        return CommonReiPlugin.MAGIC_CONDENSING;
    }

    public static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/magic_condenser/magic_condenser.png");
    public static final Identifier TEXTURE_DARK = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/magic_condenser/magic_condenser_dark.png");

}