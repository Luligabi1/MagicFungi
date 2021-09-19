package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;


public class MorbusScytheItem extends HoeItem {

    public MorbusScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) { 
        Util.applyEffectIfNotPresent(target, StatusEffects.WITHER,
                MagicFungi.CONFIG.morbusScytheEffectDuration, MagicFungi.CONFIG.morbusScytheEffectStrength);
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}