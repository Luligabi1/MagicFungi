package me.luligabi.magicfungi.client.compat.rei.essence;


import me.luligabi.magicfungi.client.compat.rei.ReiPlugin;
import me.luligabi.magicfungi.common.recipe.essence.EssenceRecipe;
import me.luligabi.magicfungi.common.util.CatalystType;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class EssenceRecipeDisplay implements Display {

    protected EssenceRecipe recipe;

    protected List<EntryIngredient> input;
    protected List<EntryIngredient> output;
    protected CatalystType catalystType;


    public EssenceRecipeDisplay(EssenceRecipe recipe) {
        this.recipe = recipe;

        this.input = EntryIngredients.ofIngredients(List.of(recipe.getInput()));
        this.output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
        this.catalystType = recipe.getCatalystType();
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return input;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return output;
    }

    public CatalystType getCatalystType() {
        return catalystType;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ReiPlugin.ESSENCE_EXTRACTION;
    }

}