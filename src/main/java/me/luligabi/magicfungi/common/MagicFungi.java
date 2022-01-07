package me.luligabi.magicfungi.common;

import draylar.omegaconfig.OmegaConfig;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.item.glyph.GlyphRegistry;
import me.luligabi.magicfungi.common.item.spell.SpellRegistry;
import me.luligabi.magicfungi.common.misc.GameRuleRegistry;
import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.recipe.RecipeRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import me.luligabi.magicfungi.common.worldgen.biome.BiomeRegistry;
import me.luligabi.magicfungi.common.worldgen.feature.FeatureRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();

        GlyphRegistry.init();
        SpellRegistry.init();
        ItemRegistry.init();
        TagRegistry.init();

        FeatureRegistry.init();
        BiomeRegistry.init();

        RecipeRegistry.init();
        ScreenHandlingRegistry.init();

        ParticleRegistry.init();
        GameRuleRegistry.init();
    }

    public static final String MOD_ID = "magicfungi";

    public static final ModConfig CONFIG = OmegaConfig.register(ModConfig.class);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(ItemRegistry.GUIDE_BOOK));

                // Blocks
                stacks.add(new ItemStack(BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.MORBUS_MUSHROOM_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.HOST_GRASS_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.HOST_DIRT));
                stacks.add(new ItemStack(BlockRegistry.HOST_GRASS));
                stacks.add(new ItemStack(BlockRegistry.HOST_TALL_GRASS));
                stacks.add(new ItemStack(BlockRegistry.HOST_FERN));
                stacks.add(new ItemStack(BlockRegistry.LARGE_HOST_FERN));
                stacks.add(new ItemStack(BlockRegistry.GLYPH_CARVING_BLOCK));
                stacks.add(new ItemStack(BlockRegistry.SPELL_DISCOVERY_BLOCK));

                // Glyphs
                stacks.add(new ItemStack(GlyphRegistry.EXPONENTIA_GLYPH));

                // Utilis Glyphs
                stacks.add(new ItemStack(GlyphRegistry.PLUVIAM_GLYPH));
                stacks.add(new ItemStack(GlyphRegistry.CADENTIS_GLYPH));

                // Vivifica Glyphs
                stacks.add(new ItemStack(GlyphRegistry.PUDICITIAM_GLYPH));
                stacks.add(new ItemStack(GlyphRegistry.SANCTIFICARE_GLYPH));

                // Morbus Glyphs
                stacks.add(new ItemStack(GlyphRegistry.PARASITUS_GLYPH));
                stacks.add(new ItemStack(GlyphRegistry.CORRUMPERE_GLYPH));
                stacks.add(new ItemStack(GlyphRegistry.MALEDICTIO_GLYPH));


                // Impetus Spells
                stacks.add(new ItemStack(SpellRegistry.IGNEI_SPELL));
                stacks.add(new ItemStack(SpellRegistry.SCINTILLAM_SPELL));

                // Clypeus Spells
                stacks.add(new ItemStack(SpellRegistry.CADERE_SPELL));
                stacks.add(new ItemStack(SpellRegistry.GLACIES_SPELL));

                // Utilis Spells
                stacks.add(new ItemStack(SpellRegistry.TRACTABILE_SPELL));
                stacks.add(new ItemStack(SpellRegistry.CIBUS_SPELL));

                // Vivifica Spells
                stacks.add(new ItemStack(SpellRegistry.FERTILIS_SPELL));

                // Essences & Relics
                stacks.add(new ItemStack(ItemRegistry.IMPETUS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_ESSENCE));

                stacks.add(new ItemStack(ItemRegistry.IMPETUS_SWORD));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_SHIELD));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_PICKAXE));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_ELIXIR));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_SCYTHE));

                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_HELMET));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_CHESTPLATE));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_LEGGINGS));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_BOOTS));

                // Misc
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_ALLOY));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_ALLOY_NUGGET));
                stacks.add(new ItemStack(BlockRegistry.MAGICAL_FUNGI_ALLOY_BLOCK));
                stacks.add(new ItemStack(ItemRegistry.FUNGI_FERTILIZER));

                // Morbus Spreading/Corruption Items
                stacks.add(new ItemStack(ItemRegistry.MORBUS_RADIOMETER));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_CLOCK));
                stacks.add(new ItemStack(ItemRegistry.HEART_OF_VIVIFICA));
                stacks.add(new ItemStack(ItemRegistry.HEART_OF_MORBUS));

                // Stews
                stacks.add(new ItemStack(ItemRegistry.IMPETUS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_MUSHROOM_STEW));
            })
            .build();

}