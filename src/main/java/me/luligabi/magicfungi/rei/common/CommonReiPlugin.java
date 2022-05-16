package me.luligabi.magicfungi.rei.common;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.glyph.GlyphCarvingScreenHandler;
import me.luligabi.magicfungi.common.screenhandler.spell.SpellDiscoveryScreenHandler;
import me.luligabi.magicfungi.rei.common.display.EssenceRecipeDisplay;
import me.luligabi.magicfungi.rei.common.display.GlyphRecipeDisplay;
import me.luligabi.magicfungi.rei.common.display.SpellRecipeDisplay;
import me.luligabi.magicfungi.rei.common.menuinfo.GlyphMenuInfo;
import me.luligabi.magicfungi.rei.common.menuinfo.SpellMenuInfo;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoRegistry;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleMenuInfoProvider;

public class CommonReiPlugin implements REIServerPlugin {


    @Override
    public void registerMenuInfo(MenuInfoRegistry registry) {
        registry.register(GLYPH_CARVING, GlyphCarvingScreenHandler.class, SimpleMenuInfoProvider.of(GlyphMenuInfo::new));
        registry.register(SPELL_DISCOVERY, SpellDiscoveryScreenHandler.class, SimpleMenuInfoProvider.of(SpellMenuInfo::new));
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(GLYPH_CARVING, GlyphRecipeDisplay.Serializer.INSTANCE);
        registry.register(SPELL_DISCOVERY, SpellRecipeDisplay.Serializer.INSTANCE);
    }


    public static final CategoryIdentifier<GlyphRecipeDisplay> GLYPH_CARVING = CategoryIdentifier.of(MagicFungi.MOD_ID, "glyph_carving");
    public static final CategoryIdentifier<SpellRecipeDisplay> SPELL_DISCOVERY = CategoryIdentifier.of(MagicFungi.MOD_ID, "spell_discovery");
    public static final CategoryIdentifier<EssenceRecipeDisplay> ESSENCE_EXTRACTION = CategoryIdentifier.of(MagicFungi.MOD_ID, "essence_extraction");

}