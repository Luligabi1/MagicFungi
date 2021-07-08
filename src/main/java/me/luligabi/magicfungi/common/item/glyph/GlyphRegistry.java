package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.glyph.morbus.CorrumpereGlyphItem;
import me.luligabi.magicfungi.common.item.glyph.vivifica.PudicitiamGlyphItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GlyphRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "exponentia_glyph"), EXPONENTIA_GLYPH_ITEM);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "pudicitiam_glyph"), PUDICITIAM_GLYPH_ITEM);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "corrumpere_glyph"), CORRUMPERE_GLYPH_ITEM);
    }

    public static final ExponentiaGlyphItem EXPONENTIA_GLYPH_ITEM = new ExponentiaGlyphItem(new FabricItemSettings().maxCount(16).group(MagicFungi.ITEM_GROUP));

    public static final PudicitiamGlyphItem PUDICITIAM_GLYPH_ITEM = new PudicitiamGlyphItem(new FabricItemSettings().maxCount(16).group(MagicFungi.ITEM_GROUP));

    public static final CorrumpereGlyphItem CORRUMPERE_GLYPH_ITEM = new CorrumpereGlyphItem(new FabricItemSettings().maxCount(16).group(MagicFungi.ITEM_GROUP));
}