package me.luligabi.magicfungi.common.recipe.entity.corrumpere;

import me.luligabi.magicfungi.common.recipe.entity.AbstractEntityConversionRecipe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class CorrumpereRecipe extends AbstractEntityConversionRecipe {

    public CorrumpereRecipe(EntityType<MobEntity> regularEntity, EntityType<MobEntity> corruptedEntity, Identifier identifier) {
        super(regularEntity, corruptedEntity, identifier);
    }

    @Override
    public boolean matches(MobEntity mobEntity) {
        return getRegularEntity() == mobEntity.getType();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CorrumpereRecipeSerializer.INSTANCE;
    }

    public static class Type implements RecipeType<CorrumpereRecipe> {
        private Type() {}
        public static final CorrumpereRecipe.Type INSTANCE = new CorrumpereRecipe.Type();

        public static final String ID = "corrumpere_corruption";
    }

    @Override
    public RecipeType<?> getType() {
        return CorrumpereRecipe.Type.INSTANCE;
    }

}