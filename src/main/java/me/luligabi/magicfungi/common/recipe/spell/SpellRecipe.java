package me.luligabi.magicfungi.common.recipe.spell;

import me.luligabi.magicfungi.common.recipe.ImplementedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellRecipe implements Recipe<ImplementedInventory> {

    private final Ingredient inputA;
    private final Ingredient inputB;
    private final Ingredient inputC;
    private final Ingredient inputD;
    private final Ingredient inputE;
    private final Ingredient inputF;
    private final Ingredient inputG;
    private final Ingredient inputH;
    private final ItemStack outputStack;
    private final Identifier identifier;


    public SpellRecipe(Ingredient inputA, Ingredient inputB, Ingredient inputC, Ingredient inputD, Ingredient inputE, Ingredient inputF, Ingredient inputG, Ingredient inputH, ItemStack outputStack, Identifier identifier) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.inputC = inputC;
        this.inputD = inputD;
        this.inputE = inputE;
        this.inputF = inputF;
        this.inputG = inputG;
        this.inputH = inputH;
        this.outputStack = outputStack;
        this.identifier = identifier;
    }

    public Ingredient getInputA() { return inputA; }

    public Ingredient getInputB() { return inputB; }

    public Ingredient getInputC() { return inputC; }

    public Ingredient getInputD() { return inputD; }

    public Ingredient getInputE() { return inputE; }

    public Ingredient getInputF() { return inputF; }

    public Ingredient getInputG() { return inputG; }

    public Ingredient getInputH() { return inputH; }

    @Override
    public boolean matches(ImplementedInventory inventory, World world) {
        if (inventory.size() < 8) return false;
        return inputA.test(inventory.getStack(0)) &&
                inputB.test(inventory.getStack(1)) &&
                inputC.test(inventory.getStack(2)) &&
                inputD.test(inventory.getStack(3)) &&
                inputE.test(inventory.getStack(4)) &&
                inputF.test(inventory.getStack(5)) &&
                inputG.test(inventory.getStack(6)) &&
                inputH.test(inventory.getStack(7));
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
        public static final Type INSTANCE = new Type();

        public static final String ID = "spell_recipe";
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

}