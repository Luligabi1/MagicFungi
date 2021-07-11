package me.luligabi.magicfungi.common.item;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "fungi_fertilizer"), FUNGI_FERTILIZER);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "guide_book"), GUIDE_BOOK);
    }

    public static final FungiFertilizerItem FUNGI_FERTILIZER = new FungiFertilizerItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));

    public static final GuideBookItem GUIDE_BOOK = new GuideBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));


}