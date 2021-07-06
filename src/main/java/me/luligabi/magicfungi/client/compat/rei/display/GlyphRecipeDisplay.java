package me.luligabi.magicfungi.client.compat.rei.display;

import me.luligabi.magicfungi.client.compat.rei.ReiPlugin;
import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlyphRecipeDisplay implements Display {

    protected GlyphRecipe display;
    protected ArrayList<EntryIngredient> input;

    protected List<EntryIngredient> inputA;
    protected List<EntryIngredient> inputB;
    protected List<EntryIngredient> inputC;
    protected List<EntryIngredient> inputD;

    protected List<EntryIngredient> output;

    public GlyphRecipeDisplay(GlyphRecipe recipe) {
        this.display = recipe;

        this.inputA = EntryIngredients.ofIngredients(List.of(recipe.getInputA()));
        this.inputB = EntryIngredients.ofIngredients(List.of(recipe.getInputB()));
        this.inputC = EntryIngredients.ofIngredients(List.of(recipe.getInputC()));
        this.inputD = EntryIngredients.ofIngredients(List.of(recipe.getInputD()));


        input = new ArrayList<>();
        input.addAll(inputA);
        input.addAll(inputB);
        input.addAll(inputC);
        input.addAll(inputD);

        this.output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ReiPlugin.GLYPH_CARVING;
    }

}