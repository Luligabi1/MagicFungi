package me.luligabi.magicfungi.common.item.relic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ImpetusSwordItem extends SwordItem {

    public ImpetusSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFromLava();
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}
