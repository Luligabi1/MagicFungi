package me.luligabi.magicfungi.common.item.misc;

import me.luligabi.magicfungi.common.misc.component.MagicFungiComponents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MorbusRadiometerItem extends Item {

    public MorbusRadiometerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return TypedActionResult.pass(user.getStackInHand(hand));
        useRadiometer(user, user);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(user.getWorld().isClient() || !(entity instanceof PlayerEntity)) return ActionResult.FAIL;
        useRadiometer(user, (PlayerEntity) entity);
        return ActionResult.SUCCESS;
    }

    private void useRadiometer(PlayerEntity user, PlayerEntity target) { // TODO: Add better message to Radiometer
        user.sendMessage(new TranslatableText(MagicFungiComponents.MORBUS_CORRUPTION.get(target).getValue() + ""), true); // TODO: Add option to have messages on actionBar or not
        target.damage(DamageSource.sting(user), 0.5F);
        target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ENCHANT_THORNS_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        user.getStackInHand(user.getActiveHand()).damage(1, user,
                (entity) -> user.sendToolBreakStatus(user.getActiveHand()));
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip.magicfungi.morbus_radiometer").formatted(Formatting.GRAY, Formatting.ITALIC));
    }
}