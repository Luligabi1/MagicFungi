package me.luligabi.magicfungi.common.util;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public interface MagicCondenserUtil {

    default void renderEssenceBar(int essence, int x, int y, int xOffset, int textureOffset, MatrixStack matrices, DrawableHelper helper) {
        if (essence > 0) {
            int essenceBarHeight = essence != 1 ? essence / 2 : 1;
            helper.drawTexture(matrices, x + xOffset, y + 101 + 10 - essenceBarHeight, textureOffset, 10 - essenceBarHeight, 4, essenceBarHeight);
        }
    }

    default void renderEssenceBarOnWidget(int essence, int x, int y, int xOffset, int textureOffset, MatrixStack matrices, DrawableHelper helper) {
        helper.drawTexture(matrices, x - xOffset, y + 15, 20, 0, 6, 12);
        int essenceBarHeight = essence != 1 ? essence/2 : 1;
        if(essenceBarHeight > 0) {
            helper.drawTexture(matrices, (x - xOffset) + 1, y + 16 + 10 - essenceBarHeight, textureOffset, 10 - essenceBarHeight, 4, essenceBarHeight);
        }
    }

    default void renderNetherStarBar(int netherStarPower, int x, int y, int xOffset, int yOffset, int xTextureOffset, int yTextureOffset, MatrixStack matrices, DrawableHelper helper) {
        int fuelBarLength = MathHelper.clamp((18 * (netherStarPower*2) + 20 - 1) / 20, 0, 18);
        if (fuelBarLength > 0) {
            helper.drawTexture(matrices, x + xOffset, y + yOffset, xTextureOffset, yTextureOffset, fuelBarLength, 4);
        }
    }


    default MutableText getCountText(String translationKey, int count, Formatting primaryColor, Formatting secondaryColor) {
        return new TranslatableText(translationKey)
                    .formatted(primaryColor)
                .append(new TranslatableText("tooltip.magicfungi.generic_value", count)
                    .formatted(secondaryColor));
    }

}