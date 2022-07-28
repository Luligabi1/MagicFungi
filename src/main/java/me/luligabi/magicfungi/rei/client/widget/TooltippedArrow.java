package me.luligabi.magicfungi.rei.client.widget;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Arrow;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.client.gui.widgets.TooltipContext;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TooltippedArrow extends Arrow {

    private final Arrow arrow;
    private  final List<Text> tooltipText;

    public TooltippedArrow(Point currentPoint, List<Text> tooltipConsumer) {
        this.arrow = Widgets.createArrow(currentPoint);
        this.tooltipText = tooltipConsumer;
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
        if(containsMouse(mousePoint)) getTooltip(TooltipContext.of(mousePoint)).queue();
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
    public @NotNull Tooltip getTooltip(TooltipContext context) {
        Tooltip tooltip = super.getTooltip(context);
        if (tooltip == null) tooltip = Tooltip.create(context.getPoint(), tooltipText);
        return tooltip;
    }

}