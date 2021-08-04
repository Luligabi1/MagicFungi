package me.luligabi.magicfungi.common.util;

import net.minecraft.text.TranslatableText;

public enum ActionType {

    PLAYER(new TranslatableText("actionType.magicfungi.player")),
    BLOCK(new TranslatableText("actionType.magicfungi.block")),
    WORLD(new TranslatableText("actionType.magicfungi.world")), // Used if action might affect both entities and blocks.
    ENTITY(new TranslatableText("actionType.magicfungi.entity")),
    UNKNOWN(new TranslatableText("actionType.magicfungi.unknown"));


    private final TranslatableText translatableText;

    ActionType(TranslatableText translatableText) {
        this.translatableText = translatableText;
    }

    public TranslatableText getTranslatableText() { return translatableText; }

}