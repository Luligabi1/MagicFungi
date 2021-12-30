package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.misc.component.MagicFungiComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        if(attacker instanceof PlayerEntity) MagicFungiComponents.MORBUS_CORRUPTION.get(attacker).decreaseBy(MagicFungi.CONFIG.impetusSwordCorruptionDecrease);
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}
