package me.luligabi.magicfungi.common.recipe.essence;

import me.luligabi.magicfungi.common.util.CatalystType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class EssenceRecipe implements Recipe<Inventory> {

    private final Ingredient input;
    private final CatalystType catalystType;
    private final ItemStack outputStack;
    private final Identifier identifier;

    public EssenceRecipe(Ingredient input, CatalystType catalystType, ItemStack outputStack, Identifier identifier) {
        this.input = input;
        this.catalystType = catalystType;
        this.outputStack = outputStack;
        this.identifier = identifier;
    }

    public Ingredient getInput() { return input; }

    public CatalystType getCatalystType() { return catalystType; }


    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return input.test(inventory.getStack(3));
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
    public ItemStack getOutput() {
        return outputStack.copy();
    }

    @Override
    public Identifier getId() {
        return identifier;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EssenceRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<EssenceRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();

        public static final String ID = "essence_extraction";
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}