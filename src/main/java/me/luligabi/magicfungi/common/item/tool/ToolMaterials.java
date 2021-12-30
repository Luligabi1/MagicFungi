package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ToolMaterials implements ToolMaterial { // TODO: Rebalance this considering Morbus Corruption

    IMPETUS(4, 3046, 10.5F, 18.5F, 15, () ->
            Ingredient.ofItems(ItemRegistry.IMPETUS_ESSENCE)),
    UTILIS(4, 3046, 10.5F, 5.5F, 15, () ->
            Ingredient.ofItems(ItemRegistry.UTILIS_ESSENCE)),
    MORBUS(4, 3046, 10.5F, 11.5F, 15, () ->
            Ingredient.ofItems(ItemRegistry.MORBUS_ESSENCE));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    ToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    public int getDurability() { return this.itemDurability; }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}