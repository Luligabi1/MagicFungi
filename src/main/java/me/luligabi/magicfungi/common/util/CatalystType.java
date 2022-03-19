package me.luligabi.magicfungi.common.util;

import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

/**
 * Same as MushroomType, but without the 'incognita' type.
 * Currently, it's used only for the Essence Extractor.
 *
 * @see me.luligabi.magicfungi.common.util.MushroomType
 */
public enum CatalystType implements StringIdentifiable {

    IMPETUS("impetus", Formatting.RED),
    CLYPEUS("clypeus", Formatting.AQUA),
    UTILIS("utilis", Formatting.LIGHT_PURPLE),
    VIVIFICA("vivifica", Formatting.GREEN),
    MORBUS("morbus", Formatting.GRAY),

    EMPTY("empty", Formatting.WHITE);


    private final String id;
    private final Formatting color;

    CatalystType(String id, Formatting color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public String asString() {
        return id;
    }

    public Formatting getColor() {
        return color;
    }

}