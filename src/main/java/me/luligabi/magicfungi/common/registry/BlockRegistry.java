package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"), IMPETUS_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"), new BlockItem(IMPETUS_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"), CLYPEUS_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"), new BlockItem(CLYPEUS_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"), UTILIS_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"), new BlockItem(UTILIS_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"), VIVIFICA_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"), new BlockItem(VIVIFICA_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom"), MORBUS_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom"), new BlockItem(MORBUS_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));
    }

    public static final ImpetusMushroomBlock IMPETUS_MUSHROOM_BLOCK = new ImpetusMushroomBlock(MagicMushroomBlock.MUSHROOM_SETTINGS);
    public static final ClypeusMushroomBlock CLYPEUS_MUSHROOM_BLOCK = new ClypeusMushroomBlock(MagicMushroomBlock.MUSHROOM_SETTINGS);
    public static final UtilisMushroomBlock UTILIS_MUSHROOM_BLOCK = new UtilisMushroomBlock(MagicMushroomBlock.MUSHROOM_SETTINGS);
    public static final VivificaMushroomBlock VIVIFICA_MUSHROOM_BLOCK = new VivificaMushroomBlock(MagicMushroomBlock.MUSHROOM_SETTINGS);
    public static final MorbusMushroomBlock MORBUS_MUSHROOM_BLOCK = new MorbusMushroomBlock(MagicMushroomBlock.MUSHROOM_SETTINGS);
}