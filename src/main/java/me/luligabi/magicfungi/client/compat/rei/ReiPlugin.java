package me.luligabi.magicfungi.client.compat.rei;

import me.luligabi.magicfungi.client.compat.rei.glyph.GlyphDisplayCategory;
import me.luligabi.magicfungi.client.compat.rei.glyph.GlyphRecipeDisplay;
import me.luligabi.magicfungi.client.compat.rei.spell.SpellDisplayCategory;
import me.luligabi.magicfungi.client.compat.rei.spell.SpellRecipeDisplay;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.plugins.PluginManager;
import me.shedaniel.rei.api.common.registry.ReloadStage;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.BuiltinClientPlugin;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;
import java.util.function.UnaryOperator;

public class ReiPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new GlyphDisplayCategory());
        registry.addWorkstations(GLYPH_CARVING, EntryStacks.of(BlockRegistry.GLYPH_CARVING_BLOCK));

        registry.add(new SpellDisplayCategory());
        registry.addWorkstations(SPELL_DISCOVERY, EntryStacks.of(BlockRegistry.SPELL_DISCOVERY_BLOCK));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(GlyphRecipe.class, GlyphRecipeDisplay::new);

        registry.registerFiller(SpellRecipe.class, SpellRecipeDisplay::new);
    }

    public static final CategoryIdentifier<GlyphRecipeDisplay> GLYPH_CARVING = CategoryIdentifier.of(MagicFungi.MOD_ID, "glyph_carving");

    public static final CategoryIdentifier<SpellRecipeDisplay> SPELL_DISCOVERY = CategoryIdentifier.of(MagicFungi.MOD_ID, "spell_discovery");

    @Override
    public void postStage(PluginManager<REIClientPlugin> manager, ReloadStage stage) {
            UnaryOperator<List<Text>> spellDiscoveryInformation = (List<Text> text) -> {
                text.add(new TranslatableText("description.magicfungi.spell_discovery_bench"));
                return text;};

            BuiltinClientPlugin.getInstance().registerInformation(EntryStacks.of(BlockRegistry.SPELL_DISCOVERY_BLOCK), new TranslatableText(""), spellDiscoveryInformation);
    }

}