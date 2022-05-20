package me.luligabi.magicfungi.client.tooltip.relic.utilis;

import me.luligabi.magicfungi.common.item.relic.UtilisPickaxeItem;
import net.minecraft.client.item.TooltipData;

public class UtilisPickaxeTooltipData implements TooltipData {

    public UtilisPickaxeTooltipData(UtilisPickaxeItem.State state) {
        this.state = state;
    }


    protected UtilisPickaxeItem.State state;

}