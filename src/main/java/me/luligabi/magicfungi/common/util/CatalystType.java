package me.luligabi.magicfungi.common.util;

import net.minecraft.util.StringIdentifiable;

/**
 * Same as MushroomType, but without the 'incognita' type.
 * Currently, it's used only for the Essence Extractor.
 *
 * @see me.luligabi.magicfungi.common.util.MushroomType
 */
public enum CatalystType implements StringIdentifiable {

    IMPETUS("impetus"),
    CLYPEUS("clypeus"),
    UTILIS("utilis"),
    VIVIFICA("vivifica"),
    MORBUS("morbus"),

    EMPTY("empty");


    private final String id;

    CatalystType(String id) {
        this.id = id;
    }

    @Override
    public String asString() {
        return id;
    }

}