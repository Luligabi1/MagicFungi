package me.luligabi.magicfungi.common.block;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.crafting.GlyphCarvingBlock;
import me.luligabi.magicfungi.common.block.crafting.SpellDiscoveryBlock;
import me.luligabi.magicfungi.common.block.misc.*;
import me.luligabi.magicfungi.common.block.mushroom.*;
import me.luligabi.magicfungi.common.item.misc.MagicalFungiAlloyBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void init() {
        initBlock("impetus_mushroom", IMPETUS_MUSHROOM_PLANT_BLOCK);
        initPotBlock("potted_impetus_mushroom", POTTED_IMPETUS_MUSHROOM);

        initBlock("clypeus_mushroom", CLYPEUS_MUSHROOM_PLANT_BLOCK);
        initPotBlock("potted_clypeus_mushroom", POTTED_CLYPEUS_MUSHROOM);

        initBlock("utilis_mushroom", UTILIS_MUSHROOM_PLANT_BLOCK);
        initPotBlock("potted_utilis_mushroom", POTTED_UTILIS_MUSHROOM);

        initBlock("vivifica_mushroom", VIVIFICA_MUSHROOM_PLANT_BLOCK);
        initPotBlock("potted_vivifica_mushroom", POTTED_VIVIFICA_MUSHROOM);

        initBlock("morbus_mushroom", MORBUS_MUSHROOM_PLANT_BLOCK);
        initPotBlock("potted_morbus_mushroom", POTTED_MORBUS_MUSHROOM);


        initBlock("morbus_mushroom_block", MORBUS_MUSHROOM_BLOCK);


        initBlock("host_grass_block", HOST_GRASS_BLOCK);
        initBlock("host_dirt", HOST_DIRT);

        initBlock("host_grass", HOST_GRASS);
        initBlock("host_tall_grass", HOST_TALL_GRASS);

        initBlock("host_fern", HOST_FERN);
        initPotBlock("potted_host_fern", POTTED_HOST_FERN);

        initBlock("large_host_fern", LARGE_HOST_FERN);


        initBlock("glyph_carving_workbench", GLYPH_CARVING_BLOCK);
        initBlock("spell_discovery_workbench", SPELL_DISCOVERY_BLOCK);


        // Block registered apart from the BlockItem, since it's a glyph.
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "cadentis_block"), CADENTIS_BLOCK);

        // Magical Fungi Alloy Block (requires glint)
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, "magical_fungi_alloy_block"), MAGICAL_FUNGI_ALLOY_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, "magical_fungi_alloy_block"), new MagicalFungiAlloyBlockItem(MAGICAL_FUNGI_ALLOY_BLOCK, new FabricItemSettings().rarity(Rarity.EPIC).group(MagicFungi.ITEM_GROUP)));
    }

    private static void initBlock(String identifier, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, identifier), block);
        Registry.register(Registry.ITEM, new Identifier(MagicFungi.MOD_ID, identifier), new BlockItem(block, new FabricItemSettings().group(MagicFungi.ITEM_GROUP)));
    }

    private static void initPotBlock(String identifier, Block content) {
        Registry.register(Registry.BLOCK, new Identifier(MagicFungi.MOD_ID, identifier), content);
    }

    public static final Block IMPETUS_MUSHROOM_PLANT_BLOCK = new ImpetusMushroomPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_RED).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block CLYPEUS_MUSHROOM_PLANT_BLOCK = new ClypeusMushroomPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.CYAN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block UTILIS_MUSHROOM_PLANT_BLOCK = new UtilisMushroomPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.MAGENTA).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block VIVIFICA_MUSHROOM_PLANT_BLOCK = new VivificaMushroomPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.LIME).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block MORBUS_MUSHROOM_PLANT_BLOCK = new MorbusMushroomPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_BLACK).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));


    public static final Block MORBUS_MUSHROOM_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(0.2F).sounds(BlockSoundGroup.WOOD));


    public static final Block HOST_GRASS_BLOCK = new HostGrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.TERRACOTTA_BROWN).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block HOST_DIRT = new HostDirtBlock(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).ticksRandomly().strength(0.5F).sounds(BlockSoundGroup.GRAVEL));

    public static final Block HOST_GRASS = new HostFernBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block HOST_TALL_GRASS = new TallPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static final Block HOST_FERN = new HostFernBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block LARGE_HOST_FERN = new TallPlantBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.TERRACOTTA_BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));


    public static final Block GLYPH_CARVING_BLOCK = new GlyphCarvingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE));
    public static final Block SPELL_DISCOVERY_BLOCK = new SpellDiscoveryBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).breakByTool(FabricToolTags.AXES, 1).sounds(BlockSoundGroup.WOOD));


    public static final Block MAGICAL_FUNGI_ALLOY_BLOCK = new MagicalFungiAlloyBlock(FabricBlockSettings.of(Material.METAL).strength(5.0F, 6.0F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 3).sounds(BlockSoundGroup.METAL));


    public static final Block CADENTIS_BLOCK = new CadentisBlock(FabricBlockSettings.of(Material.DECORATION, MapColor.CLEAR).noCollision().nonOpaque().luminance(15).sounds(BlockSoundGroup.AMETHYST_BLOCK));


    public static final Block POTTED_IMPETUS_MUSHROOM = new FlowerPotBlock(IMPETUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_CLYPEUS_MUSHROOM = new FlowerPotBlock(CLYPEUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_UTILIS_MUSHROOM = new FlowerPotBlock(UTILIS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_VIVIFICA_MUSHROOM = new FlowerPotBlock(VIVIFICA_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
    public static final Block POTTED_MORBUS_MUSHROOM = new FlowerPotBlock(MORBUS_MUSHROOM_PLANT_BLOCK, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

    public static final Block POTTED_HOST_FERN = new FlowerPotBlock(HOST_FERN, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
}