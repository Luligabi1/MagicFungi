package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public interface Relic {

    default void appendQuote(List<Text> tooltip, MushroomType mushroomType, Text quoteAuthor, Text... quote) {
        if(!Screen.hasShiftDown()) return;
        for(Text a : quote) {
            tooltip.add(a.copy().formatted(MushroomType.getLightColor(mushroomType), Formatting.ITALIC));
        }
        tooltip.add(Text.empty());
        tooltip.add(quoteAuthor.copy().formatted(MushroomType.getLightColor(mushroomType)));
        tooltip.add(Text.empty());
    }

}