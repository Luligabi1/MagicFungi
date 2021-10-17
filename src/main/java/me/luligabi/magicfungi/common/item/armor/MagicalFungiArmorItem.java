package me.luligabi.magicfungi.common.item.armor;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicalFungiArmorItem extends ArmorItem {

    public MagicalFungiArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.magical_fungi_armor.1")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip.magicfungi.magical_fungi_armor.2")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        if(Screen.hasShiftDown()) {
            tooltip.add(new LiteralText(" "));
            tooltip.add(new TranslatableText("tooltip.magicfungi.magical_fungi_armor.3")
                    .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
            tooltip.add(new TranslatableText("tooltip.magicfungi.magical_fungi_armor.4")
                    .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        } else {
            tooltip.add(new TranslatableText("tooltip.magicfungi.extended_info")
                    .formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}