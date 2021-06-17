package me.luligabi.magicfungi.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GlyphCarvingScreen extends HandledScreen<ScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/glyph_bench.png");
    //TODO: Change texture

    public GlyphCarvingScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        backgroundHeight = 205;
        x = width / 2 -backgroundWidth / 2;
        y = height / 2 - backgroundHeight / 2;
        playerInventoryTitleY = 112;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}