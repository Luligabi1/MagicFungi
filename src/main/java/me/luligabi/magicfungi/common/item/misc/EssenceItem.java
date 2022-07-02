package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

public class EssenceItem extends Item {

    public EssenceItem() {
        super(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));
    }

}