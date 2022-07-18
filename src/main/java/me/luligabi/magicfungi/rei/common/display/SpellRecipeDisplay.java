package me.luligabi.magicfungi.rei.common.display;

import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
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

public class SpellRecipeDisplay implements Display {

    protected SpellRecipe recipe;
    protected List<EntryIngredient> input;

    protected List<EntryIngredient> inputA;
    protected List<EntryIngredient> inputB;
    protected List<EntryIngredient> inputC;
    protected List<EntryIngredient> inputD;
    protected List<EntryIngredient> inputE;
    protected List<EntryIngredient> inputF;
    protected List<EntryIngredient> inputG;
    protected List<EntryIngredient> inputH;

    protected List<EntryIngredient> output;

    public SpellRecipeDisplay(SpellRecipe recipe) {
        this.recipe = recipe;

        this.inputA = EntryIngredients.ofIngredients(List.of(recipe.getInputA()));
        this.inputB = EntryIngredients.ofIngredients(List.of(recipe.getInputB()));
        this.inputC = EntryIngredients.ofIngredients(List.of(recipe.getInputC()));
        this.inputD = EntryIngredients.ofIngredients(List.of(recipe.getInputD()));
        this.inputE = EntryIngredients.ofIngredients(List.of(recipe.getInputE()));
        this.inputF = EntryIngredients.ofIngredients(List.of(recipe.getInputF()));
        this.inputG = EntryIngredients.ofIngredients(List.of(recipe.getInputG()));
        this.inputH = EntryIngredients.ofIngredients(List.of(recipe.getInputH()));

        input = new ArrayList<>();
        input.addAll(inputA);
        input.addAll(inputB);
        input.addAll(inputC);
        input.addAll(inputD);
        input.addAll(inputE);
        input.addAll(inputF);
        input.addAll(inputG);
        input.addAll(inputH);

        this.output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));
    }

    public SpellRecipeDisplay(List<EntryIngredient> input, List<EntryIngredient> output) {
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
        return CommonReiPlugin.SPELL_DISCOVERY;
    }


    public static class Serializer implements DisplaySerializer<SpellRecipeDisplay> {

        public static final SpellRecipeDisplay.Serializer INSTANCE = new SpellRecipeDisplay.Serializer();

        private Serializer() {}

        @Override
        public NbtCompound save(NbtCompound tag, SpellRecipeDisplay display) {
            NbtList input = new NbtList();
            display.input.forEach(entryStacks -> input.add(entryStacks.save()));
            tag.put("input", input);

            NbtList output = new NbtList();
            display.output.forEach(entryStacks -> output.add(entryStacks.save()));
            tag.put("output", output);

            return tag;
        }

        @Override
        public SpellRecipeDisplay read(NbtCompound tag) {
            List<EntryIngredient> input = new ArrayList<>();
            tag.getList("input", NbtType.LIST).forEach(nbtElement -> input.add(EntryIngredient.read((NbtList) nbtElement)));

            List<EntryIngredient> output = new ArrayList<>();
            tag.getList("output", NbtType.LIST).forEach(nbtElement -> output.add(EntryIngredient.read((NbtList) nbtElement)));

            return new SpellRecipeDisplay(input, output);
        }
    }

}