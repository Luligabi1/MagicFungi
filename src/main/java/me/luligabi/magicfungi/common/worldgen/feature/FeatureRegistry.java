package me.luligabi.magicfungi.common.worldgen.feature;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.util.WorldUtil;
import me.luligabi.magicfungi.common.worldgen.biome.BiomeRegistry;
import me.luligabi.magicfungi.mixin.OrePlacedFeaturesInvoker;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.BASE_STONE_OVERWORLD;

@SuppressWarnings("unused")
public class FeatureRegistry {

    public static void init() {
        // Regular Magic Mushroom gen
        addMagicMushroomFeature(FeatureRegistry.IMPETUS_ID, FeatureRegistry.IMPETUS_ENHANCED_ID, MagicFungi.CONFIG.canGenerateImpetusMushrooms, Biome.Category.SAVANNA);
        addMagicMushroomFeature(FeatureRegistry.CLYPEUS_ID, FeatureRegistry.CLYPEUS_ENHANCED_ID, MagicFungi.CONFIG.canGenerateClypeusMushrooms, Biome.Category.ICY);
        addMagicMushroomFeature(FeatureRegistry.UTILIS_ID, FeatureRegistry.UTILIS_ENHANCED_ID, MagicFungi.CONFIG.canGenerateUtilisMushrooms,  Biome.Category.MOUNTAIN, Biome.Category.EXTREME_HILLS);
        addMagicMushroomFeature(FeatureRegistry.VIVIFICA_ID, FeatureRegistry.VIVIFICA_ENHANCED_ID, MagicFungi.CONFIG.canGenerateVivificaMushrooms, Biome.Category.JUNGLE);

        addHostBiomeFeature(FeatureRegistry.MORBUS_ID, GenerationStep.Feature.VEGETAL_DECORATION, MagicFungi.CONFIG.canGenerateMorbusMushrooms);

        // TODO: Add Morbus & Morbus Tall Grass gen here.
        addHostBiomeFeature(FeatureRegistry.WITHER_ROSE_ID, GenerationStep.Feature.VEGETAL_DECORATION, MagicFungi.CONFIG.canGenerateWitherRoseHostBiome);

        addHostBiomeFeature(FeatureRegistry.MORBUS_MUSHROOM_VEGETATION_ID, GenerationStep.Feature.TOP_LAYER_MODIFICATION, true);

        //registerFeature(ORE_HOST_DIRT_CONFIGURED_FEATURE, ORE_HOST_DIRT_PLACED_FEATURE, "ore_host_dirt");
    }

    private static void addMagicMushroomFeature(String regularIdentifier, String biomeEnhancedIdentifier, boolean enabled, Biome.Category... biomeCategory) {
        if(!enabled) return;
        // Regular feature registry
        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(regularIdentifier)));

        // Biome Enhanced feature
        BiomeModifications.addFeature(BiomeSelectors.categories(biomeCategory), GenerationStep.Feature.VEGETAL_DECORATION,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(biomeEnhancedIdentifier)));
    }

    private static void addHostBiomeFeature(String identifier, GenerationStep.Feature genStep, boolean enabled) {
        if(!enabled) return;
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeRegistry.HOST_BIOME_KEY), genStep,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(identifier)));
    }

    // Impetus Mushroom
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> IMPETUS_REGULAR_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.impetusRegularSpawnRatio, FeatureRegistry.IMPETUS_ID);

    private static final RegistryEntry<PlacedFeature> IMPETUS_REGULAR_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.IMPETUS_ID, IMPETUS_REGULAR_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.impetusBiomeEnhancedSpawnRatio, FeatureRegistry.IMPETUS_ENHANCED_ID);

    private static final RegistryEntry<PlacedFeature> IMPETUS_BIOME_ENHANCED_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.IMPETUS_ENHANCED_ID, IMPETUS_BIOME_ENHANCED_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final String IMPETUS_ID = "impetus_mushroom";
    private static final String IMPETUS_ENHANCED_ID = "impetus_mushroom_enhanced";

    // Clypeus Mushroom
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> CLYPEUS_REGULAR_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.clypeusRegularSpawnRatio, FeatureRegistry.CLYPEUS_ID);

    private static final RegistryEntry<PlacedFeature> CLYPEUS_REGULAR_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.CLYPEUS_ID, CLYPEUS_REGULAR_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.clypeusBiomeEnhancedSpawnRatio, FeatureRegistry.CLYPEUS_ENHANCED_ID);

    private static final RegistryEntry<PlacedFeature> CLYPEUS_BIOME_ENHANCED_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.CLYPEUS_ENHANCED_ID, CLYPEUS_BIOME_ENHANCED_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    private static final String CLYPEUS_ID = "clypeus_mushroom";
    private static final String CLYPEUS_ENHANCED_ID = "clypeus_mushroom_enhanced";

    // Utilis Mushroom
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> UTILIS_REGULAR_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.utilisRegularSpawnRatio, FeatureRegistry.UTILIS_ID);

    private static final RegistryEntry<PlacedFeature> UTILIS_REGULAR_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.UTILIS_ID, UTILIS_REGULAR_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE =
    flowerLikeFeature(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.utilisBiomeEnhancedSpawnRatio, FeatureRegistry.UTILIS_ENHANCED_ID);

    private static final RegistryEntry<PlacedFeature> UTILIS_BIOME_ENHANCED_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.UTILIS_ENHANCED_ID, UTILIS_BIOME_ENHANCED_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    private static final String UTILIS_ID = "utilis_mushroom";
    private static final String UTILIS_ENHANCED_ID = "utilis_mushroom_enhanced";

    // Vivifica Mushroom
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> VIVIFICA_REGULAR_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaRegularSpawnRatio, FeatureRegistry.VIVIFICA_ID);

    private static final RegistryEntry<PlacedFeature> VIVIFICA_REGULAR_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.VIVIFICA_ID, VIVIFICA_REGULAR_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaBiomeEnhancedSpawnRatio, FeatureRegistry.VIVIFICA_ENHANCED_ID);

    private static final RegistryEntry<PlacedFeature> VIVIFICA_BIOME_ENHANCED_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.VIVIFICA_ENHANCED_ID, VIVIFICA_BIOME_ENHANCED_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    private static final String VIVIFICA_ID = "vivifica_mushroom";
    private static final String VIVIFICA_ENHANCED_ID = "vivifica_mushroom_enhanced";

    // Morbus Mushroom - Host Biome
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MORBUS_CONFIGURED_FEATURE =
            flowerLikeFeature(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK, MagicFungi.CONFIG.vivificaRegularSpawnRatio, FeatureRegistry.MORBUS_ID);

    private static final RegistryEntry<PlacedFeature> MORBUS_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.MORBUS_ID, MORBUS_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    private static final String MORBUS_ID = "morbus_mushroom";

    // Wither Rose - Host Biome
    private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WITHER_ROSE_CONFIGURED_FEATURE =
            flowerLikeFeature(Blocks.WITHER_ROSE, MagicFungi.CONFIG.hostBiomeWitherRoseSpawnRatio, FeatureRegistry.WITHER_ROSE_ID);

    private static final RegistryEntry<PlacedFeature> WITHER_ROSE_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.WITHER_ROSE_ID, WITHER_ROSE_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );


    private static final String WITHER_ROSE_ID = "wither_rose_host_biome";

    // Morbus Mushroom Vegetation - Host Biome
    private static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> LARGE_MORBUS_MUSHROOM = ConfiguredFeatures.register("large_morbus_mushroom", Feature.HUGE_RED_MUSHROOM,
            new HugeMushroomFeatureConfig(
            BlockStateProvider.of(WorldUtil.LARGE_MORBUS),
            BlockStateProvider.of(WorldUtil.MUSHROOM_STEM), 2)
    );

    private static final RegistryEntry<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> TALL_MORBUS_MUSHROOM = ConfiguredFeatures.register("tall_morbus_mushroom", Feature.HUGE_BROWN_MUSHROOM,
            new HugeMushroomFeatureConfig(
            BlockStateProvider.of(WorldUtil.TALL_MORBUS),
            BlockStateProvider.of(WorldUtil.MUSHROOM_STEM), 3)
    );


    private static final RegistryEntry<ConfiguredFeature<RandomBooleanFeatureConfig, ?>> MORBUS_MUSHROOM_VEGETATION_CONFIGURED_FEATURE = ConfiguredFeatures.register(FeatureRegistry.MORBUS_MUSHROOM_VEGETATION_ID, Feature.RANDOM_BOOLEAN_SELECTOR,
            new RandomBooleanFeatureConfig(
            PlacedFeatures.createEntry(FeatureRegistry.LARGE_MORBUS_MUSHROOM),
            PlacedFeatures. createEntry(FeatureRegistry.TALL_MORBUS_MUSHROOM)
    ));

    private static final RegistryEntry<PlacedFeature> MORBUS_MUSHROOM_VEGETATION_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.MORBUS_MUSHROOM_VEGETATION_ID, MORBUS_MUSHROOM_VEGETATION_CONFIGURED_FEATURE,
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
    );

    private static final String MORBUS_MUSHROOM_VEGETATION_ID = "morbus_mushroom_vegetation";


    // Host Dirt Ore Disk - Host Biome
    private static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_HOST_DIRT_CONFIGURED_FEATURE = ConfiguredFeatures.register(FeatureRegistry.HOST_DIRT_ORE_DISK_ID, Feature.ORE,
            new OreFeatureConfig(
                    BASE_STONE_OVERWORLD,
                    BlockRegistry.HOST_DIRT.getDefaultState(),
                    33
            )
    );

    public static final RegistryEntry<PlacedFeature> ORE_HOST_DIRT_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.HOST_DIRT_ORE_DISK_ID, ORE_HOST_DIRT_CONFIGURED_FEATURE,
            OrePlacedFeaturesInvoker.modifiersWithCount(7,
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(160)))
    );

    private static final String HOST_DIRT_ORE_DISK_ID = "host_dirt_ore_disk";


    private static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> HOST_GRASS_BONEMEAL_CONFIGURED_FEATURE = ConfiguredFeatures.register(FeatureRegistry.HOST_GRASS_BONEMEAL_ID, Feature.SIMPLE_BLOCK,
            new SimpleBlockFeatureConfig(BlockStateProvider.of(BlockRegistry.HOST_GRASS.getDefaultState()))
    );

    public static final RegistryEntry<PlacedFeature> HOST_GRASS_BONEMEAL_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.HOST_GRASS_BONEMEAL_ID, HOST_GRASS_BONEMEAL_CONFIGURED_FEATURE,
            PlacedFeatures.isAir()
    );

    private static final String HOST_GRASS_BONEMEAL_ID = "host_grass_bonemeal";


    private static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> SINGLE_WITHER_ROSE_CONFIGURED_FEATURE = ConfiguredFeatures.register(FeatureRegistry.SINGLE_WITHER_ROSE_ID, Feature.SIMPLE_BLOCK,
            new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.WITHER_ROSE.getDefaultState()))
    );

    public static final RegistryEntry<PlacedFeature> SINGLE_WITHER_ROSE_PLACED_FEATURE = PlacedFeatures.register(FeatureRegistry.SINGLE_WITHER_ROSE_ID, WITHER_ROSE_CONFIGURED_FEATURE,
            PlacedFeatures.isAir()
    );

    private static final String SINGLE_WITHER_ROSE_ID = "single_wither_rose";


    private static RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> flowerLikeFeature(Block block, int tries, String id) {
        return flowerLikeFeature(block, tries, 7, 2, id);
    }

    private static RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> flowerLikeFeature(Block block, int tries, int xzSpread, int ySpread, String id) {
        return ConfiguredFeatures.register(id, Feature.FLOWER, new RandomPatchFeatureConfig(tries, xzSpread, ySpread,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(block))))
        );
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

}