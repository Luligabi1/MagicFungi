package me.luligabi.magicfungi.common.item.glyph.utilis;

import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CadentisGlyphItem extends BlockItem {

    public CadentisGlyphItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public String getTranslationKey() { return this.getOrCreateTranslationKey(); }

    @Override // Mimics BaseGlyph's tooltip style
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        MushroomType mushroomType = MushroomType.UTILIS;

        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.1")
                .formatted(MushroomType.getDarkColor(mushroomType), Formatting.BOLD)
                .append(new TranslatableText("tooltip.magicfungi.spell_info.2", mushroomType.getFancyName(), mushroomType.getStatsName())
                        .formatted(MushroomType.getLightColor(mushroomType))));
        tooltip.add(new TranslatableText("tooltip.magicfungi.spell_info.5")
                .formatted(MushroomType.getDarkColor(mushroomType), Formatting.BOLD)
                .append(ActionType.WORLD.getTranslatableText()
                        .formatted(MushroomType.getLightColor(mushroomType))));
    }

}