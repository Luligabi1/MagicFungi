package me.luligabi.magicfungi.common.recipe;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.recipe.condenser.MagicCondenserRecipe;
import me.luligabi.magicfungi.common.recipe.condenser.MagicCondenserRecipeSerializer;
import me.luligabi.magicfungi.common.recipe.entity.corrumpere.CorrumpereRecipe;
import me.luligabi.magicfungi.common.recipe.entity.corrumpere.CorrumpereRecipeSerializer;
import me.luligabi.magicfungi.common.recipe.entity.pudicitiam.PudicitiamRecipe;
import me.luligabi.magicfungi.common.recipe.entity.pudicitiam.PudicitiamRecipeSerializer;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipe;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipeSerializer;
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

        Registry.register(Registry.RECIPE_SERIALIZER, EssenceRecipeSerializer.ID, EssenceRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, EssenceRecipe.Type.ID), EssenceRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_SERIALIZER, MagicCondenserRecipeSerializer.ID, MagicCondenserRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, MagicCondenserRecipe.Type.ID), MagicCondenserRecipe.Type.INSTANCE);

        // Entity Conversion
        Registry.register(Registry.RECIPE_SERIALIZER, PudicitiamRecipeSerializer.ID, PudicitiamRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, PudicitiamRecipe.Type.ID), PudicitiamRecipe.Type.INSTANCE);

        Registry.register(Registry.RECIPE_SERIALIZER, CorrumpereRecipeSerializer.ID, CorrumpereRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MagicFungi.MOD_ID, CorrumpereRecipe.Type.ID), CorrumpereRecipe.Type.INSTANCE);
    }

}