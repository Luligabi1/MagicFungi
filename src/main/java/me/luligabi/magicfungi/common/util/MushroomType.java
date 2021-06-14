package me.luligabi.magicfungi.common.util;

import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public enum MushroomType {

    IMPETUS(new TranslatableText("name.magicfungi.impetus"), new TranslatableText("name.magicfungi.impetus.stats")),
    CLYPEUS(new TranslatableText("name.magicfungi.clypeus"), new TranslatableText("name.magicfungi.clypeus.stats")),
    UTILIS(new TranslatableText("name.magicfungi.utilis"), new TranslatableText("name.magicfungi.utilis.stats")),
    VIVIFICA(new TranslatableText("name.magicfungi.vivifica"), new TranslatableText("name.magicfungi.vivifica.stats")),
    MORBUS(new TranslatableText("name.magicfungi.morbus"), new TranslatableText("name.magicfungi.morbus.stats")),

    // Unknown
    INCOGNITA(new TranslatableText("name.magicfungi.incognita"), new TranslatableText("name.magicfungi.incognita.stats"));

    protected TranslatableText fancyName;
    protected TranslatableText statsName;

    MushroomType(TranslatableText fancy, TranslatableText stats) {
        fancyName = fancy;
        statsName = stats;
    }

    public TranslatableText getFancyName() {
        return fancyName;
    }

    public TranslatableText getStatsName() {
        return statsName;
    }

    public static Formatting getLightColor(MushroomType mushroomType) {
        return switch (mushroomType) {
            case IMPETUS -> Formatting.RED;
            case CLYPEUS -> Formatting.AQUA;
            case UTILIS -> Formatting.LIGHT_PURPLE;
            case VIVIFICA -> Formatting.GREEN;
            case MORBUS -> Formatting.GRAY;
            default -> Formatting.WHITE;
        };
    }

    public static Formatting getDarkColor(MushroomType mushroomType) {
        return switch (mushroomType) {
            case IMPETUS -> Formatting.DARK_RED;
            case CLYPEUS -> Formatting.DARK_AQUA;
            case UTILIS -> Formatting.DARK_PURPLE;
            case VIVIFICA -> Formatting.DARK_GREEN;
            case MORBUS -> Formatting.DARK_GRAY;
            default -> Formatting.WHITE;
        };
    }
}