package me.luligabi.magicfungi.common.util;

import me.luligabi.magicfungi.common.misc.ParticleRegistry;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

public enum MushroomType implements StringIdentifiable {

    IMPETUS(Text.translatable("mushroomType.magicfungi.impetus"), Text.translatable("mushroomType.magicfungi.impetus.stats"), "impetus"),
    CLYPEUS(Text.translatable("mushroomType.magicfungi.clypeus"), Text.translatable("mushroomType.magicfungi.clypeus.stats"), "clypeus"),
    UTILIS(Text.translatable("mushroomType.magicfungi.utilis"), Text.translatable("mushroomType.magicfungi.utilis.stats"), "utilis"),
    VIVIFICA(Text.translatable("mushroomType.magicfungi.vivifica"), Text.translatable("mushroomType.magicfungi.vivifica.stats"), "vivifica"),
    MORBUS(Text.translatable("mushroomType.magicfungi.morbus"), Text.translatable("mushroomType.magicfungi.morbus.stats"), "morbus"),

    // Unknown
    INCOGNITA(Text.translatable("mushroomType.magicfungi.incognita"), Text.translatable("mushroomType.magicfungi.incognita.stats"), "incognita");

    private final Text fancyName;
    private final Text statsName;
    private final String id;

    MushroomType(Text fancyName, Text statsName, String id) {
        this.fancyName = fancyName;
        this.statsName = statsName;
        this.id = id;
    }

    public Text getFancyName() {
        return fancyName;
    }

    public Text getStatsName() {
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