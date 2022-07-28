package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.misc.GameRuleRegistry;
import me.luligabi.magicfungi.common.screenhandler.misc.MorbusClockScreenHandler;
import me.luligabi.magicfungi.common.util.WorldUtil;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MorbusClockItem extends Item {

    public MorbusClockItem(Settings settings) {
        super(settings);
        factory = new ExtendedScreenHandlerFactory() {

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return new MorbusClockScreenHandler(syncId, inv);
            }

            @Override
            public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                buf.writeBoolean(player.getWorld().getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING));
                buf.writeLong((player.getWorld().getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY) - WorldUtil.getCurrentInGameDay(player.getWorld())));
                buf.writeInt(player.getWorld().getGameRules().getInt(GameRuleRegistry.MORBUS_SPREADING_DAY));
            }

            @Override
            public Text getDisplayName() {
                return Text.empty();
            }
        };
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            user.openHandledScreen(factory);
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.magicfungi.morbus_clock").formatted(Formatting.GRAY, Formatting.ITALIC));
    }


    private final ExtendedScreenHandlerFactory factory;

}