package me.luligabi.magicfungi.common.recipe.spell;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpellRecipeSerializer implements RecipeSerializer<SpellRecipe> {

    private SpellRecipeSerializer() {
    }

    public static final SpellRecipeSerializer INSTANCE = new SpellRecipeSerializer();

    public static final Identifier ID = new Identifier(MagicFungi.MOD_ID, "spell_recipe");

    @Override // Turns json into Recipe
    public SpellRecipe read(Identifier recipeId, JsonObject json) {
        SpellRecipeJsonFormat recipeJson = new Gson().fromJson(json, SpellRecipeJsonFormat.class);
        if (recipeJson.inputA == null ||
                recipeJson.inputB == null ||
                recipeJson.inputC == null ||
                recipeJson.inputD == null ||
                recipeJson.inputE == null ||
                recipeJson.inputF == null ||
                recipeJson.inputG == null ||
                recipeJson.inputH == null ||
                recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
        Ingredient inputC = Ingredient.fromJson(recipeJson.inputC);
        Ingredient inputD = Ingredient.fromJson(recipeJson.inputD);
        Ingredient inputE = Ingredient.fromJson(recipeJson.inputE);
        Ingredient inputF = Ingredient.fromJson(recipeJson.inputF);
        Ingredient inputG = Ingredient.fromJson(recipeJson.inputG);
        Ingredient inputH = Ingredient.fromJson(recipeJson.inputH);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new SpellRecipe(inputA, inputB, inputC, inputD, inputE, inputF, inputG, inputH, output, recipeId);
    }
    @Override // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, SpellRecipe recipe) {
        recipe.getInputA().write(packetData);
        recipe.getInputB().write(packetData);
        recipe.getInputC().write(packetData);
        recipe.getInputD().write(packetData);
        recipe.getInputE().write(packetData);
        recipe.getInputF().write(packetData);
        recipe.getInputG().write(packetData);
        recipe.getInputH().write(packetData);
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override // Turns PacketByteBuf into Recipe
    public SpellRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        Ingredient inputA = Ingredient.fromPacket(packetData);
        Ingredient inputB = Ingredient.fromPacket(packetData);
        Ingredient inputC = Ingredient.fromPacket(packetData);
        Ingredient inputD = Ingredient.fromPacket(packetData);
        Ingredient inputE = Ingredient.fromPacket(packetData);
        Ingredient inputF = Ingredient.fromPacket(packetData);
        Ingredient inputG = Ingredient.fromPacket(packetData);
        Ingredient inputH = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        return new SpellRecipe(inputA, inputB, inputC, inputD, inputE, inputF, inputG, inputH, output, recipeId);
    }
}