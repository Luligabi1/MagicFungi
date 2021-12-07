package me.luligabi.magicfungi.mixin;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.UUID;

@Mixin(ZombieVillagerEntity.class)
public interface ZombieVillagerEntityInvoker {

    @Invoker("setConverting")
    void invokeSetConverting(@Nullable UUID uuid, int delay);
}