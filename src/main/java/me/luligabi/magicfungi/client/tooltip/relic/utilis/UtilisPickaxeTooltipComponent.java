package me.luligabi.magicfungi.client.tooltip.relic.utilis;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;

import java.awt.*;

public class UtilisPickaxeTooltipComponent implements TooltipComponent {

    public UtilisPickaxeTooltipComponent(UtilisPickaxeTooltipData tooltipData) {
        this.tooltipData = tooltipData;
        Color color = Color.decode("#" + Integer.toHexString(Formatting.LIGHT_PURPLE.getColorValue()));
        this.r = color.getRed() / 255.0F;
        this.g = color.getGreen() / 255.0F;
        this.b = color.getBlue() / 255.0F;
    }

    protected UtilisPickaxeTooltipData tooltipData;
    private final float r;
    private final float g;
    private final float b;


    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        RenderSystem.setShaderColor(r, g, b, 1.0F);

        RenderSystem.setShaderTexture(0, getStateTexture());
        DrawableHelper.drawTexture(matrices, x, y-3, z, 0F, 0F, 18, 18, 18, 18);
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        TranslatableText cooldownText = tooltipData.state.getTranslatableText();
        textRenderer.draw(cooldownText.formatted(Formatting.LIGHT_PURPLE), x + 20, y + 2.5F, -1, true, matrix, vertexConsumers, false, 0, 0xF000F0);
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 32;
    }

    @Override
    public int getHeight() {
        return 17;
    }

    private Identifier getStateTexture() {
        return switch(tooltipData.state) {
            case FUNCTIONIS -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/relic/utilis/functionis.png");
            case FORTUNAE -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/relic/fortunae.png");
            case MOLLIS -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/relic/utilis/mollis.png");
            case LASER -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/relic/utilis/laser.png");
            case DYSFUNCTIONIS -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/relic/dysfunctionis.png");
        };
    }

}