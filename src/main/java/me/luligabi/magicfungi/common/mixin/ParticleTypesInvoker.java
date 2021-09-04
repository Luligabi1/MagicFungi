package me.luligabi.magicfungi.common.mixin;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ParticleTypes.class)
public interface ParticleTypesInvoker {

    @Invoker("register")
    static DefaultParticleType register(String name, boolean alwaysShow) {
        throw new AssertionError();
    }

}