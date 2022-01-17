package me.luligabi.magicfungi.common.worldgen.feature;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.worldgen.biome.BiomeRegistry;
import me.luligabi.magicfungi.mixin.OrePlacedFeaturesInvoker;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;

import java.util.List;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.BASE_STONE_OVERWORLD;

public class FeatureRegistry {

    //@SuppressWarnings("deprecation")
    public static void init() {
        // Regular Magic Mushroom gen
        registerMagicMushroomFeature(IMPETUS_REGULAR_CONFIGURED_FEATURE, IMPETUS_REGULAR_PLACED_FEATURE, "impetus_regular", IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE, IMPETUS_BIOME_ENHANCED_PLACED_FEATURE, "impetus_savanna", MagicFungi.CONFIG.canGenerateImpetusMushrooms, Biome.Category.SAVANNA);
        registerMagicMushroomFeature(CLYPEUS_REGULAR_CONFIGURED_FEATURE, CLYPEUS_REGULAR_PLACED_FEATURE, "clypeus_regular", CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE, CLYPEUS_BIOME_ENHANCED_PLACED_FEATURE, "clypeus_icy", MagicFungi.CONFIG.canGenerateClypeusMushrooms, Biome.Category.ICY);
        registerMagicMushroomFeature(UTILIS_REGULAR_CONFIGURED_FEATURE, UTILIS_REGULAR_PLACED_FEATURE, "utilis_regular", UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE, UTILIS_BIOME_ENHANCED_PLACED_FEATURE, "utilis_mountains", MagicFungi.CONFIG.canGenerateUtilisMushrooms,  Biome.Category.MOUNTAIN, Biome.Category.EXTREME_HILLS);
        registerMagicMushroomFeature(VIVIFICA_REGULAR_CONFIGURED_FEATURE, VIVIFICA_REGULAR_PLACED_FEATURE, "vivifica_regular", VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE, VIVIFICA_BIOME_ENHANCED_PLACED_FEATURE, "vivifica_jungle", MagicFungi.CONFIG.canGenerateVivificaMushrooms, Biome.Category.JUNGLE);

        registerHostBiomeFeature(MORBUS_CONFIGURED_FEATURE, MORBUS_PLACED_FEATURE, "morbus_host_biome", GenerationStep.Feature.VEGETAL_DECORATION, MagicFungi.CONFIG.canGenerateMorbusMushrooms);

        // TODO: Add Morbus & Morbus Tall Grass gen here.
        registerHostBiomeFeature(WITHER_ROSE_CONFIGURED_FEATURE, WITHER_ROSE_PLACED_FEATURE, "wither_rose_host_biome", GenerationStep.Feature.VEGETAL_DECORATION, MagicFungi.CONFIG.canGenerateWitherRoseHostBiome);

        //registerHostBiomeFeature(HUGE_MORBUS_VEGETATION_CONFIGURED_FEATURE, HUGE_MORBUS_VEGETATION_PLACED_FEATURE, "huge_morbus_vegetation", GenerationStep.Feature.VEGETAL_DECORATION, true);

        registerFeature(ORE_HOST_DIRT_CONFIGURED_FEATURE, ORE_HOST_DIRT_PLACED_FEATURE, "ore_host_dirt");
    }

    private static void registerMagicMushroomFeature(ConfiguredFeature<?, ?> regularConfiguredFeature, PlacedFeature regularPlacedFeature, String regularIdentifier, ConfiguredFeature<?, ?> biomeEnhancedConfiguredFeature, PlacedFeature biomeEnhancedPlacedFeature, String biomeEnhancedIdentifier, boolean enabled, Biome.Category... biomeCategory) {
        if(!enabled) return;

        // Regular feature registry
        registerFeature(regularConfiguredFeature, regularPlacedFeature, regularIdentifier);
        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MagicFungi.MOD_ID, regularIdentifier)));

        // Biome Enhanced feature registry
        registerFeature(biomeEnhancedConfiguredFeature, biomeEnhancedPlacedFeature, biomeEnhancedIdentifier);
        BiomeModifications.addFeature(BiomeSelectors.categories(biomeCategory), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MagicFungi.MOD_ID, biomeEnhancedIdentifier)));
    }

    private static void registerHostBiomeFeature(ConfiguredFeature<?, ?> configuredFeature, PlacedFeature placedFeature, String identifier, GenerationStep.Feature genStep, boolean enabled) {
        if(!enabled) return;

        registerFeature(configuredFeature, placedFeature, identifier);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.HOST_BIOME_KEY), genStep,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MagicFungi.MOD_ID, identifier)));
    }

    private static void registerFeature(ConfiguredFeature<?, ?> configuredFeature, PlacedFeature placedFeature, String identifier) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, identifier), configuredFeature);
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                new Identifier(MagicFungi.MOD_ID, identifier), placedFeature);
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
    public static final ConfiguredFeature<?, ?> MORBUS_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaRegularSpawnRatio);

    public static final PlacedFeature MORBUS_PLACED_FEATURE = MORBUS_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


    // Wither Rose - Host Biome
    public static final ConfiguredFeature<?, ?> WITHER_ROSE_CONFIGURED_FEATURE =
            generateMushroomFeatureSupplier(Blocks.WITHER_ROSE, MagicFungi.CONFIG.hostBiomeWitherRoseSpawnRatio);

    public static final PlacedFeature WITHER_ROSE_PLACED_FEATURE = WITHER_ROSE_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


    // Huge Morbus Mushroom Vegetation - Host Biome
    private static final ConfiguredFeature<?, ?> HUGE_LARGE_MORBUS_MUSHROOM = Feature.HUGE_RED_MUSHROOM.configure(new HugeMushroomFeatureConfig(
            BlockStateProvider.of(FeatureRegistry.LARGE_MORBUS),
            BlockStateProvider.of(FeatureRegistry.MUSHROOM_STEM), 2));

    private static final ConfiguredFeature<?, ?> HUGE_TALL_MORBUS_MUSHROOM = Feature.HUGE_RED_MUSHROOM.configure(new HugeMushroomFeatureConfig(
            BlockStateProvider.of(FeatureRegistry.TALL_MORBUS),
            BlockStateProvider.of(FeatureRegistry.MUSHROOM_STEM), 3));


    public static final ConfiguredFeature<?, ?> HUGE_MORBUS_VEGETATION_CONFIGURED_FEATURE = Feature.RANDOM_BOOLEAN_SELECTOR.configure(new RandomBooleanFeatureConfig(
            HUGE_LARGE_MORBUS_MUSHROOM::withPlacement,
            HUGE_TALL_MORBUS_MUSHROOM::withPlacement));

    public static final PlacedFeature HUGE_MORBUS_VEGETATION_PLACED_FEATURE = HUGE_MORBUS_VEGETATION_CONFIGURED_FEATURE.withPlacement(
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());


    // Host Dirt Ore Disk - Host Biome
    public static final ConfiguredFeature<?, ?> ORE_HOST_DIRT_CONFIGURED_FEATURE = Feature.ORE.configure(
            new OreFeatureConfig(BASE_STONE_OVERWORLD,
            BlockRegistry.HOST_DIRT.getDefaultState(),
            33));

    public static final PlacedFeature ORE_HOST_DIRT_PLACED_FEATURE = ORE_HOST_DIRT_CONFIGURED_FEATURE.withPlacement(
            OrePlacedFeaturesInvoker.modifiersWithCount(7,
            HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(160))));


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


    private static final BlockState LARGE_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false);
    private static final BlockState TALL_MORBUS = BlockRegistry.MORBUS_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
    private static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);

}