package me.luligabi.magicfungi.common.worldgen.feature;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;

import java.util.List;

public class FeatureRegistry {

    //@SuppressWarnings("deprecation")
    public static void init() {
        // Regular Magic Mushroom gen
        registerMagicMushroomFeature(IMPETUS_REGULAR_CONFIGURED_FEATURE, IMPETUS_REGULAR_PLACED_FEATURE, "impetus_regular", IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE, IMPETUS_BIOME_ENHANCED_PLACED_FEATURE, "impetus_savanna", MagicFungi.CONFIG.canGenerateImpetusMushrooms, Biome.Category.SAVANNA);
        registerMagicMushroomFeature(CLYPEUS_REGULAR_CONFIGURED_FEATURE, CLYPEUS_REGULAR_PLACED_FEATURE, "clypeus_regular", CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE, CLYPEUS_BIOME_ENHANCED_PLACED_FEATURE, "clypeus_icy", MagicFungi.CONFIG.canGenerateClypeusMushrooms, Biome.Category.ICY);
        registerMagicMushroomFeature(UTILIS_REGULAR_CONFIGURED_FEATURE, UTILIS_REGULAR_PLACED_FEATURE, "utilis_regular", UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE, UTILIS_BIOME_ENHANCED_PLACED_FEATURE, "utilis_mountains", MagicFungi.CONFIG.canGenerateUtilisMushrooms,  Biome.Category.MOUNTAIN, Biome.Category.EXTREME_HILLS);
        registerMagicMushroomFeature(VIVIFICA_REGULAR_CONFIGURED_FEATURE, VIVIFICA_REGULAR_PLACED_FEATURE, "vivifica_regular", VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE, VIVIFICA_BIOME_ENHANCED_PLACED_FEATURE, "vivifica_jungle", MagicFungi.CONFIG.canGenerateVivificaMushrooms, Biome.Category.JUNGLE);


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

    private static void registerMagicMushroomFeature(ConfiguredFeature<?, ?> regularConfiguredFeature, PlacedFeature regularPlacedFeature, String regularIdentifier, ConfiguredFeature<?, ?> biomeEnhancedConfiguredFeature, PlacedFeature biomeEnhancedPlacedFeature, String biomeEnhancedIdentifier, boolean enabled, Biome.Category... biomeCategory) {
        if(!enabled) return;
        // Regular feature registry
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, regularIdentifier), regularConfiguredFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, regularIdentifier), regularPlacedFeature);
        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MagicFungi.MOD_ID, regularIdentifier)));

        // Biome Enhanced feature registry
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, biomeEnhancedIdentifier), biomeEnhancedConfiguredFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, biomeEnhancedIdentifier), biomeEnhancedPlacedFeature);
        BiomeModifications.addFeature(BiomeSelectors.categories(biomeCategory), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MagicFungi.MOD_ID, biomeEnhancedIdentifier)));
    }

    // Impetus Mushroom
    public static final ConfiguredFeature<?, ?> IMPETUS_REGULAR_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.impetusRegularSpawnRatio);

    public static final PlacedFeature IMPETUS_REGULAR_PLACED_FEATURE = IMPETUS_REGULAR_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    public static final ConfiguredFeature<?, ?> IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.impetusBiomeEnhancedSpawnRatio);

    public static final PlacedFeature IMPETUS_BIOME_ENHANCED_PLACED_FEATURE = IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


    // Clypeus Mushroom
    public static final ConfiguredFeature<?, ?> CLYPEUS_REGULAR_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.clypeusRegularSpawnRatio);

    public static final PlacedFeature CLYPEUS_REGULAR_PLACED_FEATURE = CLYPEUS_REGULAR_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    public static final ConfiguredFeature<?, ?> CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.clypeusBiomeEnhancedSpawnRatio);

    public static final PlacedFeature CLYPEUS_BIOME_ENHANCED_PLACED_FEATURE = CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


    // Utilis Mushroom
    public static final ConfiguredFeature<?, ?> UTILIS_REGULAR_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.utilisRegularSpawnRatio);

    public static final PlacedFeature UTILIS_REGULAR_PLACED_FEATURE = UTILIS_REGULAR_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    public static final ConfiguredFeature<?, ?> UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.utilisBiomeEnhancedSpawnRatio);

    public static final PlacedFeature UTILIS_BIOME_ENHANCED_PLACED_FEATURE = UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    // Vivifica Mushroom
    public static final ConfiguredFeature<?, ?> VIVIFICA_REGULAR_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaRegularSpawnRatio);

    public static final PlacedFeature VIVIFICA_REGULAR_PLACED_FEATURE = VIVIFICA_REGULAR_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    public static final ConfiguredFeature<?, ?> VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaBiomeEnhancedSpawnRatio);

    public static final PlacedFeature VIVIFICA_BIOME_ENHANCED_PLACED_FEATURE = VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


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

    private static ConfiguredFeature<?, ?> generateMushroomFeatureSupplier(Block block, int tries) {
        return Feature.FLOWER.configure(new RandomPatchFeatureConfig(tries, 8, 3, () -> Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(
                new Range<>(1, 3), new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0D), 1.0F, 2345L,
                new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0D), 1.0F, List.of(block.getDefaultState())))).withInAirFilter()));
    }


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
            Biome.Category.MUSHROOM };


    //private static final BlockState LARGE_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false);
    //private static final BlockState TALL_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
    //private static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);

}