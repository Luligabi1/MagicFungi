package me.luligabi.magicfungi.common.worldgen.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.mixin.VanillaSurfaceRulesInvoker;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.SurfaceRuleManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class HostBiomeProvider extends Region {


    public HostBiomeProvider(int weight) {
        super(BiomeRegistry.HOST_BIOME_PROVIDER_ID, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addBiome(mapper,
                ParameterUtils.Temperature.NEUTRAL,
                ParameterUtils.Humidity.NEUTRAL,
                ParameterUtils.Continentalness.FAR_INLAND,
                ParameterUtils.Erosion.EROSION_0,
                ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING,
                ParameterUtils.Depth.SURFACE,
                1.0F,
                BiomeRegistry.HOST_BIOME_KEY);
        /*addBiomeSimilar(mapper, BiomeKeys.PLAINS, BiomeKeys.PLAINS);
        addBiomeSimilar(mapper, BiomeKeys.MEADOW, BiomeKeys.MEADOW);
        addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_HILLS);
        addBiomeSimilar(mapper, BiomeKeys.DESERT, BiomeKeys.DESERT);
        addBiomeSimilar(mapper, BiomeKeys.RIVER, BiomeKeys.RIVER);

        addBiomeSimilar(mapper, BiomeKeys.FOREST, BiomeKeys.FOREST);
        addBiomeSimilar(mapper, BiomeKeys.BIRCH_FOREST, BiomeKeys.BIRCH_FOREST);
        addBiomeSimilar(mapper, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA);
        addBiomeSimilar(mapper, BiomeKeys.SWAMP, BiomeKeys.SWAMP);*/
    }



    public Optional<MaterialRules.MaterialRule> getOverworldSurfaceRules() { return Optional.of(MaterialRules.condition(MaterialRules.biome(BiomeRegistry.HOST_BIOME_KEY), createHostBiomeSurfaceRule())); }


    public static MaterialRules.MaterialRule createHostBiomeSurfaceRule() {
        MaterialRules.MaterialCondition above97 = MaterialRules.aboveY(YOffset.fixed(97), 2);
        MaterialRules.MaterialCondition above62 = MaterialRules.aboveY(YOffset.fixed(62), 0);
        MaterialRules.MaterialCondition above63_0 = MaterialRules.aboveY(YOffset.fixed(63), 0);
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition isAboveWaterLevel = MaterialRules.water(0, 0);
        MaterialRules.MaterialCondition surfacerules$conditionsource8 = MaterialRules.waterWithStoneDepth(-6, -1);
        MaterialRules.MaterialCondition isHole = MaterialRules.hole();
        MaterialRules.MaterialCondition isFrozenOcean = MaterialRules.biome(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN);
        MaterialRules.MaterialCondition isSteep = MaterialRules.steepSlope();
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, HOST_GRASS_BLOCK), HOST_DIRT);
        MaterialRules.MaterialRule stoneLinedGravel = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE), GRAVEL);
        MaterialRules.MaterialRule surfacerules$rulesource3 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.STONY_PEAKS), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, -0.0125D, 0.0125D), CALCITE), STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.STONY_SHORE), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.GRAVEL, -0.05D, 0.05D), stoneLinedGravel), STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_HILLS), MaterialRules.condition(surfaceNoiseAbove(1.0D), STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.DRIPSTONE_CAVES), STONE));
        MaterialRules.MaterialRule surfacerules$rulesource6 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.SNOWY_SLOPES), MaterialRules.condition(isSteep, STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.JAGGED_PEAKS), STONE), MaterialRules.condition(MaterialRules.biome(BiomeKeys.GROVE), HOST_DIRT), surfacerules$rulesource3, MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_SAVANNA), MaterialRules.condition(surfaceNoiseAbove(1.75D), STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS), MaterialRules.sequence(MaterialRules.condition(surfaceNoiseAbove(2.0D), stoneLinedGravel), MaterialRules.condition(surfaceNoiseAbove(1.0D), STONE), MaterialRules.condition(surfaceNoiseAbove(-1.0D), HOST_DIRT), stoneLinedGravel)), HOST_DIRT);
        MaterialRules.MaterialRule atOrAboveWaterLevelRules = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.SNOWY_SLOPES), MaterialRules.condition(isSteep, STONE)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.JAGGED_PEAKS), MaterialRules.condition(isSteep, STONE)), surfacerules$rulesource3, MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_SAVANNA), MaterialRules.sequence(MaterialRules.condition(surfaceNoiseAbove(1.75D), STONE))), MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS), MaterialRules.sequence(MaterialRules.condition(surfaceNoiseAbove(2.0D), stoneLinedGravel), MaterialRules.condition(surfaceNoiseAbove(1.0D), STONE), MaterialRules.condition(surfaceNoiseAbove(-1.0D), grassSurface), stoneLinedGravel)), grassSurface);
        MaterialRules.MaterialRule surfaceRules = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.WOODED_BADLANDS), MaterialRules.condition(above97, grassSurface)), MaterialRules.condition(MaterialRules.biome(BiomeKeys.SWAMP), MaterialRules.condition(above62, MaterialRules.condition(MaterialRules.not(above63_0), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0D), WATER)))))), MaterialRules.condition(MaterialRules.biome(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, stoneLinedGravel))), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.sequence(MaterialRules.condition(isFrozenOcean, MaterialRules.condition(isHole, MaterialRules.sequence(MaterialRules.condition(isAboveWaterLevel, AIR), WATER))), atOrAboveWaterLevelRules))), MaterialRules.condition(surfacerules$conditionsource8, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.condition(isFrozenOcean, MaterialRules.condition(isHole, WATER))), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, surfacerules$rulesource6))), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(BiomeKeys.FROZEN_PEAKS, BiomeKeys.JAGGED_PEAKS), STONE), stoneLinedGravel)));

        List<MaterialRules.MaterialRule> afterBedrockRules = SurfaceRuleManager.getDefaultSurfaceRuleAdditionsForStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.AFTER_BEDROCK);

        ImmutableList.Builder<MaterialRules.MaterialRule> builder;
        if (!afterBedrockRules.isEmpty()) {
            builder = ImmutableList.builder();
            builder.addAll(afterBedrockRules);
            builder.add(surfaceRules);
            surfaceRules = MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new));
        }

        builder = ImmutableList.builder();
        builder.addAll(SurfaceRuleManager.getDefaultSurfaceRuleAdditionsForStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK));

        builder.add(MaterialRules.condition(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK));

        MaterialRules.MaterialRule surfacerules$rulesource9 = MaterialRules.condition(MaterialRules.surface(), surfaceRules);
        builder.add(surfacerules$rulesource9);
        builder.add(MaterialRules.condition(MaterialRules.verticalGradient("deepslate", YOffset.fixed(0), YOffset.fixed(8)), DEEPSLATE));
        return MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new));
    }

    private static MaterialRules.MaterialCondition surfaceNoiseAbove(double value) { return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, value / 8.25D, Double.MAX_VALUE); }

    private static final MaterialRules.MaterialRule AIR = VanillaSurfaceRulesInvoker.block(Blocks.AIR);
    private static final MaterialRules.MaterialRule HOST_GRASS_BLOCK = VanillaSurfaceRulesInvoker.block(BlockRegistry.HOST_GRASS_BLOCK);
    private static final MaterialRules.MaterialRule HOST_DIRT = VanillaSurfaceRulesInvoker.block(BlockRegistry.HOST_DIRT);
    private static final MaterialRules.MaterialRule STONE = VanillaSurfaceRulesInvoker.block(Blocks.STONE);
    private static final MaterialRules.MaterialRule CALCITE = VanillaSurfaceRulesInvoker.block(Blocks.CALCITE);
    private static final MaterialRules.MaterialRule GRAVEL = VanillaSurfaceRulesInvoker.block(Blocks.GRAVEL);
    private static final MaterialRules.MaterialRule DEEPSLATE = VanillaSurfaceRulesInvoker.block(Blocks.DEEPSLATE);
    private static final MaterialRules.MaterialRule BEDROCK = VanillaSurfaceRulesInvoker.block(Blocks.BEDROCK);

    private static final MaterialRules.MaterialRule WATER = VanillaSurfaceRulesInvoker.block(Blocks.WATER);

}