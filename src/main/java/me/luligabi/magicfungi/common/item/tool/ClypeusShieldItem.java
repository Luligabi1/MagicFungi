package me.luligabi.magicfungi.common.item.tool;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClypeusShieldItem extends FabricBannerShieldItem { // TODO: Add crafting and other JSON stuff back

    public ClypeusShieldItem(Settings settings, int cooldownTicks, int enchantability, Item repairItem) {
        super(settings, cooldownTicks, enchantability, repairItem);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}