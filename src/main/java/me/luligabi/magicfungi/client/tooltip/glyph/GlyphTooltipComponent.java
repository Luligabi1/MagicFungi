package me.luligabi.magicfungi.client.tooltip.glyph;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.awt.*;

public class GlyphTooltipComponent implements TooltipComponent {

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

        RenderSystem.setShaderTexture(0, getMushroomTypeTexture());
        DrawableHelper.drawTexture(matrices, x, y-3, z, 0F, 0F, 18, 18, 18, 18);

        RenderSystem.setShaderTexture(0, getActionTypeTexture());
        DrawableHelper.drawTexture(matrices, x + 22, y-3, z, 0F, 0F, 18, 18, 18, 18);
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 37;
    }

    @Override
    public int getHeight() {
        return 17;
    }


    private Identifier getMushroomTypeTexture() {
        return switch(tooltipData.mushroomType) {
            case IMPETUS -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/impetus.png");
            case CLYPEUS ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/clypeus.png");
            case UTILIS ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/utilis.png");
            case VIVIFICA ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/vivifica.png");
            case MORBUS ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/morbus.png");
            case INCOGNITA ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/mushroom/incognita.png");
        };
    }

    private Identifier getActionTypeTexture() {
        return switch(tooltipData.actionType) {
            case BLOCK ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/block.png");
            case ENTITY ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/entity.png");
            case PLAYER -> new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/player.png");
            case WORLD ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/world.png");
            case UNKNOWN ->  new Identifier(MagicFungi.MOD_ID, "textures/gui/tooltip/action/unknown.png");
        };
    }

}