package me.luligabi.magicfungi.mixin;

import me.luligabi.magicfungi.common.item.ItemRegistry;
import me.luligabi.magicfungi.common.util.Util;
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
            Util.applyEffectIfNotPresent(playerEntity, StatusEffects.HEALTH_BOOST, 9999, 3);
        }
    }
}