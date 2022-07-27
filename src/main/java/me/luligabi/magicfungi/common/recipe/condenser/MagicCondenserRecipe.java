package me.luligabi.magicfungi.common.recipe.condenser;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

// TODO: Add custom times?
public class MagicCondenserRecipe implements Recipe<Inventory> {

    private final Ingredient input;
    private final ItemStack outputStack;
    private final int netherStarFuelCost;

    private final int impetusEssenceCost;
    private final int clypeusEssenceCost;
    private final int utilisEssenceCost;
    private final int vivificaEssenceCost;
    private final int morbusEssenceCost;

    private final Identifier identifier;


    public MagicCondenserRecipe(Ingredient input, ItemStack outputStack, int netherStarFuelCost, int impetusEssenceCost, int clypeusEssenceCost, int utilisEssenceCost, int vivificaEssenceCost, int morbusEssenceCost, Identifier identifier) {
        this.input = input;
        this.outputStack = outputStack;
        this.netherStarFuelCost = netherStarFuelCost;
        this.impetusEssenceCost = impetusEssenceCost;
        this.clypeusEssenceCost = clypeusEssenceCost;
        this.utilisEssenceCost = utilisEssenceCost;
        this.vivificaEssenceCost = vivificaEssenceCost;
        this.morbusEssenceCost = morbusEssenceCost;
        this.identifier = identifier;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack.copy();
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

    @Override
    public boolean matches(Inventory inventory, World world) {
        return input.test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return outputStack.copy();
    }

    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }

    @Override
    public Identifier getId() {
        return identifier;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MagicCondenserRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<MagicCondenserRecipe> {
        private Type() {}
        public static final MagicCondenserRecipe.Type INSTANCE = new MagicCondenserRecipe.Type();

        public static final String ID = "magic_condenser";
    }

    @Override
    public RecipeType<?> getType() {
        return MagicCondenserRecipe.Type.INSTANCE;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}