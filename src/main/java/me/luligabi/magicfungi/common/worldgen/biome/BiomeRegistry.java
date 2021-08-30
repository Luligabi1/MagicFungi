package me.luligabi.magicfungi.common.worldgen.biome;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class BiomeRegistry {

    @SuppressWarnings("deprecation")
    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MagicFungi.MOD_ID, "host_surface"), HOST_BIOME_SURFACE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, HOST_BIOME_KEY.getValue(), HOST_BIOME);

        OverworldBiomes.addContinentalBiome(HOST_BIOME_KEY, OverworldClimate.TEMPERATE, MagicFungi.CONFIG.hostBiomeSpawnRate);
    }

    public static final RegistryKey<Biome> HOST_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MagicFungi.MOD_ID, "host_biome"));

    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> HOST_BIOME_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(
                    BlockRegistry.HOST_GRASS_BLOCK.getDefaultState(),
                    BlockRegistry.HOST_DIRT.getDefaultState(),
                    Blocks.GRAVEL.getDefaultState()));

    private static final Biome HOST_BIOME = createHostBiome();

    private static Biome createHostBiome() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(HOST_BIOME_SURFACE_BUILDER);

        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.NONE)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(1.0F)
                .downfall(0.0F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x6a3d11)
                        .foliageColor(0x4c2c0c)
                        .waterColor(0x617b64)
                        .waterFogColor(0x232317)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x6eb1ff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}