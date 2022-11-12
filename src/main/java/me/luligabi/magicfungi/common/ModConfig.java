package me.luligabi.magicfungi.common;


public class ModConfig {

    // Spells
    public int igneiSpellCooldown = 12;

    public int scintillamSpellCooldown = 22;


    public int cadereSpellCooldown = 35;
    public int cadereSpellEffectTime = 12;


    public int glaciesSpellCooldown = 40;
    public int glaciesSpellEffectTime = 10;


    public int tractabileSpellCooldown = 3;

    public int cibusSpellCooldown = 160;
    public int cibusSpellHungerModifier = 9;
    public float cibusSpellSaturationModifier = 0.8F;


    public int fertilisSpellCooldown = 170;


    public int parasitusGlyphEffectTime = 7;

    // Fungic Relics
    public int relicDurability = 3046;
    public float relicMiningSpeed = 10.5F;

    public float impetusSwordAttackDamage = 10.0F;
    public int impetusSwordMaxCharge = 96;

    public int clypeusShieldMaxCharge = 72;

    public int vivificaElixirMaxCharge = 72;

    public float morbusScytheAttackDamage = 8.5F;
    public int morbusScytheEffectDuration = 8;
    public int morbusScytheEffectStrength = 0;
    public int morbusScytheMaxCharge = 100;

    public int magicalFungiArmorDurabilityMultiplier = 45;
    public float magicalFungiArmorToughness = 3.5F;
    public float magicalFungiKnockBackResistance = 0.15F;

    // Worldgen
    public boolean canGenerateImpetusMushrooms = true;
    public int impetusRegularSpawnRate = 12;
    public int impetusBiomeEnhancedSpawnRate = 28;

    public boolean canGenerateClypeusMushrooms = true;
    public int clypeusRegularSpawnRate = 12;
    public int clypeusBiomeEnhancedSpawnRate = 28;

    public boolean canGenerateUtilisMushrooms = true;
    public int utilisRegularSpawnRate = 12;
    public int utilisBiomeEnhancedSpawnRate = 28;

    public boolean canGenerateVivificaMushrooms = true;
    public int vivificaRegularSpawnRate = 12;
    public int vivificaBiomeEnhancedSpawnRate = 28;

    public boolean canGenerateMorbusMushrooms = true;

    public int hostBiomeSpawnRate = 7;
    public boolean canGenerateWitherRoseHostBiome = true;
    public int hostBiomeWitherRoseSpawnRate = 12;


    // Morbus Spreading
    public boolean doMorbusSpread = false;
    public int morbusSpreadStartingDay = 100;
    public boolean canUseMorbusCorruptionItems = false;


    // Misc.
    public boolean addModContentToChests = true;
    public boolean addModContentToTrades = true;
}