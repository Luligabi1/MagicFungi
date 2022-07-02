package me.luligabi.magicfungi.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.condenser.MagicCondenserScreenHandler;
import me.luligabi.magicfungi.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

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

        /*if(handler.getFuel() > 0 && handler.getFuelType() <= 4) {
            if((mouseX >= x+59 && mouseX <= x+78) && (mouseY >= y+43 && mouseY <= y+48)) {
                renderTooltip(matrices, new TranslatableText("tooltip.magicfungi.essence_extractor.catalyst_fuel", handler.getFuel())
                        .formatted(CatalystType.values()[handler.getFuelType()].getColor()), mouseX, mouseY);
            }
        }*/
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) { // TODO: Render essences/fuel & condesing progress
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        /*int fuel = handler.getFuel();
        int fuelBarLength = MathHelper.clamp((18 * fuel + 20 - 1) / 20, 0, 18);
        if (fuelBarLength > 0) {
            this.drawTexture(matrices, x + 60, y + 44, 176, getFuelBarTypeYCoordinate(), fuelBarLength, 4);
        }

        int brewTime = handler.getBrewTime();
        if (brewTime > 0) {
            int brewTimeProgress = Math.round(28.0F * (1.0F - brewTime / 400.0F));
            if (brewTimeProgress > 0) {
                this.drawTexture(matrices, x + 97, y + 16, 176, 0, 9, brewTimeProgress);
            }

            brewTimeProgress = BrewingStandScreenAccessor.getBubbleProgress()[brewTime / 2 % 7];
            if (brewTimeProgress > 0) {
                this.drawTexture(matrices, x + 63, y + 14 + 29 - brewTimeProgress, 185, 29 - brewTimeProgress, 12, brewTimeProgress);
            }
        }*/

    }

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/magic_condenser.png");

}