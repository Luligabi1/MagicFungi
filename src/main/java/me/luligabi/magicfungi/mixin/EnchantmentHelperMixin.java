package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(method = "getSweepingMultiplier",
            at = @At("HEAD"),
            cancellable = true)
    private static void injectGetSweepingMultiplier(LivingEntity entity, CallbackInfoReturnable<Float> cir) {
        if(entity.getEquippedStack(EquipmentSlot.MAINHAND).isOf(ItemRegistry.MORBUS_SCYTHE) || entity.getEquippedStack(EquipmentSlot.OFFHAND).isOf(ItemRegistry.MORBUS_SCYTHE)) {
            cir.setReturnValue(1.0F);
        }
    }
}