package me.luligabi.magicfungi.common.item.relic.armor;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MagicalFungiArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * MagicFungi.CONFIG.magicalFungiArmorDurabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() { return Ingredient.ofItems(ItemRegistry.MAGICAL_FUNGI_ALLOY); }

    @Override
    public String getName() {
        return "magical_fungi";
    }

    @Override
    public float getToughness() {
        return MagicFungi.CONFIG.magicalFungiArmorToughness;
    }

    @Override
    public float getKnockbackResistance() {
        return MagicFungi.CONFIG.magicalFungiKnockBackResistance;
    }

    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
    private static final int[] PROTECTION_VALUES = new int[] {6, 9, 11, 6};
}