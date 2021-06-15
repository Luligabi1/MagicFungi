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
    public void executeGlyph(PlayerEntity playerEntity) {
        World world = playerEntity.getEntityWorld();
        if(world.getBlockState(blockPos).getBlock() != Blocks.CRAFTING_TABLE) return;
        world.setBlockState(blockPos,
                BlockRegistry.SPELL_DISCOVERY_BENCH_BLOCK.getDefaultState());
        playSound(playerEntity);
    }
}