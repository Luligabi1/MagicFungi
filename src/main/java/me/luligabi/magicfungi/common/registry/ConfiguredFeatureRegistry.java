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
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ConfiguredFeatureRegistry { // TODO: Add features for all mushrooms to generate.

    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, vivificaJungle.getValue(), Feature.RANDOM_PATCH.configure(ConfiguredFeatureRegistry.VIVIFICA_MUSHROOM_CONFIG_JUNGLE).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(5))).repeat(3));

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, vivificaJungle);
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> vivificaJungle = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
            new Identifier(MagicFungi.MOD_ID, "vivifica_mushroom_jungle"));

    private static final RandomPatchFeatureConfig VIVIFICA_MUSHROOM_CONFIG_JUNGLE = new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.VIVIFICA_MUSHROOM_BLOCK.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(64).build();
}