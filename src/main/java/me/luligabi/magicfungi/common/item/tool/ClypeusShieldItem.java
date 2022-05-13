package me.luligabi.magicfungi.common.item.tool;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricBannerShieldItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class ClypeusShieldItem extends FabricBannerShieldItem {

    public ClypeusShieldItem(Settings settings, int cooldownTicks, ToolMaterial material) {
        super(settings, cooldownTicks, material);
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}