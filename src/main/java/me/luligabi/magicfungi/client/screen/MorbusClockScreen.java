package me.luligabi.magicfungi.client.screen;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.screenhandler.misc.MorbusClockScreenHandler;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class MorbusClockScreen extends HandledScreen<MorbusClockScreenHandler> {


    public MorbusClockScreen(MorbusClockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        backgroundHeight = 205;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderCheckMark(getScreenHandler().isImminent(), matrices);
        renderCountdown((getScreenHandler().isImminent() ? (int) getScreenHandler().getDaysLeft() : -1), matrices);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {}


    @Override
    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
        super.drawMouseoverTooltip(matrices, x, y);

        renderLimitedTooltipAt(
                List.of(new TranslatableText("message.magicfungi.morbus_clock.imminent")
                            .formatted(Formatting.DARK_RED, Formatting.BOLD)
                        .append((getScreenHandler().isImminent() ? ((TranslatableText) ScreenTexts.YES) : ((TranslatableText) ScreenTexts.NO))
                            .formatted(Formatting.RED))),
                78, 96,
                45, 63,
                x, y, 0,
                matrices
        );
        renderLimitedTooltipAt(
                getCountdownTooltip(),
                77, 97,
                73, 82,
                x, y, 0,
                matrices
        );
        renderLimitedTooltipAt(
                List.of(
                        new TranslatableText("message.magicfungi.morbus_clock.tip.1"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.2"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.3"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.4"),
                        new LiteralText(" "),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.5"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.6"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.7"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.8"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.9"),
                        new LiteralText(" "),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.10"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.11"),
                        new TranslatableText("message.magicfungi.morbus_clock.tip.12")
                ),
                132, 141,
                89, 99,
                x, y, -58,
                matrices
        );
    }

    private void renderCheckMark(boolean isImminent, MatrixStack matrices) {
        if(isImminent) {
            drawTexture(matrices, x + 81, y + 49, textureIndex.get(12).x, textureIndex.get(12).y, 14, 12);
        } else {
            drawTexture(matrices, x + 81, y + 48, textureIndex.get(13).x, textureIndex.get(13).y, 14, 14);
        }
    }

    private void renderCountdown(int countdown, MatrixStack matrices) {
         if(countdown > 999) { // Render as '999' if value can't be shown on 3 digits.
            renderNumberSlot(9, 1, matrices);
            renderNumberSlot(9, 2, matrices);
            renderNumberSlot(9, 3, matrices);
        } else if(countdown == 0) { // Render as '000' (in red) if value equals 0 aka Morbus Spreading is enabled.
            renderNumberSlot(10, 1, matrices);
            renderNumberSlot(10, 2, matrices);
            renderNumberSlot(10, 3, matrices);
        } else if(countdown <= -1) { // Render as '000' (in green) if Morbus Spreading is disabled.
             renderNumberSlot(11, 1, matrices);
             renderNumberSlot(11, 2, matrices);
             renderNumberSlot(11, 3, matrices);
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
        switch(slot) { // Slot order is right to left
            case 3 -> drawTexture(matrices, x + 78, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
            case 2 -> drawTexture(matrices, x + 85, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
            case 1 -> drawTexture(matrices, x + 92, y + 73, textureIndex.get(value).x, textureIndex.get(value).y, 6, 9);
        }
    }

    // Remember Y is counted from top to bottom
    private void renderLimitedTooltipAt(List<Text> text, int leftX, int rightX, int bottomY, int topY, int mouseX, int mouseY, int tooltipOffset, MatrixStack matrices) {
        int inventoryX = this.x;
        int inventoryY = this.y;

        if((mouseX >= inventoryX+leftX && mouseX <= inventoryX+rightX) && (mouseY >= inventoryY+bottomY && mouseY <= inventoryY+topY)) {
            this.renderTooltip(matrices, text, mouseX, mouseY+tooltipOffset);
        }
    }

    private List<Text> getCountdownTooltip() {
        if(getScreenHandler().isImminent()) {
            if(getScreenHandler().getDaysLeft() > 0) {
                return List.of(new TranslatableText("message.magicfungi.morbus_clock.daysLeft", getScreenHandler().getDaysLeft(), getScreenHandler().getStartingDay())
                        .formatted(Formatting.DARK_RED));
            } else {
                return List.of(new TranslatableText("message.magicfungi.morbus_clock.daysLeft.2")
                        .formatted(Formatting.DARK_RED, Formatting.BOLD, Formatting.UNDERLINE));
            }
        } else {
            return List.of(new TranslatableText("message.magicfungi.morbus_clock.daysLeft.3")
                    .formatted(Formatting.DARK_GREEN, Formatting.BOLD));
        }
    }

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
            Coordinate.of(183, 34), // 0 (Green)


            Coordinate.of(176, 0), // Checkmark
            Coordinate.of(191, 0) // X
    );

    private static final Identifier TEXTURE = new Identifier(MagicFungi.MOD_ID, "textures/gui/morbus_clock.png");


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