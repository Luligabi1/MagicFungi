package me.luligabi.magicfungi.rei.client;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.item.glyph.GlyphRegistry;
import me.luligabi.magicfungi.common.item.spell.SpellRegistry;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipe;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import me.luligabi.magicfungi.rei.client.displaycategory.EssenceDisplayCategory;
import me.luligabi.magicfungi.rei.client.displaycategory.GlyphDisplayCategory;
import me.luligabi.magicfungi.rei.client.displaycategory.SpellDisplayCategory;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.luligabi.magicfungi.rei.common.display.EssenceRecipeDisplay;
import me.luligabi.magicfungi.rei.common.display.GlyphRecipeDisplay;
import me.luligabi.magicfungi.rei.common.display.SpellRecipeDisplay;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.Map;

public class ClientReiPlugin implements REIClientPlugin {


    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new GlyphDisplayCategory());
        registry.addWorkstations(CommonReiPlugin.GLYPH_CARVING, EntryStacks.of(BlockRegistry.GLYPH_CARVING_BLOCK));

        registry.add(new SpellDisplayCategory());
        registry.addWorkstations(CommonReiPlugin.SPELL_DISCOVERY, EntryStacks.of(BlockRegistry.SPELL_DISCOVERY_BLOCK));

        registry.add(new EssenceDisplayCategory());
        registry.addWorkstations(CommonReiPlugin.ESSENCE_EXTRACTION, EntryStacks.of(BlockRegistry.ESSENCE_EXTRACTOR_BLOCK));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(GlyphRecipe.class, GlyphRecipeDisplay::new);
        registry.registerFiller(SpellRecipe.class, SpellRecipeDisplay::new);
        registry.registerFiller(EssenceRecipe.class, EssenceRecipeDisplay::new);

        Map<ItemConvertible, String> reiInformationMap = Maps.newHashMap((new ImmutableMap.Builder<ItemConvertible, String>())
                .put(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, "description.magicfungi.impetus_mushroom")
                .put(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, "description.magicfungi.clypeus_mushroom")
                .put(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, "description.magicfungi.utilis_mushroom")
                .put(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, "description.magicfungi.vivifica_mushroom")
                .put(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, "description.magicfungi.morbus_mushroom")


                // Glyphs
                .put(BlockRegistry.GLYPH_CARVING_BLOCK, "description.magicfungi.glyph_carving_workbench")

                .put(GlyphRegistry.EXPONENTIA_GLYPH, "description.magicfungi.exponentia_glyph")

                .put(GlyphRegistry.PLUVIAM_GLYPH, "description.magicfungi.pluviam_glyph")
                .put(GlyphRegistry.CADENTIS_GLYPH, "description.magicfungi.cadentis_glyph")

                .put(GlyphRegistry.PUDICITIAM_GLYPH, "description.magicfungi.pudicitiam_glyph")
                .put(GlyphRegistry.SANCTIFICARE_GLYPH, "description.magicfungi.sanctificare_glyph")

                .put(GlyphRegistry.PARASITUS_GLYPH, "description.magicfungi.parasitus_glyph")
                .put(GlyphRegistry.CORRUMPERE_GLYPH, "description.magicfungi.corrumpere_glyph")
                .put(GlyphRegistry.MALEDICTIO_GLYPH, "description.magicfungi.maledictio_glyph")

                // Spells
                .put(BlockRegistry.SPELL_DISCOVERY_BLOCK, "description.magicfungi.spell_discovery_workbench")

                .put(SpellRegistry.IGNEI_SPELL, "description.magicfungi.ignei_spell")
                .put(SpellRegistry.SCINTILLAM_SPELL, "description.magicfungi.scintillam_spell")

                .put(SpellRegistry.CADERE_SPELL, "description.magicfungi.cadere_spell")
                .put(SpellRegistry.GLACIES_SPELL, "description.magicfungi.glacies_spell")

                .put(SpellRegistry.TRACTABILE_SPELL, "description.magicfungi.tractabile_spell")
                .put(SpellRegistry.CIBUS_SPELL, "description.magicfungi.cibus_spell")

                .put(SpellRegistry.FERTILIS_SPELL, "description.magicfungi.fertilis_spell")

                // Essences
                .put(BlockRegistry.ESSENCE_EXTRACTOR_BLOCK, "description.magicfungi.essence_extractor")

                .put(ItemRegistry.IMPETUS_ESSENCE, "description.magicfungi.impetus_essence")
                .put(ItemRegistry.CLYPEUS_ESSENCE, "description.magicfungi.clypeus_essence")
                .put(ItemRegistry.UTILIS_ESSENCE, "description.magicfungi.utilis_essence")
                .put(ItemRegistry.VIVIFICA_ESSENCE, "description.magicfungi.vivifica_essence")
                .put(ItemRegistry.MORBUS_ESSENCE, "description.magicfungi.morbus_essence")

                // Morbus Items
                .put(ItemRegistry.MORBUS_CLOCK, "description.magicfungi.morbus_clock")
                .put(ItemRegistry.HEART_OF_VIVIFICA, "description.magicfungi.heart_of_vivifica")
                .put(ItemRegistry.HEART_OF_MORBUS, "description.magicfungi.heart_of_morbus")

                // Misc
                .put(ItemRegistry.GUIDE_BOOK, "description.magicfungi.guide_book")
                .put(ItemRegistry.RESEARCH_LOG, "description.magicfungi.research_log")

                .build());

        reiInformationMap.forEach((itemConvertible, text) -> {
            DefaultInformationDisplay info = DefaultInformationDisplay.createFromEntry(EntryStacks.of(itemConvertible), new LiteralText(""));
            info.line(new TranslatableText(text));
            registry.add(info);
        });
    }

}