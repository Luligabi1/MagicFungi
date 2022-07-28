package me.luligabi.magicfungi.client.tooltip.spell;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.client.tooltip.MagicItemTooltipComponent;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;

public class SpellTooltipComponent implements MagicItemTooltipComponent {

    public SpellTooltipComponent(SpellTooltipData tooltipData) {
        this.tooltipData = tooltipData;
        Color color = Color.decode("#" + Integer.toHexString(MushroomType.getLightColor(tooltipData.mushroomType).getColorValue()));
        this.r = color.getRed() / 255.0F;
        this.g = color.getGreen() / 255.0F;
        this.b = color.getBlue() / 255.0F;
    }

    protected SpellTooltipData tooltipData;
    private final float r;
    private final float g;
    private final float b;

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        RenderSystem.setShaderColor(r, g, b, 1.0F);

        drawMushroomType(matrices, x, y, z, tooltipData.mushroomType);
        drawActionType(matrices, x, y, z, tooltipData.actionType, false);
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        Text cooldownText = Text.of(tooltipData.cooldown/20 + "s");
        textRenderer.draw(cooldownText.copy().formatted(MushroomType.getLightColor(tooltipData.mushroomType)), x + (32 - (3*cooldownText.getString().length())), y + 2.5F, -1, true, matrix, vertexConsumers, false, 0, 0xF000F0);
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 61;
    }

}