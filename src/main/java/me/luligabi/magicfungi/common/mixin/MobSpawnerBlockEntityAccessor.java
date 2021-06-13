package me.luligabi.magicfungi.common.mixin;

import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobSpawnerBlockEntity.class)
public interface MobSpawnerBlockEntityAccessor {

    @Accessor
    MobSpawnerLogic getLogic();
}