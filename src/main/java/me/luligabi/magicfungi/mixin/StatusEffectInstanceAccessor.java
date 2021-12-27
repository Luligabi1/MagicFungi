package me.luligabi.magicfungi.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StatusEffectInstance.class)
public interface StatusEffectInstanceAccessor {

    @Accessor
    int getDuration();

    @Accessor("duration")
    void setDuration(int duration);

}