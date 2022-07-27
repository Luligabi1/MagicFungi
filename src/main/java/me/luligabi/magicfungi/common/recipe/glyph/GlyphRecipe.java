package me.luligabi.magicfungi.common.recipe.glyph;

import me.luligabi.magicfungi.common.screenhandler.SimpleCraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

// TODO This will need to be refactored at some point, but I don't want to :(
public class GlyphRecipe implements Recipe<SimpleCraftingInventory> {

    private final Ingredient inputA;
    private final Ingredient inputB;
    private final Ingredient inputC;
    private final Ingredient inputD;
    private final ItemStack outputStack;
    private final Identifier identifier;
    private final DefaultedList<Ingredient> inputs;


    public GlyphRecipe(Ingredient inputA, Ingredient inputB, Ingredient inputC, Ingredient inputD, ItemStack outputStack, Identifier identifier) {
        this.inputA = inputA;
        this.inputB = inputB;
        this.inputC = inputC;
        this.inputD = inputD;
        this.outputStack = outputStack;
        this.identifier = identifier;
        this.inputs = DefaultedList.copyOf(Ingredient.empty(), inputA, inputB, inputC, inputD);
    }

    public Ingredient getInputA() { return inputA; }

    public Ingredient getInputB() { return inputB; }

    public Ingredient getInputC() { return inputC; }

    public Ingredient getInputD() { return inputD; }

    public DefaultedList<Ingredient> getInputs() { return inputs; }

    @Override
    public boolean matches(SimpleCraftingInventory inventory, World world) {
        if (inventory.size() < 5) return false;
        return inputA.test(inventory.getStack(0)) &&
                inputB.test(inventory.getStack(1)) &&
                inputC.test(inventory.getStack(2)) &&
                inputD.test(inventory.getStack(3));
    }

    @Override
    public ItemStack craft(SimpleCraftingInventory inventory) {
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

        public static final String ID = "glyph_carving";
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