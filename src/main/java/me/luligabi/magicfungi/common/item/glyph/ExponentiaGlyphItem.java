package me.luligabi.magicfungi.common.item.glyph;

import me.luligabi.magicfungi.common.registry.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ExponentiaGlyphItem extends GlyphBaseItem {

    public ExponentiaGlyphItem(Settings settings) {
        super(settings);
        setSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE);
    }

    @Override
    public boolean executeGlyph(PlayerEntity playerEntity) {
        World world = playerEntity.getEntityWorld();
        if (world.getBlockState(blockPos).getBlock() == Blocks.CRAFTING_TABLE) {
            world.setBlockState(blockPos,
                    BlockRegistry.SPELL_DISCOVERY_BLOCK.getDefaultState());
            super.executeGlyph(playerEntity);
            return true;
        }
        return false;
    }
}