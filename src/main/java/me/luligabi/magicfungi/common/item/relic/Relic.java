package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public interface Relic {

    default void appendQuote(List<Text> tooltip, MushroomType mushroomType, TranslatableText quoteAuthor, TranslatableText... quote) {
        if(!Screen.hasShiftDown()) return;
        for(TranslatableText a : quote) {
            tooltip.add(a.formatted(MushroomType.getLightColor(mushroomType), Formatting.ITALIC));
        }
        tooltip.add(new LiteralText(""));
        tooltip.add(quoteAuthor.formatted(MushroomType.getLightColor(mushroomType)));
        tooltip.add(new LiteralText(""));
    }

}