package me.luligabi.magicfungi.common.recipe.glyph;

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

public class GlyphRecipeSerializer implements RecipeSerializer<GlyphRecipe> {

    private GlyphRecipeSerializer() {
    }

    public static final GlyphRecipeSerializer INSTANCE = new GlyphRecipeSerializer();

    public static final Identifier ID = new Identifier(MagicFungi.MOD_ID, "glyph_recipe");

    @Override
    public GlyphRecipe read(Identifier recipeId, JsonObject json) {
        GlyphRecipeJsonFormat recipeJson = new Gson().fromJson(json, GlyphRecipeJsonFormat.class);
        if (recipeJson.inputA == null ||
                recipeJson.inputB == null ||
                recipeJson.inputC == null ||
                recipeJson.inputD == null ||
                recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        // If output size isn't specified, default to 1.
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
        Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
        Ingredient inputC = Ingredient.fromJson(recipeJson.inputC);
        Ingredient inputD = Ingredient.fromJson(recipeJson.inputD);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                // Validating the inputted item actually exists
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new GlyphRecipe(inputA, inputB, inputC, inputD, output, recipeId);
    }
    @Override
    // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, GlyphRecipe recipe) {
        recipe.getInputA().write(packetData);
        recipe.getInputB().write(packetData);
        recipe.getInputC().write(packetData);
        recipe.getInputD().write(packetData);
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override
    // Turns PacketByteBuf into Recipe
    public GlyphRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        Ingredient inputA = Ingredient.fromPacket(packetData);
        Ingredient inputB = Ingredient.fromPacket(packetData);
        Ingredient inputC = Ingredient.fromPacket(packetData);
        Ingredient inputD = Ingredient.fromPacket(packetData);
        ItemStack output = packetData.readItemStack();
        return new GlyphRecipe(inputA, inputB, inputC, inputD, output, recipeId);
    }

}