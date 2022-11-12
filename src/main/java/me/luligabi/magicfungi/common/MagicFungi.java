package me.luligabi.magicfungi.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.entity.EntityRegistry;
import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.item.glyph.GlyphRegistry;
import me.luligabi.magicfungi.common.item.spell.SpellRegistry;
import me.luligabi.magicfungi.common.misc.*;
import me.luligabi.magicfungi.common.recipe.RecipeRegistry;
import me.luligabi.magicfungi.common.screenhandler.ScreenHandlingRegistry;
import me.luligabi.magicfungi.common.worldgen.LootTableRegistry;
import me.luligabi.magicfungi.common.worldgen.feature.FeatureRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class MagicFungi implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockRegistry.init();

        // Items & Tags
        GlyphRegistry.init();
        SpellRegistry.init();
        ItemRegistry.init();
        TagRegistry.init();

        // Worldgen
        FeatureRegistry.init();
        if(CONFIG.addModContentToChests) {
            LootTableRegistry.init();
        }

        // Recipe & UI
        RecipeRegistry.init();
        ScreenHandlingRegistry.init();

        // Misc
        EntityRegistry.init();
        ParticleRegistry.init();
        EventRegistry.init();
        GameRuleRegistry.init();
        if(CONFIG.addModContentToTrades) {
            TradeOfferRegistry.init();
        }
        CompostableRegistry.init();
    }


    private static ModConfig createConfig() {
        ModConfig finalConfig;
        LOGGER.info("Trying to read config file...");
        try {
            if(CONFIG_FILE.createNewFile()) {
                LOGGER.info("No config file found, creating a new one...");
                writeConfig(GSON.toJson(JsonParser.parseString(GSON.toJson(new ModConfig()))));
                finalConfig = new ModConfig();
                LOGGER.info("Successfully created default config file.");
            } else {
                LOGGER.info("A config file was found, loading it..");
                finalConfig = GSON.fromJson(new String(Files.readAllBytes(CONFIG_FILE.toPath())), ModConfig.class);
                if(finalConfig == null) {
                    throw new NullPointerException("The config file was empty.");
                } else {
                    LOGGER.info("Successfully loaded config file.");
                }
            }
        } catch(Exception e) {
            LOGGER.error("There was an error creating/loading the config file!", e);
            finalConfig = new ModConfig();
            LOGGER.warn("Defaulting to original config.");
        }
        return finalConfig;
    }

    public static void saveConfig(ModConfig modConfig) {
        try {
            writeConfig(GSON.toJson(JsonParser.parseString(GSON.toJson(modConfig))));
            LOGGER.info("Saved new config file.");
        } catch(Exception e) {
            LOGGER.error("There was an error saving the config file!", e);
        }
    }

    private static void writeConfig(String json) {
        try(PrintWriter printWriter = new PrintWriter(CONFIG_FILE)) {
            printWriter.write(json);
            printWriter.flush();
        } catch(IOException e) {
            LOGGER.error("Failed to write config file", e);
        }
    }

    public static final String MOD_ID = "magicfungi";

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

                // Glyphs
                stacks.add(new ItemStack(BlockRegistry.GLYPH_CARVING_BLOCK));
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
                stacks.add(new ItemStack(BlockRegistry.SPELL_DISCOVERY_BLOCK));

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

                // Essences
                stacks.add(new ItemStack(BlockRegistry.ESSENCE_EXTRACTOR_BLOCK));
                stacks.add(new ItemStack(ItemRegistry.IMPETUS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_ESSENCE));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_ESSENCE));

                // Relics
                stacks.add(new ItemStack(BlockRegistry.MAGIC_CONDENSER_BLOCK));
                stacks.add(new ItemStack(ItemRegistry.IMPETUS_SWORD));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_SHIELD));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_PICKAXE));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_ELIXIR));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_SCYTHE));

                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_HELMET));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_CHESTPLATE));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_LEGGINGS));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_BOOTS));

                // Magical Fungi Alloy
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_ALLOY));
                stacks.add(new ItemStack(ItemRegistry.MAGICAL_FUNGI_ALLOY_NUGGET));
                stacks.add(new ItemStack(BlockRegistry.MAGICAL_FUNGI_ALLOY_BLOCK));

                // Morbus Items
                stacks.add(new ItemStack(ItemRegistry.MORBUS_LEATHER));
                stacks.add(new ItemStack(ItemRegistry.RESEARCH_LOG));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_CLOCK));
                stacks.add(new ItemStack(ItemRegistry.HEART_OF_VIVIFICA));
                stacks.add(new ItemStack(ItemRegistry.HEART_OF_MORBUS));

                // Fungi Fertilizer & Stews
                stacks.add(new ItemStack(BlockRegistry.MOLDING_CAULDRON_BLOCK));
                stacks.add(new ItemStack(ItemRegistry.BALL_OF_MOLD));
                stacks.add(new ItemStack(ItemRegistry.FUNGI_FERTILIZER));
                stacks.add(new ItemStack(ItemRegistry.IMPETUS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.CLYPEUS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.UTILIS_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.VIVIFICA_MUSHROOM_STEW));
                stacks.add(new ItemStack(ItemRegistry.MORBUS_MUSHROOM_STEW));

                // Spawn Eggs
                stacks.add(new ItemStack(EntityRegistry.MORBUS_MOOSHROOM_SPAWN_EGG));
            })
            .build();

    public static final Logger LOGGER;
    private static final Gson GSON;

    private static final File CONFIG_FILE;
    public static final ModConfig CONFIG;

    static {
        LOGGER = LoggerFactory.getLogger("Magic Fungi");
        GSON = new GsonBuilder().setPrettyPrinting().create();

        CONFIG_FILE = new File(String.format("%s%s%s.json", FabricLoader.getInstance().getConfigDir(), File.separator, MOD_ID));
        CONFIG = createConfig();
    }
}