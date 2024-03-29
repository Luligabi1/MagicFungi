package me.luligabi.magicfungi.common.item.relic;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ToolMaterials implements ToolMaterial {

    IMPETUS(0, MagicFungi.CONFIG.relicDurability, MagicFungi.CONFIG.relicMiningSpeed, MagicFungi.CONFIG.impetusSwordAttackDamage, 15, () ->
            Ingredient.ofItems(ItemRegistry.IMPETUS_ESSENCE), MagicFungi.CONFIG.impetusSwordMaxCharge),
    CLYPEUS(0, MagicFungi.CONFIG.relicDurability, 1, 1, 15, () ->
            Ingredient.ofItems(ItemRegistry.CLYPEUS_ESSENCE), MagicFungi.CONFIG.clypeusShieldMaxCharge),
    UTILIS(4, MagicFungi.CONFIG.relicDurability, MagicFungi.CONFIG.relicMiningSpeed, 5.5F, 15, () ->
            Ingredient.ofItems(ItemRegistry.UTILIS_ESSENCE), -1),
    MORBUS(0, MagicFungi.CONFIG.relicDurability, MagicFungi.CONFIG.relicMiningSpeed, MagicFungi.CONFIG.morbusScytheAttackDamage, 15, () ->
            Ingredient.ofItems(ItemRegistry.MORBUS_ESSENCE), MagicFungi.CONFIG.morbusScytheMaxCharge);

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;
    private final int maxCharge;

    ToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient, int maxCharge) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<>(repairIngredient);
        this.maxCharge = maxCharge;
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

    public int getMaxCharge() {
        return maxCharge;
    }

}