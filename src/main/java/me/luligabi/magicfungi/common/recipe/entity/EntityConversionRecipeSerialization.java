package me.luligabi.magicfungi.common.recipe.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;

public interface EntityConversionRecipeSerialization {

    default EntityType<MobEntity> parseEntityType(String identifier) { // TODO: Add check if entity is MobEntity
        return (EntityType<MobEntity>) EntityType.get(identifier).orElseThrow(() ->
                new NullPointerException("Entity " + identifier + " not found!"));
    }
}