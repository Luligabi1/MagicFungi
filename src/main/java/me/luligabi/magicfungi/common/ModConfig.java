package me.luligabi.magicfungi.common;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

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


    @Comment(value = "Spawnrate for generation of the Host Biome. Increase if using any biome overhaul-styled mods, as otherwise the biome will be exceedingly rare.")
    public double hostBiomeSpawnRate = 0.2;

    @Override
    public String getName() {
        return "magicfungi";
    }
}