package me.luligabi.magicfungi.common.item;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_essence"), IMPETUS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_essence"), CLYPEUS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_essence"), UTILIS_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_essence"), VIVIFICA_ESSENCE);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_essence"), MORBUS_ESSENCE);


        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "fungi_fertilizer"), FUNGI_FERTILIZER);

        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "guide_book"), GUIDE_BOOK);
    }

    public static final Item IMPETUS_ESSENCE = new Item(ItemRegistry.ESSENCE_ITEM_SETTINGS);

    public static final Item CLYPEUS_ESSENCE = new Item(ItemRegistry.ESSENCE_ITEM_SETTINGS);

    public static final Item UTILIS_ESSENCE = new Item(ItemRegistry.ESSENCE_ITEM_SETTINGS);

    public static final Item VIVIFICA_ESSENCE = new Item(ItemRegistry.ESSENCE_ITEM_SETTINGS);

    public static final Item MORBUS_ESSENCE = new Item(ItemRegistry.ESSENCE_ITEM_SETTINGS);


    public static final Item FUNGI_FERTILIZER = new FungiFertilizerItem(new FabricItemSettings().group(MagicFungi.ITEM_GROUP));

    public static final Item GUIDE_BOOK = new GuideBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(MagicFungi.ITEM_GROUP));



    private static final Item.Settings ESSENCE_ITEM_SETTINGS = new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).group(MagicFungi.ITEM_GROUP);

}