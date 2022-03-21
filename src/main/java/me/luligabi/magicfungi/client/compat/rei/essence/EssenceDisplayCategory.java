package me.luligabi.magicfungi.client.compat.rei.essence;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.client.compat.rei.ReiPlugin;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class EssenceDisplayCategory implements DisplayCategory<EssenceRecipeDisplay> {


    @Override
    public List<Widget> setupDisplay(EssenceRecipeDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 52, bounds.getCenterY() - 29);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            RenderSystem.setShaderTexture(0, REIRuntime.getInstance().isDarkThemeEnabled() ? TEXTURE_DARK : TEXTURE);
            helper.drawTexture(matrices, startPoint.x, startPoint.y, 0, 0, 103, 59);
            int width = MathHelper.ceil(System.currentTimeMillis() / 250d % 18d);
            helper.drawTexture(matrices, startPoint.x + 44, startPoint.y + 28, 103, getCatalystBarTextureYCoordinate(display), width, 4);
        }));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 63, startPoint.y + 1)).entries(display.getInputEntries().get(0)).disableBackground().markInput()); // Input
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 1)).entries(getCatalyst(display)).disableBackground().markInput()); // Catalyst

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 40, startPoint.y + 35)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput()); // Outputs
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 63, startPoint.y + 42)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 86, startPoint.y + 35)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        return widgets;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(BlockRegistry.ESSENCE_EXTRACTOR_BLOCK);
    }

    @Override
    public Text getTitle() {
        return new TranslatableText("title.magicfungi.essence_extractor");
    }

    @Override
    public CategoryIdentifier<? extends EssenceRecipeDisplay> getCategoryIdentifier() {
        return ReiPlugin.ESSENCE_EXTRACTION;
    }


    // oh this'll be a bitch to port to 1.18.2+
    private List<EntryStack<ItemStack>> getCatalyst(EssenceRecipeDisplay display) {
        switch(display.catalystType) {
            case IMPETUS -> {
                if(impetusCatalystList == null) {
                    impetusCatalystList = new ArrayList<>();
                    TagRegistry.IMPETUS_CATALYST.values().forEach(item -> impetusCatalystList.add(EntryStacks.of(new ItemStack(item))));
                }
                return impetusCatalystList;
            }
            case CLYPEUS -> {
                if(clypeusCatalystList == null) {
                    clypeusCatalystList = new ArrayList<>();
                    TagRegistry.CLYPEUS_CATALYST.values().forEach(item -> clypeusCatalystList.add(EntryStacks.of(new ItemStack(item))));
                }
                return clypeusCatalystList;
            }
            case UTILIS -> {
                if(utilisCatalystList == null) {
                    utilisCatalystList = new ArrayList<>();
                    TagRegistry.UTILIS_CATALYST.values().forEach(item -> utilisCatalystList.add(EntryStacks.of(new ItemStack(item))));
                }
                return utilisCatalystList;
            }
            case VIVIFICA -> {
                if(vivificaCatalystList == null) {
                    vivificaCatalystList = new ArrayList<>();
                    TagRegistry.VIVIFICA_CATALYST.values().forEach(item -> vivificaCatalystList.add(EntryStacks.of(new ItemStack(item))));
                }
                return vivificaCatalystList;
            }
            case MORBUS -> {
                if(morbusCatalystList == null) {
                    morbusCatalystList = new ArrayList<>();
                    TagRegistry.MORBUS_CATALYST.values().forEach(item -> morbusCatalystList.add(EntryStacks.of(new ItemStack(item))));
                }
                return morbusCatalystList;
            }
            default -> throw new UnsupportedOperationException("Catalyst Type outside valid options!");
        }
    }


    private int getCatalystBarTextureYCoordinate(EssenceRecipeDisplay display) {
        return switch(display.catalystType) {
            case IMPETUS -> 29;
            case CLYPEUS -> 33;
            case UTILIS -> 37;
            case VIVIFICA -> 41;
            case MORBUS -> 45;
            default -> 128;
        };
    }

    private List<EntryStack<ItemStack>> impetusCatalystList;
    private List<EntryStack<ItemStack>> clypeusCatalystList;
    private List<EntryStack<ItemStack>> utilisCatalystList;
    private List<EntryStack<ItemStack>> vivificaCatalystList;
    private List<EntryStack<ItemStack>> morbusCatalystList;

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/essence_extractor.png");
    private static final Identifier TEXTURE_DARK = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/essence_extractor_dark.png");

}