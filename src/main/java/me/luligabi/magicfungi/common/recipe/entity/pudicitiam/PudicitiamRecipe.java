package me.luligabi.magicfungi.common.recipe.entity.pudicitiam;

import me.luligabi.magicfungi.common.recipe.entity.AbstractEntityConversionRecipe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class PudicitiamRecipe extends AbstractEntityConversionRecipe {

    public PudicitiamRecipe(EntityType<MobEntity> regularEntity, EntityType<MobEntity> corruptedEntity, Identifier identifier) {
        super(regularEntity, corruptedEntity, identifier);
    }

    @Override
    public boolean matches(MobEntity mobEntity) {
        return getCorruptedEntity() == mobEntity.getType();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PudicitiamRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<PudicitiamRecipe> {
        private Type() {}
        public static final PudicitiamRecipe.Type INSTANCE = new PudicitiamRecipe.Type();

        public static final String ID = "pudicitiam_healing";
    }

    @Override
    public RecipeType<?> getType() {
        return PudicitiamRecipe.Type.INSTANCE;
    }

}