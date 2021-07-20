package me.luligabi.magicfungi.common.recipe.glyph;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GlyphRecipe implements Recipe<Inventory> {

    private final Ingredient inputA;
    private final Ingredient inputB;
    private final Ingredient inputC;
    private final Ingredient inputD;
    private final ItemStack outputStack;
    private final Identifier identifier;


    public GlyphRecipe(Ingredient inputA, Ingredient inputB, Ingredient inputC, Ingredient inputD, ItemStack outputStack, Identifier identifier) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.inputC = inputC;
        this.inputD = inputD;
        this.outputStack = outputStack;
        this.identifier = identifier;
    }

    public Ingredient getInputA() { return inputA; }

    public Ingredient getInputB() { return inputB; }

    public Ingredient getInputC() { return inputC; }

    public Ingredient getInputD() { return inputD; }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return inputA.test(inventory.getStack(0)) &&
                inputB.test(inventory.getStack(1)) &&
                inputC.test(inventory.getStack(2)) &&
                inputD.test(inventory.getStack(3));
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
        return GlyphRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<GlyphRecipe> {
        private Type() {}
        public static final GlyphRecipe.Type INSTANCE = new GlyphRecipe.Type();

        public static final String ID = "glpyh_recipe";
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