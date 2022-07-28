package me.luligabi.magicfungi.rei.client.widget.magiccondenser;

import com.mojang.blaze3d.systems.RenderSystem;
import me.luligabi.magicfungi.common.util.MagicCondenserUtil;
import me.luligabi.magicfungi.rei.client.displaycategory.MagicCondenserDisplayCategory;
import me.luligabi.magicfungi.rei.common.display.MagicCondenserDisplay;
import me.shedaniel.math.Dimension;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.client.gui.widgets.TooltipContext;
import me.shedaniel.rei.api.client.gui.widgets.WidgetWithBounds;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EssenceCostWidget extends WidgetWithBounds implements MagicCondenserUtil {

    public static EssenceCostWidget create(Point point, MagicCondenserDisplay display, Point startPoint, DrawableHelper helper) {
        return new EssenceCostWidget(point, display, startPoint, helper);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.setShaderTexture(0, REIRuntime.getInstance().isDarkThemeEnabled() ? MagicCondenserDisplayCategory.TEXTURE_DARK : MagicCondenserDisplayCategory.TEXTURE);

        renderEssenceBarOnWidget(display.getImpetusEssenceCost(), startPoint.x, startPoint.y, 30, 0, matrices, helper);
        renderEssenceBarOnWidget(display.getClypeusEssenceCost(), startPoint.x, startPoint.y, 23, 4, matrices, helper);
        renderEssenceBarOnWidget(display.getUtilisEssenceCost(), startPoint.x, startPoint.y, 16, 8, matrices, helper);
        renderEssenceBarOnWidget(display.getVivificaEssenceCost(), startPoint.x, startPoint.y, 9, 12, matrices, helper);
        renderEssenceBarOnWidget(display.getMorbusEssenceCost(), startPoint.x, startPoint.y, 2, 16, matrices, helper);

        final Point mousePoint = new Point(mouseX, mouseY);
        if(containsMouse(mousePoint)) getTooltip(TooltipContext.of(mousePoint)).queue();
    }

    @Override
    public @NotNull Tooltip getTooltip(TooltipContext context) {
        Tooltip tooltip = super.getTooltip(context);
        if(tooltip == null) tooltip = Tooltip.create(context.getPoint(), List.of(
                getCountText("tooltip.magicfungi.impetus_essence", display.getImpetusEssenceCost(), Formatting.DARK_RED, Formatting.RED),
                getCountText("tooltip.magicfungi.clypeus_essence", display.getClypeusEssenceCost(), Formatting.DARK_AQUA, Formatting.AQUA),
                getCountText("tooltip.magicfungi.utilis_essence", display.getUtilisEssenceCost(), Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE),
                getCountText("tooltip.magicfungi.vivifica_essence", display.getVivificaEssenceCost(), Formatting.DARK_GREEN, Formatting.GREEN),
                getCountText("tooltip.magicfungi.morbus_essence", display.getMorbusEssenceCost(), Formatting.DARK_GRAY, Formatting.GRAY)
        ));
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


    private EssenceCostWidget(Point point, MagicCondenserDisplay display, Point startPoint, DrawableHelper helper) {
        this.bounds = new Rectangle(Objects.requireNonNull(point), new Dimension(34, 12));
        this.display = display;
        this.startPoint = startPoint;
        this.helper = helper;
    }

}