package me.luligabi.magicfungi.common.recipe.glyph;

import me.luligabi.magicfungi.common.recipe.ImplementedInventory;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipe;
import me.luligabi.magicfungi.common.recipe.spell.SpellRecipeSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GlyphRecipe implements Recipe<ImplementedInventory> {

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
    public boolean matches(ImplementedInventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return inputA.test(inventory.getStack(0)) &&
                inputB.test(inventory.getStack(1)) &&
                inputC.test(inventory.getStack(2)) &&
                inputD.test(inventory.getStack(3));
    }

    @Override
    public ItemStack craft(ImplementedInventory inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack;
    }

    @Override
    public Identifier getId() {
        return identifier;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SpellRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<SpellRecipe> {
        private Type() {}
        public static final GlyphRecipe.Type INSTANCE = new GlyphRecipe.Type();

        public static final String ID = "glpyh_recipe";
    }

    @Override
    public RecipeType<?> getType() {
        return SpellRecipe.Type.INSTANCE;
    }

}