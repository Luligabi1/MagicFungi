package me.luligabi.magicfungi.common.registry;

import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ConfiguredFeatureRegistry {

    @SuppressWarnings("deprecation")
    public static void init() {
        // Impetus
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, impetus.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.IMPETUS_MUSHROOM_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(1)));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, impetusSavanna.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.IMPETUS_MUSHROOM_CONFIG_SAVANNA).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(3)));

        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, impetus);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SAVANNA), GenerationStep.Feature.VEGETAL_DECORATION, impetusSavanna);

        // Clypeus
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, clypeus.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.CLYPEUS_MUSHROOM_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(1)));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, clypeusIcy.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.CLYPEUS_MUSHROOM_CONFIG_ICY).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(3)));

        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, clypeus);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.VEGETAL_DECORATION, clypeusIcy);

        // Utilis
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, utilis.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.UTILIS_MUSHROOM_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(1)));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, utilisExtremeHills.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.UTILIS_MUSHROOM_CONFIG_EXTREME_HILLS).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(3)));

        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, utilis);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS, Biome.Category.MUSHROOM), GenerationStep.Feature.VEGETAL_DECORATION, utilisExtremeHills);

        // Vivifica
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, vivifica.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.VIVIFICA_MUSHROOM_CONFIG).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(2)));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, vivificaJungle.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.VIVIFICA_MUSHROOM_CONFIG_JUNGLE).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE.repeat(3)));

        BiomeModifications.addFeature(BiomeSelectors.categories(OVERWORLD_BIOMES), GenerationStep.Feature.VEGETAL_DECORATION, vivifica);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, vivificaJungle);

        //TODO: Add morbus generation
    }

    // Impetus Mushroom - Regular
    public static final RegistryKey<ConfiguredFeature<?, ?>> impetus = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "impetus_mushroom"));

    private static final RandomPatchFeatureConfig IMPETUS_MUSHROOM_CONFIG = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.IMPETUS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build();

    // Impetus Mushroom - Savanna Biomes
    public static final RegistryKey<ConfiguredFeature<?, ?>> impetusSavanna = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "impetus_mushroom_savanna"));

    private static final RandomPatchFeatureConfig IMPETUS_MUSHROOM_CONFIG_SAVANNA = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.IMPETUS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(24).build();

    // Clypeus Mushroom - Regular
    public static final RegistryKey<ConfiguredFeature<?, ?>> clypeus = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom"));

    private static final RandomPatchFeatureConfig CLYPEUS_MUSHROOM_CONFIG = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.CLYPEUS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build();

    // Clypeus Mushroom - Savanna Biomes
    public static final RegistryKey<ConfiguredFeature<?, ?>> clypeusIcy = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "clypeus_mushroom_icy"));

    private static final RandomPatchFeatureConfig CLYPEUS_MUSHROOM_CONFIG_ICY = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.CLYPEUS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(24).build();

    // Utilis Mushroom - Regular
    public static final RegistryKey<ConfiguredFeature<?, ?>> utilis = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "utilis_mushroom"));

    private static final RandomPatchFeatureConfig UTILIS_MUSHROOM_CONFIG = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.UTILIS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build();

    // Utilis Mushroom - Extreme Hills
    public static final RegistryKey<ConfiguredFeature<?, ?>> utilisExtremeHills = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "utilis_mushroom_extreme_hills"));

    private static final RandomPatchFeatureConfig UTILIS_MUSHROOM_CONFIG_EXTREME_HILLS = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.UTILIS_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(24).build();


    // Vivifica Mushroom - Regular
    public static final RegistryKey<ConfiguredFeature<?, ?>> vivifica = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom"));

    private static final RandomPatchFeatureConfig VIVIFICA_MUSHROOM_CONFIG = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.VIVIFICA_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build();

    // Vivifica Mushroom - Jungle Biome
    public static final RegistryKey<ConfiguredFeature<?, ?>> vivificaJungle = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom_jungle"));

    private static final RandomPatchFeatureConfig VIVIFICA_MUSHROOM_CONFIG_JUNGLE = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.VIVIFICA_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(24).build();

    // List of Overworld Biome Categories excluding biomes that would be odd for mushrooms to spawn (such as oceans and deserts)
    private static final Biome.Category[] OVERWORLD_BIOMES = {Biome.Category.PLAINS,
            Biome.Category.FOREST,
            Biome.Category.TAIGA,
            Biome.Category.SAVANNA,
            Biome.Category.ICY,
            Biome.Category.SWAMP,
            Biome.Category.JUNGLE,
            Biome.Category.UNDERGROUND,
            Biome.Category.MUSHROOM};
}