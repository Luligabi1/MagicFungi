package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.MagicMushroomBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "attack_mushroom"), ATTACK_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "attack_mushroom"), new BlockItem(ATTACK_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "defense_mushroom"), DEFENSE_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "defense_mushroom"), new BlockItem(DEFENSE_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }

    public static final MagicMushroomBlock ATTACK_MUSHROOM_BLOCK = new MagicMushroomBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final MagicMushroomBlock DEFENSE_MUSHROOM_BLOCK = new MagicMushroomBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
}