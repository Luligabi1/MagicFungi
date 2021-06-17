package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipeSerializer;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegistry {

    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, GlyphRecipeSerializer.ID, GlyphRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, GlyphRecipe.Type.ID), GlyphRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_SERIALIZER, SpellRecipeSerializer.ID, SpellRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, SpellRecipe.Type.ID), SpellRecipe.Type.INSTANCE);
    }
}
