package me.luligabi.magicfungi.common.item.relic;

import com.mojang.datafixers.util.Pair;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.entity.MorbusProjectileEntity;
import me.luligabi.magicfungi.common.util.MushroomType;
import me.luligabi.magicfungi.common.util.Util;
import me.luligabi.magicfungi.mixin.HoeItemAccessor;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class MorbusScytheItem extends SwordItem implements Chargeable {

    public MorbusScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        Util.applyEffectIfNotPresent(target, StatusEffects.WITHER,
                MagicFungi.CONFIG.morbusScytheEffectDuration, MagicFungi.CONFIG.morbusScytheEffectStrength);
        increaseCharge(stack);
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return super.use(world, user, hand);
        ItemStack stack = user.getStackInHand(hand);

        if(user.isSneaking() && isChargeFull(stack)) {
            double x, z;
            for (int i = 0; i < 360; i += 15) {
                x = 2 * Math.sin(i);
                z = 2 * Math.cos(i);

                MorbusProjectileEntity morbusProjectile = new MorbusProjectileEntity(world, user, x, 0, z);
                world.spawnEntity(morbusProjectile);
            }
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.PLAYERS, 0.5F, 1F);
            resetCharge(stack, user);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = HoeItemAccessor.getTillingActions().get(world.getBlockState(blockPos).getBlock());
        if (pair == null) return ActionResult.PASS;
        Predicate<ItemUsageContext> predicate = pair.getFirst();
        Consumer<ItemUsageContext> consumer = pair.getSecond();
        if (predicate.test(context)) {
            PlayerEntity playerEntity = context.getPlayer();
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isClient()) {
                consumer.accept(context);
                if (playerEntity != null) {
                    context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
            return ActionResult.success(world.isClient());
            }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        appendQuote(tooltip, MushroomType.MORBUS, new TranslatableText("tooltip.magicfungi.morbus_scythe.author"),
                new TranslatableText("tooltip.magicfungi.morbus_scythe.1"),
                new TranslatableText("tooltip.magicfungi.morbus_scythe.2"));
        appendHiddenChargeLevel(tooltip, stack, MushroomType.MORBUS);
    }

    @Override
    public int getMaxCharge() {
        return ToolMaterials.MORBUS.getMaxCharge();
    }

    @Override
    public int getChargeBarColor(ItemStack stack) {
        return 0xAAAAAA;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isChargeFull(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}