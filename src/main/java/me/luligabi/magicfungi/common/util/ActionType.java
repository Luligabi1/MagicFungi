package me.luligabi.magicfungi.common.util;


import net.minecraft.text.Text;

public enum ActionType {

    BLOCK(Text.translatable("actionType.magicfungi.block")),
    ENTITY(Text.translatable("actionType.magicfungi.entity")),
    PLAYER(Text.translatable("actionType.magicfungi.player")),
    WORLD(Text.translatable("actionType.magicfungi.world")), // Used if action might affect both entities and blocks.
    UNKNOWN(Text.translatable("actionType.magicfungi.unknown"));


    private final Text translatableText;

    ActionType(Text translatableText) {
        this.translatableText = translatableText;
    }

    public Text getTranslatableText() { return translatableText; }

}