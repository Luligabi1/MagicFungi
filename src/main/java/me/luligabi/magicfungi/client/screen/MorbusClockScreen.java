package me.luligabi.magicfungi.client.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.misc.MorbusClockScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.List;

public class MorbusClockScreen extends HandledScreen<MorbusClockScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/morbus_clock.png");

    public MorbusClockScreen(MorbusClockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        backgroundHeight = 205;
        x = width / 2 - backgroundWidth / 2;
        y = height / 2 - backgroundHeight / 2;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderCountdown(0, matrices);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {}



    private void renderCountdown(int countdown, MatrixStack matrices) {
        if(countdown > 999) { // Render as '999' if value can't be shown on 3 digits.
            renderNumberSlot(9, 1, matrices);
            renderNumberSlot(9, 2, matrices);
            renderNumberSlot(9, 3, matrices);
        } else if(countdown <= 0) { // Render as '000' (in red) if value equals 0 aka Morbus Spreading is enabled.
            renderNumberSlot(10, 1, matrices);
            renderNumberSlot(10, 2, matrices);
            renderNumberSlot(10, 3, matrices);
        } else { // If no special conditions are met, render actual countdown value.
            int length = (int) Math.ceil(Math.log10(countdown + 1));
            if(length >= 3) {
                renderNumberSlot((countdown / 100) % 10, 3, matrices);
            }
            if(length >= 2) {
                if(length == 2) {
                    renderNumberSlot(0, 3, matrices);
                }
                renderNumberSlot((countdown / 10) % 10, 2, matrices);
            }
            if(length == 1) {
                renderNumberSlot(0, 3, matrices);
                renderNumberSlot(0, 2, matrices);
            }
            renderNumberSlot(countdown % 10, 1, matrices);
        }
    }

    private void renderNumberSlot(int value, int slot, MatrixStack matrices) {
        int x = this.x;
        int y = this.y;

        switch(slot) { // Slot order is right to left
            case 3 -> drawTexture(matrices, x + 78, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
            case 2 -> drawTexture(matrices, x + 85, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
            case 1 -> drawTexture(matrices, x + 92, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
        }
    }

    private TranslatableText isEnabled;
    private TranslatableText b;


    private final List<Coordinate> textureIndex = ImmutableList.of(
            Coordinate.of(176, 14), // 0
            Coordinate.of(183, 14), // 1
            Coordinate.of(190, 14), // 2
            Coordinate.of(197, 14), // 3
            Coordinate.of(204, 14), // 4
            Coordinate.of(176, 24), // 5
            Coordinate.of(183, 24), // 6
            Coordinate.of(190, 24), // 7
            Coordinate.of(197, 24), // 8
            Coordinate.of(204, 24), // 9

            Coordinate.of(176, 34), // 0 (Red)


            Coordinate.of(176, 0), // Checkmark
            Coordinate.of(191, 0) // X
    );

    public static class Coordinate {

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Coordinate of(int x, int y) {
            return new Coordinate(x, y);
        }


        protected int x;
        protected int y;

    }
}