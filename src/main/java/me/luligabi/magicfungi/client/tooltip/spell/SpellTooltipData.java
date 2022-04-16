package me.luligabi.magicfungi.client.tooltip.spell;

import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.client.item.TooltipData;

public class SpellTooltipData implements TooltipData {

    public SpellTooltipData(MushroomType mushroomType, int cooldown, ActionType actionType) {
        this.mushroomType = mushroomType;
        this.cooldown = cooldown;
        this.actionType = actionType;
    }

    protected MushroomType mushroomType;
    protected int cooldown;
    protected ActionType actionType;

}