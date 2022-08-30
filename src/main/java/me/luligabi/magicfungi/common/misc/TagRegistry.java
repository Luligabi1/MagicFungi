package me.luligabi.magicfungi.common.misc;

import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class TagRegistry {

    public static final TagKey<Item> MOD_MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "mod_mushrooms"));


    public static final TagKey<Item> IMPETUS_CATALYST = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "impetus_catalyst"));
    public static final TagKey<Item> CLYPEUS_CATALYST = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "clypeus_catalyst"));
    public static final TagKey<Item> UTILIS_CATALYST = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "utilis_catalyst"));
    public static final TagKey<Item> VIVIFICA_CATALYST = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "vivifica_catalyst"));
    public static final TagKey<Item> MORBUS_CATALYST = TagKey.of(Registry.ITEM_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_catalyst"));
  
  
    public static final TagKey<Block> MORBUS_GRASS_BLOCK_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_grass_block_spreadable"));
    public static final TagKey<Block> MORBUS_DIRT_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_dirt_spreadable"));
  
    public static final TagKey<Block> MORBUS_GRASS_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_grass_spreadable"));
    public static final TagKey<Block> MORBUS_FERN_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_fern_spreadable"));
    public static final TagKey<Block> MORBUS_TALL_GRASS_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_tall_grass_spreadable"));
    public static final TagKey<Block> MORBUS_LARGE_FERN_SPREADABLE = TagKey.of(Registry.BLOCK_KEY, (new Identifier(MagicFungi.MOD_ID, "morbus_large_fern_spreadable")));


    public static final TagKey<Block> HOST_BIOME_VEGETATION = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "host_biome_vegetation"));
    public static final TagKey<Block> MORBUS_MOOSHROOMS_SPAWNABLE_ON = TagKey.of(Registry.BLOCK_KEY, new Identifier(MagicFungi.MOD_ID, "morbus_mooshrooms_spawnable_on"));

    public static final TagKey<Biome> FUNGI_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "fungi_spawnable"));
    public static final TagKey<Biome> IMPETUS_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "impetus_spawnable"));
    public static final TagKey<Biome> CLYPEUS_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "clypeus_spawnable"));
    public static final TagKey<Biome> UTILIS_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "utilis_spawnable"));
    public static final TagKey<Biome> VIVIFICA_SPAWNABLE = TagKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "vivifica_spawnable"));


    public static void init() {
        // NO-OP
    }

    private TagRegistry() {
        // NO-OP
    }

}