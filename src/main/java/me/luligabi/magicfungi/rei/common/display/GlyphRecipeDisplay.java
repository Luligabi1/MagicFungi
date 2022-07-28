package me.luligabi.magicfungi.rei.common.display;

import me.luligabi.magicfungi.common.recipe.glyph.GlyphRecipe;
import me.luligabi.magicfungi.rei.common.CommonReiPlugin;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlyphRecipeDisplay implements Display {

    protected GlyphRecipe recipe;
    protected List<EntryIngredient> input;

    protected List<EntryIngredient> inputA;
    protected List<EntryIngredient> inputB;
    protected List<EntryIngredient> inputC;
    protected List<EntryIngredient> inputD;

    protected List<EntryIngredient> output;

    public GlyphRecipeDisplay(GlyphRecipe recipe) {
        this.recipe = recipe;

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

    public GlyphRecipeDisplay(List<EntryIngredient> input, List<EntryIngredient> output) {
        this.input = input;
        this.output = output;
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
        return CommonReiPlugin.GLYPH_CARVING;
    }


    public static class Serializer implements DisplaySerializer<GlyphRecipeDisplay> {

        public static final Serializer INSTANCE = new Serializer();

        private Serializer() {}

        @Override
        public NbtCompound save(NbtCompound tag, GlyphRecipeDisplay display) {
            NbtList input = new NbtList();
            display.input.forEach(entryStacks -> input.add(entryStacks.saveIngredient()));
            tag.put("input", input);

            NbtList output = new NbtList();
            display.output.forEach(entryStacks -> output.add(entryStacks.saveIngredient()));
            tag.put("output", output);

            return tag;
        }

        @Override
        public GlyphRecipeDisplay read(NbtCompound tag) {
            List<EntryIngredient> input = new ArrayList<>();
            tag.getList("input", NbtType.LIST).forEach(nbtElement -> input.add(EntryIngredient.read((NbtList) nbtElement)));

            List<EntryIngredient> output = new ArrayList<>();
            tag.getList("output", NbtType.LIST).forEach(nbtElement -> output.add(EntryIngredient.read((NbtList) nbtElement)));

            return new GlyphRecipeDisplay(input, output);
        }
    }

}