package me.luligabi.magicfungi.common.mixin;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "tick", at = @At("RETURN"), cancellable = true)
    public void injectTick(CallbackInfo info) {
        PlayerEntity playerEntity = ((PlayerEntity) (Object) this);
        if(Util.isUsingFullArmor(playerEntity, ItemRegistry.MAGICAL_FUNGI_HELMET, ItemRegistry.MAGICAL_FUNGI_CHESTPLATE, ItemRegistry.MAGICAL_FUNGI_LEGGINGS, ItemRegistry.MAGICAL_FUNGI_BOOTS)) {
            Util.applyEffectIfNotPresent(playerEntity, StatusEffects.HEALTH_BOOST, 9999, 1);
        } else {
            Util.removeEffectIfPresent(playerEntity, StatusEffects.HEALTH_BOOST);
        }
        info.cancel();
    }
}