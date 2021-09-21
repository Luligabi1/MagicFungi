package me.luligabi.magicfungi.common.misc.tag;

import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagRegistry {

    public static void init() {
        MORBUS_UNSPREADABLE = net.fabricmc.fabric.api.tag.TagRegistry.block(new Identifier(MagicFungi.MOD_ID, "morbus_unspreadable"));
    }

    public static Tag<Block> MORBUS_UNSPREADABLE;
}