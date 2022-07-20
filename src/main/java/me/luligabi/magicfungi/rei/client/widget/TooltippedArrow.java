package me.luligabi.magicfungi.rei.client.widget;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Arrow;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class TooltippedArrow extends Arrow {

    private final Arrow arrow;
    private  final Consumer<Tooltip> tooltipConsumer;

    public TooltippedArrow(Point currentPoint, Consumer<Tooltip> tooltipConsumer) {
        this.arrow = Widgets.createArrow(currentPoint);
        this.tooltipConsumer = tooltipConsumer;
    }


    @Override
    public Rectangle getBounds() {
        return arrow.getBounds();
    }

    @Override
    public List<? extends Element> children() {
        return arrow.children();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        arrow.render(matrices, mouseX, mouseY, delta);
        final Point mousePoint = new Point(mouseX, mouseY);
        if(containsMouse(mousePoint)) getTooltip(mousePoint).queue();
    }

    @Override
    public double getAnimationDuration() {
        return arrow.getAnimationDuration();
    }

    @Override
    public void setAnimationDuration(double animationDurationMS) {
        arrow.setAnimationDuration(animationDurationMS);
    }

    @Override
    public @NotNull Tooltip getTooltip(Point mouse) {
        Tooltip tooltip = super.getTooltip(mouse);
        if (tooltip == null) tooltip = Tooltip.create(mouse);
        tooltipConsumer.accept(tooltip);
        return tooltip;
    }

}