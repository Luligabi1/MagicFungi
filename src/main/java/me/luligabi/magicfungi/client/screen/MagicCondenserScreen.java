package me.luligabi.magicfungi.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import me.luligabi.magicfungi.common.util.MagicCondenserUtil;
import me.luligabi.magicfungi.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class MagicCondenserScreen extends HandledScreen<MagicCondenserScreenHandler> implements MagicCondenserUtil {

    public MagicCondenserScreen(MagicCondenserScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void init() {
        backgroundHeight = 208;
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 4;
        playerInventoryTitleY = 115;
        ((HandledScreenAccessor) this).setPlayerInventoryTitle(playerInventoryTitle.copy().formatted(Formatting.WHITE));
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

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int condensingProgress = handler.getProperty(5); // TODO: Improve progress animation
        if (condensingProgress > 0) {
            int condensingBar = MathHelper.clamp((640 / 8) - (condensingProgress / 8), 0, 78);
            if (condensingBar > 0) {
                this.drawTexture(matrices, x + 45, y + 20 + 78 - condensingBar, 176, 92 - condensingBar, 43, condensingBar);
                this.drawTexture(matrices, x + 88, y + 20 + 78 - condensingBar, 176, 170 - condensingBar, 43, condensingBar);
            }
        }

        renderNetherStarBar(
                handler.getProperty(6), x, y, 151, 114,
                176, 10, matrices, this
        );

        renderEssenceBar(handler.getProperty(0), x, y, 8, 176, matrices, this);
        renderEssenceBar(handler.getProperty(1), x, y, 15, 180, matrices, this);
        renderEssenceBar(handler.getProperty(2), x, y, 22, 184, matrices, this);
        renderEssenceBar(handler.getProperty(3), x, y, 29, 188, matrices, this);
        renderEssenceBar(handler.getProperty(4), x, y, 36, 192, matrices, this);
    }

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/magic_condenser.png");

}