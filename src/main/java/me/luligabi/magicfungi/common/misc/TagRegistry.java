package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagRegistry {

    public static void init() {
        MOD_MUSHROOMS = TagFactory.ITEM.create(new Identifier(MagicFungi.MOD_ID, "mod_mushrooms"));

        MORBUS_GRASS_BLOCK_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_grass_block_spreadable"));
        MORBUS_DIRT_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_dirt_spreadable"));

        MORBUS_GRASS_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_grass_spreadable"));
        MORBUS_FERN_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_fern_spreadable"));
        MORBUS_TALL_GRASS_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_tall_grass_spreadable"));
        MORBUS_LARGE_FERN_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_large_fern_spreadable"));

        HOST_BIOME_VEGETATION = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "host_biome_vegetation"));
    }

    public static Tag<Item> MOD_MUSHROOMS;

    public static Tag<Block> MORBUS_GRASS_BLOCK_SPREADABLE;
    public static Tag<Block> MORBUS_DIRT_SPREADABLE;

    public static Tag<Block> MORBUS_GRASS_SPREADABLE;
    public static Tag<Block> MORBUS_FERN_SPREADABLE;
    public static Tag<Block> MORBUS_TALL_GRASS_SPREADABLE;
    public static Tag<Block> MORBUS_LARGE_FERN_SPREADABLE;

    public static Tag<Block> HOST_BIOME_VEGETATION;

}