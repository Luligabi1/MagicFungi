package me.luligabi.magicfungi.common;


import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import org.jetbrains.annotations.Nullable;

public class ModConfig implements Config {

    @Comment(value = "Cooldown for Ignei Spell usage.")
    public int igneiSpellCooldown = 12;

    @Comment(value  = "Cooldown for Scintillam Spell usage.")
    public int scintillamSpellCooldown = 22;


    @Comment(value  = "Cooldown for Cadere Spell usage.")
    public int cadereSpellCooldown = 35;

    @Comment(value  = "Cooldown for Glacies Spell usage.")
    public int glaciesSpellCooldown = 40;


    @Comment(value  = "Cooldown for Tractabile Spell usage.")
    public int tractabileSpellCooldown = 3;

    @Comment(value  = "Cooldown for Cibus Spell usage.")
    public int cibusSpellCooldown = 160;


    @Comment(value  = "Cooldown for Fertilis Spell usage.")
    public int fertilisSpellCooldown = 170;


    @Comment(value = "Duration of Parasitus Glyph's effects.")
    public int parasitusGlyphEffectTime = 7;

    @Comment(value = "Duration of Cadere Spell's effects.")
    public int cadereSpellEffectTime = 12;

    @Comment(value = "Duration of Glacies Spell's effects.")
    public int glaciesSpellEffectTime = 10;

    @Comment(value = "Hunger satiated by Cibus Spell. 1 = half drumstick.")
    public int cibusSpellHungerModifier = 9;

    @Comment(value = "Saturation given by Cibus Spell.")
    public float cibusSpellSaturationModifier = 0.8F;


    @Comment(value = "Utilis Pickaxe's haste effect duration in seconds.")
    public int utilisPickaxeEffectDuration = 4;

    @Comment(value = "Utilis Pickaxe's haste effect strength. 0 = I, 1 = II, etc.")
    public int utilisPickaxeEffectStrength = 2;

    @Comment(value = "Morbus Scythe's wither effect duration in seconds.")
    public int morbusScytheEffectDuration = 8;

    @Comment(value = "Morbus Scythe's wither effect strength. 0 = I, 1 = II, etc.")
    public int morbusScytheEffectStrength = 0;


    @Comment(value = "Whether Impetus Mushrooms are generated naturally. This will not impact previously generated chunks.")
    public boolean  canGenerateImpetusMushrooms = true;

    @Comment(value = "Whether Clypeus Mushrooms are generated naturally. This will not impact previously generated chunks.")
    public boolean  canGenerateClypeusMushrooms = true;

    @Comment(value = "Whether Impetus Mushrooms are generated naturally. This will not impact previously generated chunks.")
    public boolean  canGenerateUtilisMushrooms = true;

    @Comment(value = "Whether Vivifica Mushrooms are generated naturally. This will not impact previously generated chunks.")
    public boolean  canGenerateVivificaMushrooms = true;

    /*@Comment(value = "Whether Morbus Mushrooms are generated naturally. This will not impact previously generated chunks.")
    public boolean  canGenerateMorbusMushrooms = true;*/


    @Comment(value = "Impetus Mushrooms' spawn ratio at normal circumstances.")
    public int impetusRegularSpawnRatio = 12;

    @Comment(value = "Impetus Mushrooms' spawn ratio on biomes with enhanced ratios (check the book guide for info on such biomes).")
    public int impetusBiomeEnhancedSpawnRatio = 28;


    @Comment(value = "Clypeus Mushrooms' spawn ratio at normal circumstances.")
    public int clypeusRegularSpawnRatio = 12;

    @Comment(value = "Clypeus Mushrooms' spawn ratio on biomes with enhanced ratios (check the book guide for info on such biomes).")
    public int clypeusBiomeEnhancedSpawnRatio = 28;


    @Comment(value = "Utilis Mushrooms' spawn ratio at normal circumstances.")
    public int utilisRegularSpawnRatio = 12;

    @Comment(value = "Utilis Mushrooms' spawn ratio on biomes with enhanced ratios (check the book guide for info on such biomes).")
    public int utilisBiomeEnhancedSpawnRatio = 28;


    @Comment(value = "Vivifica Mushrooms' spawn ratio at normal circumstances.")
    public int vivificaRegularSpawnRatio = 12;

    @Comment(value = "Vivifica Mushrooms' spawn ratio on biomes with enhanced ratios (check the book guide for info on such biomes).")
    public int vivificaBiomeEnhancedSpawnRatio = 28;


    @Comment(value = "Spawnrate for generation of the Host Biome. Increase if using any biome overhaul-styled mods, as otherwise the biome will be exceedingly rare.")
    public double hostBiomeSpawnRate = 0.2;



    @Comment(value = "Whether Host Grass will start spreading towards non-grass blocks after X in-game days.\nThis only affects the default gamerule value for this setting.\nTo change this on already generated worlds, use /gamerule doMorbusSpread.\nKeep in mind this can be overridden by Morbus corruption items. Check canUseMorbusCorruptionItems for further information.")
    public boolean doMorbusSpread = false;

    @Comment(value = "In-game days until Host Grass blocks start spreading beyond the Host Biome.\nThis only affects the default gamerule value for this setting.\nTo change this on already generated worlds, use /gamerule morbusSpreadStartingDay")
    public int morbusSpreadStartingDay = 100;

    @Comment(value = "Enables the usage of the Maledictio Glyph and Heart of Morbus.\nIf you plan to use this mod on a public server, disable this to avoid griefing.")
    public boolean canUseMorbusCorruptionItems = false;

    // Morbus Corruption Increases/Decreases

    //TODO: Add config to disable Morbus Corruption

    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when attacking with an Impetus Sword.")
    public float impetusSwordCorruptionDecrease = 0.25F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when defending with a Clypeus Shield.")
    public float clypeusShieldCorruptionDecrease = 0.25F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when mining blocks with an Utilis Pickaxe.")
    public float utilisPickaxeCorruptionDecrease = 0.05F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when drinking Vivifica Elixir.")
    public float vivificaElixirCorruptionDecrease = 1.25F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will increase when attacking with a Morbus Scythe")
    public float morbusScytheCorruptionIncrease = 0.75F;


    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when using a Heart of Vivifica.")
    public float heartOfVivificaCorruptionDecrease = 7.5F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will increase when using a Heart of Morbus.")
    public float heartOfMorbusCorruptionIncrease = 7.5F;


    @Comment(value = "Determines how much of the player's Morbus Corruption % will increase when eating a non-Vivifica, non-Morbus Stew.")
    public float magicMushroomStewCorruptionDecrease = 0.25F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will decrease when eating a Vivifica Stew.")
    public float vivificaStewCorruptionDecrease = 0.8F;

    @Comment(value = "Determines how much of the player's Morbus Corruption % will increase when eating a Morbus Stew.")
    public float morbusStewCorruptionIncrease = 0.8F;

    @Override
    public String getName() { return MagicFungi.MOD_ID; }

    @Override
    public @Nullable String getModid() { return MagicFungi.MOD_ID; }
}