package me.luligabi.magicfungi.common.worldgen.feature;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.mixin.VegetationPlacedFeaturesInvoker;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class PlacedFeatureRegistry {

    //@SuppressWarnings("deprecation")
    public static void init() {
        // Regular Magic Mushroom gen
        registerMagicMushroomFeature(impetus, impetusSavanna, BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, Biome.Category.SAVANNA);
        registerMagicMushroomFeature(clypeus, clypeusIcy, BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, Biome.Category.ICY);
        registerMagicMushroomFeature(utilis,  utilisExtremeHills, BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, Biome.Category.EXTREME_HILLS);
        registerMagicMushroomFeature(vivifica,  vivificaJungle, BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, Biome.Category.JUNGLE);


        // Host Biome's Vegetal Decoration
        /*Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, morbusHost.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.MORBUS_MUSHROOM_CONFIG_HOST).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(2)));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.HOST_BIOME_KEY), GenerationStep.Feature.VEGETAL_DECORATION, morbusHost);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, witherRoseHost.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.WITHER_ROSE_CONFIG_HOST).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(1)));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.HOST_BIOME_KEY), GenerationStep.Feature.VEGETAL_DECORATION, witherRoseHost);
        */
        // TODO: Add Morbus & Morbus Tall Grass gen here.

        // Huge Morbus Mushroom
        /*LARGE_MORBUS_MUSHROOM = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MagicFungi.MOD_ID, "large_morbus_mushroom"), Feature.HUGE_RED_MUSHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(LARGE_MORBUS), new SimpleBlockStateProvider(MUSHROOM_STEM), 2)));
        TALL_MORBUS_MUSHROOM = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(MagicFungi.MOD_ID, "tall_morbus_mushroom"), Feature.HUGE_BROWN_MUSHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(TALL_MORBUS), new SimpleBlockStateProvider(MUSHROOM_STEM), 3)));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, hugeMorbusMushroom.getValue(), Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(
                () -> LARGE_MORBUS_MUSHROOM, () -> TALL_MORBUS_MUSHROOM)).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP));


        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.HOST_BIOME_KEY), GenerationStep.Feature.TOP_LAYER_MODIFICATION, hugeMorbusMushroom);*/
    }

    // TODO: Add config to disable generation / change generation odds.
    private static void registerMagicMushroomFeature(RegistryKey<PlacedFeature> regular, RegistryKey<PlacedFeature> biomeEnhanced, Block block, Biome.Category biomeCategory) {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, regular.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(block))), List.of(), 4)).withPlacement(VegetationPlacedFeaturesInvoker.modifiersWithChance(256, null)));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, biomeEnhanced.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(block))), List.of(), 24)).withPlacement(VegetationPlacedFeaturesInvoker.modifiersWithChance(256, null)));

        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, regular);
        BiomeModifications.addFeature(BiomeSelectors.categories(biomeCategory), GenerationStep.Feature.VEGETAL_DECORATION, biomeEnhanced);
    }

    // Impetus Mushroom - Regular
    public static final RegistryKey<PlacedFeature> impetus = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"));


    // Impetus Mushroom - Savanna Biomes
    public static final RegistryKey<PlacedFeature> impetusSavanna = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "impetus_mushroom_savanna"));


    // Clypeus Mushroom - Regular
    public static final RegistryKey<PlacedFeature> clypeus = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"));


    // Clypeus Mushroom - Icy Biomes
    public static final RegistryKey<PlacedFeature> clypeusIcy = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom_icy"));


    // Utilis Mushroom - Regular
    public static final RegistryKey<PlacedFeature> utilis = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"));


    // Utilis Mushroom - Extreme Hills
    public static final RegistryKey<PlacedFeature> utilisExtremeHills = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "utilis_mushroom_extreme_hills"));



    // Vivifica Mushroom - Regular
    public static final RegistryKey<PlacedFeature> vivifica = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"));


    // Vivifica Mushroom - Jungle Biome
    public static final RegistryKey<PlacedFeature> vivificaJungle = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom_jungle"));


    // Morbus Mushroom - Host Biome
    /*public static final RegistryKey<PlacedFeature> morbusHost = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "morbus_mushroom_host"));


    // Wither Rose - Host Biome
    public static final RegistryKey<PlacedFeature> witherRoseHost = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "wither_rose_host"));


    // Huge Morbus Mushroom - Host Biome
    public static ConfiguredFeature<?, ?> TALL_MORBUS_MUSHROOM;
    public static ConfiguredFeature<?, ?> LARGE_MORBUS_MUSHROOM;

    public static final RegistryKey<PlacedFeature> hugeMorbusMushroom = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "huge_morbus_mushroom"));*/



    // List of Overworld Biome Categories excluding biomes that would be odd for mushrooms to spawn (such as oceans and deserts)
    private static final Biome.Category[] OVERWORLD_BIOMES = {
            Biome.Category.PLAINS,
            Biome.Category.FOREST,
            Biome.Category.TAIGA,
            Biome.Category.SAVANNA,
            Biome.Category.ICY,
            Biome.Category.SWAMP,
            Biome.Category.JUNGLE,
            Biome.Category.UNDERGROUND,
            Biome.Category.MUSHROOM};


    //private static final BlockState LARGE_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false);
    //private static final BlockState TALL_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
    //private static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);

}