package me.luligabi.magicfungi.common.recipe.essence;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.util.CatalystType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EssenceRecipeSerializer implements RecipeSerializer<EssenceRecipe> {

    private EssenceRecipeSerializer() {
    }

    public static final EssenceRecipeSerializer INSTANCE = new EssenceRecipeSerializer();

    public static final Identifier ID = new Identifier(MagicFungi.MOD_ID, "essence_extraction");

    @Override // Turns json into Recipe
    public EssenceRecipe read(Identifier recipeId, JsonObject json) {
        EssenceRecipeJsonFormat recipeJson = new Gson().fromJson(json, EssenceRecipeJsonFormat.class);
        if (recipeJson.input == null || recipeJson.catalystType == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if (recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        Ingredient input = Ingredient.fromJson(recipeJson.input);

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new EssenceRecipe(input, CatalystType.valueOf(recipeJson.catalystType.toUpperCase()), output, recipeId);
    }
    @Override // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, EssenceRecipe recipe) {
        recipe.getInput().write(packetData);
        packetData.writeEnumConstant(recipe.getCatalystType());
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override // Turns PacketByteBuf into Recipe
    public EssenceRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        Ingredient inputA = Ingredient.fromPacket(packetData);
        CatalystType catalystType = packetData.readEnumConstant(CatalystType.class);
        ItemStack output = packetData.readItemStack();
        return new EssenceRecipe(inputA, catalystType, output, recipeId);
    }

}