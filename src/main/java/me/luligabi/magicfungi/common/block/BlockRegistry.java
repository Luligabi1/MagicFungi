package me.luligabi.magicfungi.common.block;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.misc.GlyphCarvingBlock;
import me.luligabi.magicfungi.common.block.misc.SpellDiscoveryBlock;
import me.luligabi.magicfungi.common.block.mushroom.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"), IMPETUS_MUSHROOM_PLANT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"), new BlockItem(IMPETUS_MUSHROOM_PLANT_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"), CLYPEUS_MUSHROOM_PLANT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"), new BlockItem(CLYPEUS_MUSHROOM_PLANT_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"), UTILIS_MUSHROOM_PLANT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"), new BlockItem(UTILIS_MUSHROOM_PLANT_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"), VIVIFICA_MUSHROOM_PLANT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"), new BlockItem(VIVIFICA_MUSHROOM_PLANT_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom"), MORBUS_MUSHROOM_PLANT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom"), new BlockItem(MORBUS_MUSHROOM_PLANT_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom_block"), MORBUS_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "morbus_mushroom_block"), new BlockItem(MORBUS_MUSHROOM_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));


        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "host_grass_block"), HOST_GRASS_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "host_grass_block"), new BlockItem(HOST_GRASS_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));


        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "host_dirt"), HOST_DIRT);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "host_dirt"), new BlockItem(HOST_DIRT, new Item.Settings().group(MagicFungi.ITEM_GROUP)));


        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "glyph_carving_workbench"), GLYPH_CARVING_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "glyph_carving_workbench"), new BlockItem(GLYPH_CARVING_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));


        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "spell_discovery_workbench"), SPELL_DISCOVERY_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "spell_discovery_workbench"), new BlockItem(SPELL_DISCOVERY_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

    }

    public static final ImpetusMushroomPlantBlock IMPETUS_MUSHROOM_PLANT_BLOCK = new ImpetusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final ClypeusMushroomPlantBlock CLYPEUS_MUSHROOM_PLANT_BLOCK = new ClypeusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final UtilisMushroomPlantBlock UTILIS_MUSHROOM_PLANT_BLOCK = new UtilisMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final VivificaMushroomPlantBlock VIVIFICA_MUSHROOM_PLANT_BLOCK = new VivificaMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final MorbusMushroomPlantBlock MORBUS_MUSHROOM_PLANT_BLOCK = new MorbusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);

    public static final MushroomBlock MORBUS_MUSHROOM_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(0.2F).sounds(BlockSoundGroup.WOOD));

    //TODO: Make Host Grass spread to Host Dirt.
    public static final Block HOST_GRASS_BLOCK = new GrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS));

    public static final Block HOST_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));

    public static final GlyphCarvingBlock GLYPH_CARVING_BLOCK = new GlyphCarvingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE));

    public static final SpellDiscoveryBlock SPELL_DISCOVERY_BLOCK = new SpellDiscoveryBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).breakByTool(FabricToolTags.AXES, 1).sounds(BlockSoundGroup.WOOD));

}