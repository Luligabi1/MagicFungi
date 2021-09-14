package me.luligabi.magicfungi.common.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class MagicalFungiArmorItem extends ArmorItem {

    public MagicalFungiArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}