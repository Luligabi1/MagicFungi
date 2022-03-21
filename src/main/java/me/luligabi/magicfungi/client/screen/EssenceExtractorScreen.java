package me.luligabi.magicfungi.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.essence.EssenceExtractorScreenHandler;
import me.luligabi.magicfungi.common.util.CatalystType;
import me.luligabi.magicfungi.mixin.BrewingStandScreenAccessor;
import me.luligabi.magicfungi.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class EssenceExtractorScreen extends HandledScreen<EssenceExtractorScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/essence_extractor.png");

    public EssenceExtractorScreen(EssenceExtractorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        ((HandledScreenAccessor) this).setPlayerInventoryTitle(playerInventoryTitle.shallowCopy().formatted(Formatting.WHITE));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);

        if(handler.getFuelType() >= 0 && handler.getFuelType() <= 4) {
            if((mouseX >= x+58 && mouseX <= x+78) && (mouseY >= y+42 && mouseY <= y+48)) {
                renderTooltip(matrices, new TranslatableText("tooltip.magicfungi.essence_extractor.catalyst_fuel", handler.getFuel())
                        .formatted(CatalystType.values()[handler.getFuelType()].getColor()), mouseX, mouseY);
            }
        }
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (width - backgroundWidth) / 2;
        int j = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, backgroundWidth, backgroundHeight);
        int k = handler.getFuel();
        int l = MathHelper.clamp((18 * k + 20 - 1) / 20, 0, 18);
        if (l > 0) {
            this.drawTexture(matrices, i + 60, j + 44, 176, getFuelBarTypeYCoordinate(), l, 4);
        }

        int m = handler.getBrewTime();
        if (m > 0) {
            int n = Math.round(28.0F * (1.0F - m / 400.0F));
            if (n > 0) {
                this.drawTexture(matrices, i + 97, j + 16, 176, 0, 9, n);
            }

            n = BrewingStandScreenAccessor.getBubbleProgress()[m / 2 % 7];
            if (n > 0) {
                this.drawTexture(matrices, i + 63, j + 14 + 29 - n, 185, 29 - n, 12, n);
            }
        }

    }

    private int getFuelBarTypeYCoordinate() {
        return switch(handler.getFuelType()) {
            case 0 -> 29;
            case 1 -> 33;
            case 2 -> 37;
            case 3 -> 41;
            case 4 -> 45;
            default -> 128;
        };
    }

}