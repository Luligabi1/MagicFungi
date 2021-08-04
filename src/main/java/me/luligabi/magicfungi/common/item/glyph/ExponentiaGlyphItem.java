package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.util.ActionType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ExponentiaGlyphItem extends BaseGlyphItem {

    public ExponentiaGlyphItem(Settings settings) {
        super(settings);
        setSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE);
        setActionType(ActionType.BLOCK);
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        World world = playerEntity.getEntityWorld();
        if (world.getBlockState(blockPos).getBlock() == Blocks.CRAFTING_TABLE) {
            world.setBlockState(blockPos,
                    BlockRegistry.SPELL_DISCOVERY_BLOCK.getDefaultState());
            super.executeGlyph(playerEntity, itemStack);
        }
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {}
}