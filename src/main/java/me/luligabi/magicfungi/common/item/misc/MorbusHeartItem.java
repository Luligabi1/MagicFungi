package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.misc.gamerule.GameRuleRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MorbusHeartItem extends Item {

    public MorbusHeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) { // TODO: Add config to disable
        if(!world.isClient()) {
            if(!world.getGameRules().getBoolean(GameRuleRegistry.DO_MORBUS_SPREADING)) {
                world.getGameRules().get(GameRuleRegistry.DO_MORBUS_SPREADING).set(true, world.getServer());
                user.getStackInHand(hand).decrement(1);
                user.getEntityWorld().playSound(null,
                        user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_MOSS_PLACE, SoundCategory.NEUTRAL, 1F, 1F);
                return TypedActionResult.consume(user.getStackInHand(hand)); // TODO: Add message when using.
            } else {
                user.sendMessage(new TranslatableText("message.magicfungi.fate_design").formatted(Formatting.GRAY, Formatting.ITALIC), false);
                return TypedActionResult.pass(user.getStackInHand(hand));
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

}