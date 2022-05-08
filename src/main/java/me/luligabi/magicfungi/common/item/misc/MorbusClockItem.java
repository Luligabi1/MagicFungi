package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.screenhandler.misc.MorbusClockScreenHandler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.LiteralText;
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
            user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                    new MorbusClockScreenHandler(syncId, inventory), new LiteralText("")
            ));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.morbus_clock").formatted(Formatting.GRAY, Formatting.ITALIC));
    }

    /*TranslatableText isEnabled = world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING) ? new TranslatableText("message.magicfungi.yes") : new TranslatableText("message.magicfungi.no");
            user.sendMessage(new TranslatableText("message.magicfungi.isMorbusEnabled").formatted(Formatting.DARK_GRAY, Formatting.BOLD)
                    .append(isEnabled.formatted(Formatting.GRAY)), false);

            if (world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING)) {
                long daysLeft = (world.getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY) - WorldUtil.getCurrentInGameDay(world));

                TranslatableText daysLeftText = daysLeft > 0 ?
                        new TranslatableText("message.magicfungi.daysLeft.2", daysLeft, world.getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY)) :
                        new TranslatableText("message.magicfungi.daysLeft.3");
                user.sendMessage(new TranslatableText("message.magicfungi.daysLeft.1").formatted(Formatting.DARK_GRAY, Formatting.BOLD)
                        .append(daysLeftText.formatted(daysLeft > 0 ? Formatting.GRAY : Formatting.DARK_RED, Formatting.BOLD)), false);
            }*/
}