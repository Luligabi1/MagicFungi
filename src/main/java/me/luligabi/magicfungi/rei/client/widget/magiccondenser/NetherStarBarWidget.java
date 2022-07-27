package me.luligabi.magicfungi.rei.client.widget.magiccondenser;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.util.MagicCondenserUtil;
import me.luligabi.magicfungi.rei.client.displaycategory.MagicCondenserDisplayCategory;
import me.luligabi.magicfungi.rei.common.display.MagicCondenserDisplay;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class NetherStarBarWidget extends WidgetWithBounds implements MagicCondenserUtil {

    public static NetherStarBarWidget create(Point point, MagicCondenserDisplay display, Point startPoint, DrawableHelper helper) {
        return new NetherStarBarWidget(point, display, startPoint, helper);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, MagicCondenserDisplayCategory.TEXTURE);

        helper.drawTexture(matrices, startPoint.x + 92, startPoint.y + 21, 0, 14, 20, 6);
        renderNetherStarBar(
                display.getNetherStarFuelCost(), startPoint.x, startPoint.y, 93, 22,
                0,10, matrices, helper
        );

        final Point mousePoint = new Point(mouseX, mouseY);
        if(containsMouse(mousePoint)) getTooltip(mousePoint).queue();
    }

    @Override
    public @NotNull Tooltip getTooltip(Point mouse) {
        Tooltip tooltip = super.getTooltip(mouse);
        if(tooltip == null) tooltip = Tooltip.create(mouse);
        Consumer<Tooltip> tooltipConsumer = consumer -> {
            consumer.add(getCountText(
                    "tooltip.magicfungi.nether_star_fuel", display.getNetherStarFuelCost()*10,
                    Formatting.GRAY, Formatting.WHITE
            ).append("%"));
        };
        tooltipConsumer.accept(tooltip);
        return tooltip;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public List<? extends Element> children() {
        return Collections.emptyList();
    }

    private final MagicCondenserDisplay display;
    private final DrawableHelper helper;
    private final Point startPoint;
    private final Rectangle bounds;


    private NetherStarBarWidget(Point point, MagicCondenserDisplay display, Point startPoint, DrawableHelper helper) {
        this.bounds = new Rectangle(Objects.requireNonNull(point), new Dimension(20, 6));
        this.display = display;
        this.startPoint = startPoint;
        this.helper = helper;
    }

}