package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.glyph.vivifica.PaganumGlyphItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GlyphRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "exponentia_glyph"), EXPONENTIA_GLYPH_ITEM);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "paganum_glyph"), PAGANUM_GLYPH_ITEM);
    }

    public static final ExponentiaGlyphItem EXPONENTIA_GLYPH_ITEM = new ExponentiaGlyphItem(new FabricItemSettings().maxCount(16).group(MagicFungi.ITEM_GROUP));

    public static final PaganumGlyphItem PAGANUM_GLYPH_ITEM = new PaganumGlyphItem(new FabricItemSettings().maxCount(16).group(MagicFungi.ITEM_GROUP));
}