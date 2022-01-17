package me.luligabi.magicfungi.common.worldgen.biome;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.worldgen.feature.FeatureRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

public class BiomeRegistry implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Registry.register(BuiltinRegistries.BIOME, HOST_BIOME_KEY.getValue(), HOST_BIOME);

        BiomeProviders.register(new Identifier(MagicFungi.MOD_ID, "host_biome_provider"), new HostBiomeProvider(new Identifier(MagicFungi.MOD_ID, "host_biome_provider"), MagicFungi.CONFIG.hostBiomeSpawnRate));
    }

    public static final RegistryKey<Biome> HOST_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "host_biome"));

    private static final Biome HOST_BIOME = createHostBiome();

    private static Biome createHostBiome() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100, false);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        //generationSettings.surfaceBuilder(HOST_BIOME_SURFACE_BUILDER);

        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);

        // DefaultBiomes#addMineables, but with ORE_HOST_DIRT_PLACED_FEATURE replacing ORE_DIRT.
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, FeatureRegistry.ORE_HOST_DIRT_PLACED_FEATURE);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRAVEL);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRANITE_UPPER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRANITE_LOWER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIORITE_UPPER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIORITE_LOWER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_ANDESITE_UPPER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_ANDESITE_LOWER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_TUFF);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.GLOW_LICHEN);

        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);


        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.NONE)
                .temperature(1.0F)
                .downfall(0.0F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x6a3d11)
                        .foliageColor(0x4c2c0c)
                        .waterColor(0x617b64) // TODO: Change water color
                        .waterFogColor(0x232317) // TODO: Test water fog color
                        .fogColor(0xc0d8ff)
                        .skyColor(0x6eb1ff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}