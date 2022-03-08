package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagRegistry {

    public static void init() {
        MOD_MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "mod_mushrooms"));

        MORBUS_GRASS_BLOCK_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_grass_block_spreadable"));
        MORBUS_DIRT_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_dirt_spreadable"));

        MORBUS_GRASS_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_grass_spreadable"));
        MORBUS_FERN_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_fern_spreadable"));
        MORBUS_TALL_GRASS_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_tall_grass_spreadable"));
        MORBUS_LARGE_FERN_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, (new Identifier(MagicFungi.MOD_ID, "morbus_large_fern_spreadable")));

        HOST_BIOME_VEGETATION = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "host_biome_vegetation"));
    }

    public static TagKey<Item> MOD_MUSHROOMS;

    public static TagKey<Block> MORBUS_GRASS_BLOCK_SPREADABLE;
    public static TagKey<Block> MORBUS_DIRT_SPREADABLE;

    public static TagKey<Block> MORBUS_GRASS_SPREADABLE;
    public static TagKey<Block> MORBUS_FERN_SPREADABLE;
    public static TagKey<Block> MORBUS_TALL_GRASS_SPREADABLE;
    public static TagKey<Block> MORBUS_LARGE_FERN_SPREADABLE;

    public static TagKey<Block> HOST_BIOME_VEGETATION;

}