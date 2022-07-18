package me.luligabi.magicfungi.rei.common.display;

import me.luligabi.magicfungi.common.recipe.condenser.MagicCondenserRecipe;
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

public class MagicCondenserDisplay implements Display {

    public MagicCondenserDisplay(MagicCondenserRecipe recipe) {
        this.recipe = recipe;

        this.input = EntryIngredients.ofIngredients(List.of(recipe.getInput()));
        this.output = Collections.singletonList(EntryIngredients.of(recipe.getOutput()));

        this.netherStarFuelCost = recipe.getNetherStarFuelCost();

        this.impetusEssenceCost = recipe.getImpetusEssenceCost();
        this.clypeusEssenceCost = recipe.getClypeusEssenceCost();
        this.utilisEssenceCost = recipe.getUtilisEssenceCost();
        this.vivificaEssenceCost = recipe.getVivificaEssenceCost();
        this.morbusEssenceCost = recipe.getMorbusEssenceCost();
    }


    public MagicCondenserDisplay(List<EntryIngredient> input, List<EntryIngredient> output) {
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

    public int getNetherStarFuelCost() {
        return netherStarFuelCost;
    }

    public int getImpetusEssenceCost() {
        return impetusEssenceCost;
    }

    public int getClypeusEssenceCost() {
        return clypeusEssenceCost;
    }

    public int getUtilisEssenceCost() {
        return utilisEssenceCost;
    }

    public int getVivificaEssenceCost() {
        return vivificaEssenceCost;
    }

    public int getMorbusEssenceCost() {
        return morbusEssenceCost;
    }

    public boolean usesEssences() {
        return impetusEssenceCost + clypeusEssenceCost + utilisEssenceCost + vivificaEssenceCost + morbusEssenceCost > 0;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CommonReiPlugin.MAGIC_CONDENSING;
    }


    protected MagicCondenserRecipe recipe;

    protected List<EntryIngredient> input;
    protected List<EntryIngredient> output;

    int netherStarFuelCost;

    int impetusEssenceCost;
    int clypeusEssenceCost;
    int utilisEssenceCost;
    int vivificaEssenceCost;
    int morbusEssenceCost;


    public static class Serializer implements DisplaySerializer<MagicCondenserDisplay> {

        public static final MagicCondenserDisplay.Serializer INSTANCE = new MagicCondenserDisplay.Serializer();

        private Serializer() {}

        @Override
        public NbtCompound save(NbtCompound tag, MagicCondenserDisplay display) {
            NbtList input = new NbtList();
            display.input.forEach(entryStacks -> input.add(entryStacks.save()));
            tag.put("input", input);

            NbtList output = new NbtList();
            display.output.forEach(entryStacks -> output.add(entryStacks.save()));
            tag.put("output", output);

            return tag;
        }

        @Override
        public MagicCondenserDisplay read(NbtCompound tag) {
            List<EntryIngredient> input = new ArrayList<>();
            tag.getList("input", NbtType.LIST).forEach(nbtElement -> input.add(EntryIngredient.read((NbtList) nbtElement)));

            List<EntryIngredient> output = new ArrayList<>();
            tag.getList("output", NbtType.LIST).forEach(nbtElement -> output.add(EntryIngredient.read((NbtList) nbtElement)));

            return new MagicCondenserDisplay(input, output);
        }
    }

}