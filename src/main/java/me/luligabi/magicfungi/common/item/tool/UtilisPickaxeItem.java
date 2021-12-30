package me.luligabi.magicfungi.common.item.tool;

import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.common.misc.component.MagicFungiComponents;
import me.luligabi.magicfungi.common.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UtilisPickaxeItem extends PickaxeItem {

    public UtilisPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        Util.applyEffectIfNotPresent(miner, StatusEffects.HASTE,
                MagicFungi.CONFIG.utilisPickaxeEffectDuration, MagicFungi.CONFIG.utilisPickaxeEffectStrength);
        if(miner instanceof PlayerEntity) MagicFungiComponents.MORBUS_CORRUPTION.get(miner).decreaseBy(MagicFungi.CONFIG.utilisPickaxeCorruptionDecrease);
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

}