package me.luligabi.magicfungi.common.recipe.condenser;

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

public class MagicCondenserRecipeSerializer implements RecipeSerializer<MagicCondenserRecipe> {

    private MagicCondenserRecipeSerializer() {
    }

    public static final MagicCondenserRecipeSerializer INSTANCE = new MagicCondenserRecipeSerializer();

    public static final Identifier ID = new Identifier(MagicFungi.MOD_ID, "magic_condenser");

    @Override // Turns json into Recipe
    public MagicCondenserRecipe read(Identifier recipeId, JsonObject json) {
        MagicCondenserRecipeJsonFormat recipeJson = new Gson().fromJson(json, MagicCondenserRecipeJsonFormat.class);
        if(recipeJson.input == null || recipeJson.outputItem == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        if(recipeJson.outputAmount == 0) recipeJson.outputAmount = 1;

        if(recipeJson.netherStarFuelCost == 0) recipeJson.netherStarFuelCost = 1;
        if(recipeJson.netherStarFuelCost > 20) recipeJson.netherStarFuelCost = 20;

        Ingredient input = Ingredient.fromJson(recipeJson.input);
        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);

        return new MagicCondenserRecipe(input, output, recipeJson.netherStarFuelCost, recipeJson.impetusEssenceCost, recipeJson.clypeusEssenceCost, recipeJson.utilisEssenceCost, recipeJson.vivificaEssenceCost, recipeJson.morbusEssenceCost, recipeId);
    }

    @Override // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, MagicCondenserRecipe recipe) {
        recipe.getInput().write(packetData);
        packetData.writeInt(recipe.getNetherStarFuelCost());
        packetData.writeInt(recipe.getImpetusEssenceCost());
        packetData.writeInt(recipe.getClypeusEssenceCost());
        packetData.writeInt(recipe.getUtilisEssenceCost());
        packetData.writeInt(recipe.getVivificaEssenceCost());
        packetData.writeInt(recipe.getMorbusEssenceCost());
        packetData.writeItemStack(recipe.getOutput());
    }

    @Override // Turns PacketByteBuf into Recipe
    public MagicCondenserRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        Ingredient input = Ingredient.fromPacket(packetData);
        int netherStarFuelCost = packetData.readInt();
        int impetusEssenceCost = packetData.readInt();
        int clypeusEssenceCost = packetData.readInt();
        int utilisEssenceCost = packetData.readInt();
        int vivificaEssenceCost = packetData.readInt();
        int morbusEssenceCost = packetData.readInt();
        ItemStack output = packetData.readItemStack();
        return new MagicCondenserRecipe(input, output, netherStarFuelCost, impetusEssenceCost, clypeusEssenceCost, utilisEssenceCost, vivificaEssenceCost, morbusEssenceCost, recipeId);
    }

}