package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class ClypeusShieldItem extends ShieldItem {

    public ClypeusShieldItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem() == ItemRegistry.CLYPEUS_ESSENCE;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}