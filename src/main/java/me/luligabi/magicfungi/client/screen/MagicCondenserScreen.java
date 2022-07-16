package me.luligabi.magicfungi.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import me.luligabi.magicfungi.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MagicCondenserScreen extends HandledScreen<MagicCondenserScreenHandler> {

    public MagicCondenserScreen(MagicCondenserScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void init() {
        backgroundHeight = 208;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 4;
        playerInventoryTitleY = 115;
        ((HandledScreenAccessor) this).setPlayerInventoryTitle(playerInventoryTitle.shallowCopy().formatted(Formatting.WHITE));
        super.init();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);

        if((mouseX >= x+7 && mouseX <= x+40) && (mouseY >= y+100 && mouseY <= y+111)) {
            renderTooltip(matrices,
                List.of(
                    getCountText("tooltip.magicfungi.impetus_essence", handler.getProperty(0), Formatting.DARK_RED, Formatting.RED),
                    getCountText("tooltip.magicfungi.clypeus_essence", handler.getProperty(1), Formatting.DARK_AQUA, Formatting.AQUA),
                    getCountText("tooltip.magicfungi.utilis_essence", handler.getProperty(2), Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
                    getCountText("tooltip.magicfungi.vivifica_essence", handler.getProperty(3), Formatting.DARK_GREEN, Formatting.GREEN),
                    getCountText("tooltip.magicfungi.morbus_essence", handler.getProperty(4), Formatting.DARK_GRAY, Formatting.GRAY)
                ),
                mouseX, mouseY);
        }

        if((mouseX >= x+150 && mouseX <= x+169) && (mouseY >= y+113 && mouseY <= y+118)) {
            renderTooltip(matrices,
                    getCountText(
                            "tooltip.magicfungi.nether_star_fuel", handler.getProperty(6)*10,
                            Formatting.GRAY, Formatting.WHITE
                    ).append("%"),
                    mouseX, mouseY);
        }
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) { // TODO: Render condensing progress
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int fuel = handler.getProperty(6);
        int fuelBarLength = MathHelper.clamp((18 * (fuel*2) + 20 - 1) / 20, 0, 18);
        if (fuelBarLength > 0) {
            this.drawTexture(matrices, x + 151, y + 114, 176, 10, fuelBarLength, 4);
        }

        renderEssenceBar(0, 8, 176, matrices);
        renderEssenceBar(1, 15, 180, matrices);
        renderEssenceBar(2, 22, 184, matrices);
        renderEssenceBar(3, 29, 188, matrices);
        renderEssenceBar(4, 36, 192, matrices);
    }

    private void renderEssenceBar(int propertyIndex, int xOffset, int textureOffset, MatrixStack matrices) {
        int essence = handler.getProperty(propertyIndex);
        if (essence > 0) {
            int essenceBar = essence/2;
            if (essenceBar > 0) {
                this.drawTexture(matrices, x + xOffset, y + 101 + 10 - essenceBar, textureOffset, 10 - essenceBar, 4, essenceBar);
            }
        }
    }

    private MutableText getCountText(String translationKey, int count, Formatting primaryColor, Formatting secondaryColor) {
        return new TranslatableText(translationKey)
                    .formatted(primaryColor)
                .append(new TranslatableText("tooltip.magicfungi.generic_value", count)
                    .formatted(secondaryColor));
    }


    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/magic_condenser.png");

}