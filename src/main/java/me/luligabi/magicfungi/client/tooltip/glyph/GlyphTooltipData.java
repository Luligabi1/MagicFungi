package me.luligabi.magicfungi.client.tooltip.glyph;

import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.item.TooltipData;

public class GlyphTooltipData implements TooltipData {

    public GlyphTooltipData(MushroomType mushroomType, ActionType actionType) {
        this.mushroomType = mushroomType;
        this.actionType = actionType;
    }

    protected MushroomType mushroomType;
    protected ActionType actionType;

}