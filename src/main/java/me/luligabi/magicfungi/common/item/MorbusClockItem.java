package me.luligabi.magicfungi.common.item;

import me.luligabi.magicfungi.common.misc.gamerule.GameRuleRegistry;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MorbusClockItem extends Item {

    public MorbusClockItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            TranslatableText isEnabled = world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING) ? new TranslatableText("message.magicfungi.yes") : new TranslatableText("message.magicfungi.no");
            user.sendMessage(new TranslatableText("message.magicfungi.isMorbusEnabled").formatted(Formatting.DARK_GRAY, Formatting.BOLD)
                    .append(isEnabled.formatted(Formatting.GRAY)), false);

            if (world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING)) {
                long daysLeft = (world.getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY) - Util.getCurrentInGameDay(world));

                TranslatableText daysLeftText = daysLeft > 0 ? new TranslatableText("message.magicfungi.daysLeft.2", daysLeft) : new TranslatableText("message.magicfungi.daysLeft.3");
                user.sendMessage(new TranslatableText("message.magicfungi.daysLeft.1").formatted(Formatting.DARK_GRAY, Formatting.BOLD)
                        .append(daysLeftText.formatted(daysLeft > 0 ? Formatting.GRAY : Formatting.DARK_RED, Formatting.BOLD)), false);
            }

            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.morbus_clock").formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
    }
}