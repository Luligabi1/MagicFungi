package me.luligabi.magicfungi.client.tooltip.glyph;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.client.tooltip.MagicItemTooltipComponent;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class GlyphTooltipComponent implements MagicItemTooltipComponent {

    public GlyphTooltipComponent(GlyphTooltipData tooltipData) {
        this.tooltipData = tooltipData;
        Color color = Color.decode("#" + Integer.toHexString(MushroomType.getLightColor(tooltipData.mushroomType).getColorValue()));
        this.r = color.getRed() / 255.0F;
        this.g = color.getGreen() / 255.0F;
        this.b = color.getBlue() / 255.0F;
    }

    protected GlyphTooltipData tooltipData;
    private final float r;
    private final float g;
    private final float b;

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        RenderSystem.setShaderColor(r, g, b, 1.0F);

        drawMushroomType(matrices, x, y, z, tooltipData.mushroomType);
        drawActionType(matrices, x, y, z, tooltipData.actionType, true);
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 37;
    }

}