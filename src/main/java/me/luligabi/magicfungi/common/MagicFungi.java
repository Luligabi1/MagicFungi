package me.luligabi.magicfungi.common;

import draylar.omegaconfig.OmegaConfig;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.item.glyph.GlyphRegistry;
import me.luligabi.magicfungi.common.item.spell.SpellRegistry;
import me.luligabi.magicfungi.common.particle.ParticleRegistry;
import me.luligabi.magicfungi.common.recipe.RecipeRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import me.luligabi.magicfungi.common.worldgen.biome.BiomeRegistry;
import me.luligabi.magicfungi.common.worldgen.feature.ConfiguredFeatureRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        ItemRegistry.initGuideBook();

        BlockRegistry.init();

        GlyphRegistry.init();
        SpellRegistry.init();
        ItemRegistry.init();

        ConfiguredFeatureRegistry.init();
        BiomeRegistry.init();

        RecipeRegistry.init();
        ScreenHandlingRegistry.init();

        ParticleRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";

    public static final ModConfig CONFIG = OmegaConfig.register(ModConfig.class);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK))
            .build();

}