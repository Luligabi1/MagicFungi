package me.luligabi.magicfungi.common.block.misc;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicalFungiAlloyBlock extends Block {

    public MagicalFungiAlloyBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.alloy_info.1")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip.magicfungi.alloy_info.2")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        if(Screen.hasShiftDown()) {
            tooltip.add(new LiteralText("...")
                    .formatted(Formatting.GRAY, Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip.magicfungi.alloy_info.3")
                    .formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}