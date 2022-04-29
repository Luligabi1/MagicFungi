package me.luligabi.magicfungi.common.item.glyph.utilis;

import me.luligabi.magicfungi.client.tooltip.glyph.GlyphTooltipData;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

// This is the only Glyph that doesn't extend BaseGlyphItem (as it's easier to use BlockItem's existing implementation in this case) so there's some hardcoding here. Sorry!
public class CadentisGlyphItem extends BlockItem {

    public CadentisGlyphItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public String getTranslationKey() { return this.getOrCreateTranslationKey(); }

    @Override // Mimics BaseGlyph's tooltip style
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!Screen.hasShiftDown()) return;
        MushroomType mushroomType = MushroomType.UTILIS;

        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                .formatted(MushroomType.getDarkColor(mushroomType), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", mushroomType.getFancyName(), mushroomType.getStatsName())
                        .formatted(MushroomType.getLightColor(mushroomType))));
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.5")
                .formatted(MushroomType.getDarkColor(mushroomType), Formatting.BOLD)
                .append(ActionType.BLOCK.getTranslatableText()
                        .formatted(MushroomType.getLightColor(mushroomType))));
    }

    @Override //
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new GlyphTooltipData(MushroomType.UTILIS, ActionType.BLOCK));
    }

}