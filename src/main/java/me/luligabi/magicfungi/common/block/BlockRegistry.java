package me.luligabi.magicfungi.common.block;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.misc.GlyphCarvingBlock;
import me.luligabi.magicfungi.common.block.misc.HostGrassBlock;
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
        initBlock("impetus_mushroom", IMPETUS_MUSHROOM_PLANT_BLOCK);
        initBlock("clypeus_mushroom", CLYPEUS_MUSHROOM_PLANT_BLOCK);
        initBlock("utilis_mushroom", UTILIS_MUSHROOM_PLANT_BLOCK);
        initBlock("vivifica_mushroom", VIVIFICA_MUSHROOM_PLANT_BLOCK);
        initBlock("morbus_mushroom", MORBUS_MUSHROOM_PLANT_BLOCK);


        initBlock("morbus_mushroom_block", MORBUS_MUSHROOM_BLOCK);


        initBlock("host_grass_block", HOST_GRASS_BLOCK);
        initBlock("host_dirt", HOST_DIRT);


        initBlock("glyph_carving_workbench", GLYPH_CARVING_BLOCK);
        initBlock("spell_discovery_workbench", SPELL_DISCOVERY_BLOCK);

        initPotBlock("potted_impetus_mushroom", POTTED_IMPETUS_MUSHROOM);
        initPotBlock("potted_clypeus_mushroom", POTTED_CLYPEUS_MUSHROOM);
        initPotBlock("potted_utilis_mushroom", POTTED_UTILIS_MUSHROOM);
        initPotBlock("potted_vivifica_mushroom", POTTED_VIVIFICA_MUSHROOM);
        initPotBlock("potted_morbus_mushroom", POTTED_MORBUS_MUSHROOM);
    }

    private static void initBlock(String identifier, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, identifier), block);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, identifier), new BlockItem(block, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

    }

    private static void initPotBlock(String identifier, Block content) {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, identifier), content);
    }

    public static final Block IMPETUS_MUSHROOM_PLANT_BLOCK = new ImpetusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final Block CLYPEUS_MUSHROOM_PLANT_BLOCK = new ClypeusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final Block UTILIS_MUSHROOM_PLANT_BLOCK = new UtilisMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final Block VIVIFICA_MUSHROOM_PLANT_BLOCK = new VivificaMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final Block MORBUS_MUSHROOM_PLANT_BLOCK = new MorbusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);


    public static final Block MORBUS_MUSHROOM_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(0.2F).sounds(BlockSoundGroup.WOOD));


    public static final Block HOST_GRASS_BLOCK = new HostGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block HOST_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(0.5F).sounds(BlockSoundGroup.GRAVEL));


    public static final Block GLYPH_CARVING_BLOCK = new GlyphCarvingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE));
    public static final Block SPELL_DISCOVERY_BLOCK = new SpellDiscoveryBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).breakByTool(FabricToolTags.AXES, 1).sounds(BlockSoundGroup.WOOD));


    public static final Block POTTED_IMPETUS_MUSHROOM = new FlowerPotBlock(IMPETUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_CLYPEUS_MUSHROOM = new FlowerPotBlock(CLYPEUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_UTILIS_MUSHROOM = new FlowerPotBlock(UTILIS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_VIVIFICA_MUSHROOM = new FlowerPotBlock(VIVIFICA_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_MORBUS_MUSHROOM = new FlowerPotBlock(MORBUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

}