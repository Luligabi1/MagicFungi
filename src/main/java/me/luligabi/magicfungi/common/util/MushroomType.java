package me.luligabi.magicfungi.common.util;

import net.minecraft.text.TranslatableText;

public enum MushroomType {

    IMPETUS(new TranslatableText("name.magicfungi.impetus.fancy"), new TranslatableText("name.magicfungi.impetus")),
    CLYPEUS(new TranslatableText("name.magicfungi.clypeus.fancy"), new TranslatableText("name.magicfungi.clypeus")),
    UTILIS(new TranslatableText("name.magicfungi.utilis.fancy"), new TranslatableText("name.magicfungi.utilis")),
    VIVIFICA(new TranslatableText("name.magicfungi.vivifica.fancy"), new TranslatableText("name.magicfungi.vivifica")),
    MORBUS(new TranslatableText("name.magicfungi.morbus.fancy"), new TranslatableText("name.magicfungi.morbus")),
    UNKNOWN(null, null);

    protected TranslatableText fancyName;
    protected TranslatableText comprehensibleName;

    MushroomType(TranslatableText fancy, TranslatableText comprehensible) {
        fancyName = fancy;
        comprehensibleName = comprehensible;
    }

    public TranslatableText getFancyName() {
        return fancyName;
    }

    public TranslatableText getComprehensibleName() {
        return comprehensibleName;
    }
}