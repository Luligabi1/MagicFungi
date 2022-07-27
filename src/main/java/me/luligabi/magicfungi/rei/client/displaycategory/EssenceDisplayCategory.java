package me.luligabi.magicfungi.rei.client.displaycategory;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.EssenceRecipeDisplay;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static me.luligabi.magicfungi.common.util.CatalystType.*;

public class EssenceDisplayCategory implements DisplayCategory<EssenceRecipeDisplay> {


    @SuppressWarnings("UnstableApiUsage")
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
        return CommonReiPlugin.ESSENCE_EXTRACTION;
    }


    private List<EntryStack<ItemStack>> getCatalyst(EssenceRecipeDisplay display) {
        switch(display.getCatalystType()) {
            case IMPETUS -> {
                addToCatalystList(IMPETUS.getTag(), impetusCatalystList);
                return impetusCatalystList;
            }
            case CLYPEUS -> {
                addToCatalystList(CLYPEUS.getTag(), clypeusCatalystList);
                return clypeusCatalystList;
            }
            case UTILIS -> {
                addToCatalystList(UTILIS.getTag(), utilisCatalystList);
                return utilisCatalystList;
            }
            case VIVIFICA -> {
                addToCatalystList(VIVIFICA.getTag(), vivificaCatalystList);
                return vivificaCatalystList;
            }
            case MORBUS -> {
                addToCatalystList(MORBUS.getTag(), morbusCatalystList);
                return morbusCatalystList;
            }
            default -> throw new UnsupportedOperationException("Catalyst Type outside valid options!");
        }
    }


    private int getCatalystBarTextureYCoordinate(EssenceRecipeDisplay display) {
        return switch(display.getCatalystType()) {
            case IMPETUS -> 29;
            case CLYPEUS -> 33;
            case UTILIS -> 37;
            case VIVIFICA -> 41;
            case MORBUS -> 45;

            default -> 128;
        };
    }

    private void addToCatalystList(TagKey<Item> tagKey, List<EntryStack<ItemStack>> catalystList) {
        if(catalystList.isEmpty()) {
            Registry.ITEM.getEntryList(tagKey).ifPresentOrElse(
                    registryEntries -> registryEntries.forEach(itemRegistryEntry -> catalystList.add(EntryStacks.of(new ItemStack(itemRegistryEntry.value())))),
                    () -> LogUtils.getLogger().error("Catalyst tag is empty!"));
        }
    }

    private final List<EntryStack<ItemStack>> impetusCatalystList = new ArrayList<>();
    private final List<EntryStack<ItemStack>> clypeusCatalystList = new ArrayList<>();
    private final List<EntryStack<ItemStack>> utilisCatalystList = new ArrayList<>();
    private final List<EntryStack<ItemStack>> vivificaCatalystList = new ArrayList<>();
    private final List<EntryStack<ItemStack>> morbusCatalystList = new ArrayList<>();


    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/essence_extractor/essence_extractor.png");
    private static final Identifier TEXTURE_DARK = new Identifier(MagicFungi.MOD_ID, "textures/gui/rei/essence_extractor/essence_extractor_dark.png");

}