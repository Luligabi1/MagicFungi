package me.luligabi.magicfungi.common.util;

import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

public enum MushroomType implements StringIdentifiable {

    IMPETUS(new TranslatableText("mushroomType.magicfungi.impetus"), new TranslatableText("mushroomType.magicfungi.impetus.stats"), "impetus"),
    CLYPEUS(new TranslatableText("mushroomType.magicfungi.clypeus"), new TranslatableText("mushroomType.magicfungi.clypeus.stats"), "clypeus"),
    UTILIS(new TranslatableText("mushroomType.magicfungi.utilis"), new TranslatableText("mushroomType.magicfungi.utilis.stats"), "utilis"),
    VIVIFICA(new TranslatableText("mushroomType.magicfungi.vivifica"), new TranslatableText("mushroomType.magicfungi.vivifica.stats"), "vivifica"),
    MORBUS(new TranslatableText("mushroomType.magicfungi.morbus"), new TranslatableText("mushroomType.magicfungi.morbus.stats"), "morbus"),

    // Unknown
    INCOGNITA(new TranslatableText("mushroomType.magicfungi.incognita"), new TranslatableText("mushroomType.magicfungi.incognita.stats"), "incognita");

    private final TranslatableText fancyName;
    private final TranslatableText statsName;
    private final String id;

    MushroomType(TranslatableText fancyName, TranslatableText statsName, String id) {
        this.fancyName = fancyName;
        this.statsName = statsName;
        this.id = id;
    }

    public TranslatableText getFancyName() {
        return fancyName;
    }

    public TranslatableText getStatsName() {
        return statsName;
    }

    public String getId() { return id; }

    public static Formatting getLightColor(MushroomType mushroomType) {
        return switch (mushroomType) {
            case IMPETUS -> Formatting.RED;
            case CLYPEUS -> Formatting.AQUA;
            case UTILIS -> Formatting.LIGHT_PURPLE;
            case VIVIFICA -> Formatting.GREEN;
            case MORBUS -> Formatting.GRAY;
            default -> Formatting.WHITE;
        };
    }

    public static Formatting getDarkColor(MushroomType mushroomType) {
        return switch (mushroomType) {
            case IMPETUS -> Formatting.DARK_RED;
            case CLYPEUS -> Formatting.DARK_AQUA;
            case UTILIS -> Formatting.DARK_PURPLE;
            case VIVIFICA -> Formatting.DARK_GREEN;
            case MORBUS -> Formatting.DARK_GRAY;
            default -> Formatting.WHITE;
        };
    }

    public static DefaultParticleType getParticleEffect(MushroomType mushroomType) {
        return switch (mushroomType) {
            case IMPETUS -> ParticleRegistry.IMPETUS_FLAME;
            case CLYPEUS -> ParticleRegistry.CLYPEUS_FLAME;
            case UTILIS -> ParticleRegistry.UTILIS_FLAME;
            case VIVIFICA -> ParticleRegistry.VIVIFICA_FLAME;
            case MORBUS -> ParticleRegistry.MORBUS_FLAME;
            default -> throw new IllegalArgumentException("No particle effect available for this MushroomType!");
        };
    }


    @Override
    public String asString() {
        return getId();
    }
}