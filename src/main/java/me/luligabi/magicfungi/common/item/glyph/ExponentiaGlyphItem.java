package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ExponentiaGlyphItem extends AbstractGlyphItem {

    public ExponentiaGlyphItem(Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull MushroomType getMushroomType() {
        return MushroomType.INCOGNITA;
    }

    @Override
    public @NotNull SoundEvent getSoundEvent() {
        return SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE;
    }

    @Override
    public @NotNull ActionType getActionType() {
        return ActionType.BLOCK;
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        World world = playerEntity.getEntityWorld();
        if (world.getBlockState(blockPos).isOf(Blocks.CRAFTING_TABLE)) {
            world.setBlockState(blockPos, BlockRegistry.SPELL_DISCOVERY_BLOCK.getDefaultState());
            super.executeGlyph(playerEntity, itemStack);
        }
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {}
}