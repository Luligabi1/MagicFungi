package me.luligabi.magicfungi.common.misc.tag;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagRegistry {

    public static void init() {
        MORBUS_GRASS_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_grass_spreadable"));
        MORBUS_DIRT_SPREADABLE = TagFactory.BLOCK.create(new Identifier(MagicFungi.MOD_ID, "morbus_dirt_spreadable"));
    }

    public static Tag<Block> MORBUS_GRASS_SPREADABLE;
    public static Tag<Block> MORBUS_DIRT_SPREADABLE;

}