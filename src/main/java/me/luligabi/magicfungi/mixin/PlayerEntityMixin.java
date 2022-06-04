package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "tick",
            at = @At("RETURN"))
    public void injectTick(CallbackInfo callbackInfo) {
        PlayerEntity playerEntity = ((PlayerEntity) (Object) this);


        if(Util.isUsingFullArmor(playerEntity, ItemRegistry.MAGICAL_FUNGI_HELMET, ItemRegistry.MAGICAL_FUNGI_CHESTPLATE, ItemRegistry.MAGICAL_FUNGI_LEGGINGS, ItemRegistry.MAGICAL_FUNGI_BOOTS)) {
            if(!playerEntity.hasStatusEffect(StatusEffects.HEALTH_BOOST)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 4*20, 3, true, false, false));
            }

            /*
             * Adding a new StatusEffectInstance of Health Boost on top of the existing one causes the player
             * to lose all the extra hearts for a split second. The solution? Checking if the existing
             * health boost effect's duration is equal to 2 seconds (2*20, as in 40 ticks) then setting
             *  the duration back to 4 seconds. Is it ugly? A bit, but hey, it works :)
             */
            if(playerEntity.getActiveStatusEffects().get(StatusEffects.HEALTH_BOOST) instanceof StatusEffectInstanceAccessor instance && instance.getDuration() == 2*20) {
                ((StatusEffectInstanceAccessor) playerEntity.getActiveStatusEffects().get(StatusEffects.HEALTH_BOOST)).setDuration(4*20);
            }
        }
    }
}