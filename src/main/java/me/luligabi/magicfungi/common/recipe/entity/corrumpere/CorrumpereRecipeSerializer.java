package me.luligabi.magicfungi.common.recipe.entity.corrumpere;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.recipe.entity.EntityConversionRecipeJsonFormat;
import me.luligabi.magicfungi.common.recipe.entity.EntityConversionRecipeSerialization;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class CorrumpereRecipeSerializer implements RecipeSerializer<CorrumpereRecipe>, EntityConversionRecipeSerialization {

    private CorrumpereRecipeSerializer() {
    }

    public static final CorrumpereRecipeSerializer INSTANCE = new CorrumpereRecipeSerializer();

    public static final Identifier ID = new Identifier(MagicFungi.MOD_ID, "corrumpere_corruption");

    @Override // Turns json into Recipe
    public CorrumpereRecipe read(Identifier recipeId, JsonObject json) {
        EntityConversionRecipeJsonFormat recipeJson = new Gson().fromJson(json, EntityConversionRecipeJsonFormat.class);
        if (recipeJson.regularEntity == null || recipeJson.corruptedEntity == null) {
            throw new JsonSyntaxException("A required attribute is missing!");
        }

        EntityType<MobEntity> regularEntity = parseEntityType(recipeJson.regularEntity);
        EntityType<MobEntity> corruptedEntity = parseEntityType(recipeJson.corruptedEntity);

        return new CorrumpereRecipe(regularEntity, corruptedEntity, recipeId);
    }

    @Override // Turns Recipe into PacketByteBuf
    public void write(PacketByteBuf packetData, CorrumpereRecipe recipe) {
        packetData.writeIdentifier(EntityType.getId(recipe.getRegularEntity()));
        packetData.writeIdentifier(EntityType.getId(recipe.getCorruptedEntity()));
    }

    @Override // Turns PacketByteBuf into Recipe
    public CorrumpereRecipe read(Identifier recipeId, PacketByteBuf packetData) {
        EntityType<MobEntity> regularEntity = parseEntityType(packetData.readIdentifier().toString());
        EntityType<MobEntity> corruptedEntity = parseEntityType(packetData.readIdentifier().toString());
        return new CorrumpereRecipe(regularEntity, corruptedEntity, recipeId);
    }

}