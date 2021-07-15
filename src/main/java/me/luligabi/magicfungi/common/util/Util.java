package me.luligabi.magicfungi.common.util;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

public class Util {

    public static Block getMushroomByNumber(int number) {
        return switch (number) {
            case 0 -> BlockRegistry.IMPETUS_MUSHROOM_PLANT_BLOCK;
            case 1 -> BlockRegistry.CLYPEUS_MUSHROOM_PLANT_BLOCK;
            case 2 -> BlockRegistry.UTILIS_MUSHROOM_PLANT_BLOCK;
            case 3 -> BlockRegistry.VIVIFICA_MUSHROOM_PLANT_BLOCK;
            case 4 -> BlockRegistry.MORBUS_MUSHROOM_PLANT_BLOCK;
            default -> null;
        };
    }

    public static void applyEffectIfNotPresent(LivingEntity livingEntity, StatusEffect statusEffect, int duration, int strength) {
        if(livingEntity.hasStatusEffect(statusEffect)) return;
        livingEntity.addStatusEffect(new StatusEffectInstance(statusEffect, duration*20, strength));
    }
}
