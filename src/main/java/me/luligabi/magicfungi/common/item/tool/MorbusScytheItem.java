package me.luligabi.magicfungi.common.item.tool;

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
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) { //TODO: Add config to change effect duration
        Util.applyEffectIfNotPresent(target, StatusEffects.WITHER, 8, 0);
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}