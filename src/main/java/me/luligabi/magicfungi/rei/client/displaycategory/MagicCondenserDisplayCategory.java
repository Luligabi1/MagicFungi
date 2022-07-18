package me.luligabi.magicfungi.rei.client.displaycategory;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.rei.client.widget.TooltippedArrow;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.MagicCondenserDisplay;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MagicCondenserDisplayCategory implements DisplayCategory<MagicCondenserDisplay> {


    @SuppressWarnings("UnstableApiUsage")
    @Override
    public List<Widget> setupDisplay(MagicCondenserDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 41, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(new TooltippedArrow(new Point(startPoint.x + 32, startPoint.y + 4),
                tooltip -> tooltip.add(new TranslatableText("category.rei.campfire.time", 32)))
                .animationDurationTicks(32*20));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 66, startPoint.y + 5)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 9, startPoint.y + 5)).entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 66, startPoint.y + 5)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> { // TODO: add tooltip to widgets
            RenderSystem.setShaderTexture(0, REIRuntime.getInstance().isDarkThemeEnabled() ? TEXTURE_DARK : TEXTURE);

            int fuelBarLength = MathHelper.clamp((18 * (display.getNetherStarFuelCost()*2) + 20 - 1) / 20, 0, 18);
            if(fuelBarLength > 0) {
                helper.drawTexture(matrices, startPoint.x + 92, startPoint.y + 21, 0, 14, 20, 6);
                helper.drawTexture(matrices, startPoint.x + 93, startPoint.y + 22, 0, 10, display.getNetherStarFuelCost(), 4);
            }
        }));
        if(display.usesEssences()) {
            widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
                RenderSystem.setShaderTexture(0, REIRuntime.getInstance().isDarkThemeEnabled() ? TEXTURE_DARK : TEXTURE);

                renderEssenceBar(helper, matrices, startPoint, display.getImpetusEssenceCost(), 30, 0);
                renderEssenceBar(helper, matrices, startPoint, display.getClypeusEssenceCost(), 23, 4);
                renderEssenceBar(helper, matrices, startPoint, display.getUtilisEssenceCost(), 16, 8);
                renderEssenceBar(helper, matrices, startPoint, display.getVivificaEssenceCost(), 9, 12);
                renderEssenceBar(helper, matrices, startPoint, display.getMorbusEssenceCost(), 2, 16);
            }));
        }
        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockRegistry.MAGIC_CONDENSER_BLOCK);
    }

    @Override
    public Text getTitle() {
        return new TranslatableText("title.magicfungi.magic_condenser.2");
    }

    @Override
    public int getDisplayHeight() {
        return 36;
    }

    @Override
    public CategoryIdentifier<? extends MagicCondenserDisplay> getCategoryIdentifier() {
        return CommonReiPlugin.MAGIC_CONDENSING;
    }


    private void renderEssenceBar(DrawableHelper helper, MatrixStack matrices, Point startPoint, int essenceAmount, int xOffset, int textureOffset) {
        helper.drawTexture(matrices, startPoint.x - xOffset, startPoint.y + 15, 20, 0, 6, 12);
        int essenceBarHeight = essenceAmount != 1 ? essenceAmount/2 : 1;
        if(essenceBarHeight > 0) {
            helper.drawTexture(matrices, (startPoint.x - xOffset) + 1, startPoint.y + 16 + 10 - essenceBarHeight, textureOffset, 10 - essenceBarHeight, 4, essenceBarHeight);
        }
    }

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/magic_condenser/magic_condenser.png");
    private static final Identifier TEXTURE_DARK = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/magic_condenser/magic_condenser_dark.png");

}