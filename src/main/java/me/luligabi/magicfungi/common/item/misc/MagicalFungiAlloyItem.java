package me.luligabi.magicfungi.common.item.misc;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicalFungiAlloyItem extends Item {

    public MagicalFungiAlloyItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.magicfungi.alloy_info.1")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        tooltip.add(Text.translatable("tooltip.magicfungi.alloy_info.2")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.literal("...")
                    .formatted(Formatting.GRAY, Formatting.ITALIC));
            tooltip.add(Text.translatable("tooltip.magicfungi.alloy_info.3")
                    .formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}