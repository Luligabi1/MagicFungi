package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.glyph.HabereGlyphItem;
import me.luligabi.magicfungi.common.item.spell.TestSpell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GlyphRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "habere_glyph"), HABERE_GLYPH_ITEM);
    }

    public static final HabereGlyphItem HABERE_GLYPH_ITEM = new HabereGlyphItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));
}