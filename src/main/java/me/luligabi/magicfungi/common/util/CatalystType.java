package me.luligabi.magicfungi.common.util;

import me.luligabi.magicfungi.common.misc.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

/**
 * Same as MushroomType, but without the 'incognita' type.
 * Currently, it's used only for the Essence Extractor.
 *
 * @see me.luligabi.magicfungi.common.util.MushroomType
 */
public enum CatalystType implements StringIdentifiable {

    IMPETUS("impetus", TagRegistry.IMPETUS_CATALYST, Formatting.RED),
    CLYPEUS("clypeus", TagRegistry.CLYPEUS_CATALYST, Formatting.AQUA),
    UTILIS("utilis", TagRegistry.UTILIS_CATALYST, Formatting.LIGHT_PURPLE),
    VIVIFICA("vivifica", TagRegistry.VIVIFICA_CATALYST, Formatting.GREEN),
    MORBUS("morbus", TagRegistry.MORBUS_CATALYST, Formatting.GRAY),

    EMPTY("empty", null, Formatting.WHITE);


    private final String id;
    private final TagKey<Item> tag;
    private final Formatting color;

    CatalystType(String id, TagKey<Item> tag, Formatting color) {
        this.id = id;
        this.tag = tag;
        this.color = color;
    }

    @Override
    public String asString() {
        return id;
    }

    public TagKey<Item> getTag() {
        return tag;
    }

    public Formatting getColor() {
        return color;
    }

}