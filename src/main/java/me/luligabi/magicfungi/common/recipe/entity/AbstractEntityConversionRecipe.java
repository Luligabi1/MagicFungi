package me.luligabi.magicfungi.common.recipe.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class AbstractEntityConversionRecipe implements Recipe<Inventory> {

    private final EntityType<MobEntity> regularEntity;
    private final EntityType<MobEntity> corruptedEntity;

    private final Identifier identifier;

    public AbstractEntityConversionRecipe(EntityType<MobEntity> regularEntity, EntityType<MobEntity> corruptedEntity, Identifier identifier) {
        this.regularEntity = regularEntity;
        this.corruptedEntity = corruptedEntity;
        this.identifier = identifier;
    }

    public EntityType<MobEntity> getRegularEntity() { return regularEntity; }

    public EntityType<MobEntity> getCorruptedEntity() { return corruptedEntity; }

    @Override
    public Identifier getId() {
        return identifier;
    }


    public abstract boolean matches(MobEntity mobEntity);

    @Override
    public boolean matches(Inventory inventory, World world) {
        return true;
    }

    @Override
    public boolean fits(int var1, int var2) {
        return false;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}