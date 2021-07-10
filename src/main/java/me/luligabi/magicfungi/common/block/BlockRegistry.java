package me.luligabi.magicfungi.common.block;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.blockentity.GlyphCarvingBlockEntity;
import me.luligabi.magicfungi.common.block.blockentity.SpellDiscoveryBlockEntity;
import me.luligabi.magicfungi.common.block.misc.GlyphCarvingBlock;
import me.luligabi.magicfungi.common.block.misc.SpellDiscoveryBlock;
import me.luligabi.magicfungi.common.block.mushroom.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.entity.BlockEntityType;
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


        Registry.register(Registry.BLOCK, GLYPH_CARVING_BENCH_IDENTIFIER, GLYPH_CARVING_BLOCK);
        Registry.register(Registry.ITEM, GLYPH_CARVING_BENCH_IDENTIFIER, new BlockItem(GLYPH_CARVING_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        GLYPH_CARVING_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, GLYPH_CARVING_BENCH_IDENTIFIER, FabricBlockEntityTypeBuilder.create(GlyphCarvingBlockEntity::new, GLYPH_CARVING_BLOCK).build(null));

        Registry.register(Registry.BLOCK, SPELL_DISCOVERY_BENCH_IDENTIFIER, SPELL_DISCOVERY_BLOCK);
        Registry.register(Registry.ITEM, SPELL_DISCOVERY_BENCH_IDENTIFIER, new BlockItem(SPELL_DISCOVERY_BLOCK, new Item.Settings().group(MagicFungi.ITEM_GROUP)));

        SPELL_DISCOVERY_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, SPELL_DISCOVERY_BENCH_IDENTIFIER, FabricBlockEntityTypeBuilder.create(SpellDiscoveryBlockEntity::new, SPELL_DISCOVERY_BLOCK).build(null));
    }

    public static final ImpetusMushroomPlantBlock IMPETUS_MUSHROOM_PLANT_BLOCK = new ImpetusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final ClypeusMushroomPlantBlock CLYPEUS_MUSHROOM_PLANT_BLOCK = new ClypeusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final UtilisMushroomPlantBlock UTILIS_MUSHROOM_PLANT_BLOCK = new UtilisMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final VivificaMushroomPlantBlock VIVIFICA_MUSHROOM_PLANT_BLOCK = new VivificaMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);
    public static final MorbusMushroomPlantBlock MORBUS_MUSHROOM_PLANT_BLOCK = new MorbusMushroomPlantBlock(MagicMushroomPlantBlock.MUSHROOM_SETTINGS);

    public static final MushroomBlock MORBUS_MUSHROOM_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(0.2F).sounds(BlockSoundGroup.WOOD));

    public static final GlyphCarvingBlock GLYPH_CARVING_BLOCK = new GlyphCarvingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 1).sounds(BlockSoundGroup.STONE));
    public static BlockEntityType<GlyphCarvingBlockEntity> GLYPH_CARVING_BLOCK_ENTITY;


    public static final SpellDiscoveryBlock SPELL_DISCOVERY_BLOCK = new SpellDiscoveryBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5F).breakByTool(FabricToolTags.AXES, 1).sounds(BlockSoundGroup.WOOD));
    public static BlockEntityType<SpellDiscoveryBlockEntity> SPELL_DISCOVERY_BLOCK_ENTITY;

    public static final Identifier GLYPH_CARVING_BENCH_IDENTIFIER = new Identifier(MagicFungi.MOD_ID, "glyph_carving_bench");
    public static final Identifier SPELL_DISCOVERY_BENCH_IDENTIFIER = new Identifier(MagicFungi.MOD_ID, "spell_discovery_bench");

}