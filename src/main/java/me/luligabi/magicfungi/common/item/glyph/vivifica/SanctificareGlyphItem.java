package me.luligabi.magicfungi.common.item.glyph.vivifica;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.item.glyph.BaseGlyphItem;
import me.luligabi.magicfungi.common.util.ActionType;
import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SanctificareGlyphItem extends BaseGlyphItem {

    public SanctificareGlyphItem(Settings settings) {
        super(settings);
        setMushroomType(MushroomType.VIVIFICA);
        setSound(SoundEvents.BLOCK_MOSS_BREAK);
        setActionType(ActionType.BLOCK);
    }

    @Override
    public void executeBlockGlyph(PlayerEntity playerEntity, ItemStack itemStack) {
        World world = playerEntity.getEntityWorld();
        double radius = 6.5;
        for (int x = (int) -radius - 1; x <= radius; x++) {
            for (int y = (int) -radius - 1; y <= radius; y++) {
                for (int z = (int) -radius - 1; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(playerEntity.getBlockX() + x, playerEntity.getBlockY() + y, playerEntity.getBlockZ() + z);
                    if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) <= radius && world.random.nextBoolean()) {
                        if(world.getBlockState(blockPos).getBlock() == BlockRegistry.HOST_GRASS_BLOCK) {
                            world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
                        }
                        if(world.getBlockState(blockPos).getBlock() == BlockRegistry.HOST_DIRT) {
                            world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
                        }
                        super.executeGlyph(playerEntity, itemStack);
                    }
                }
            }
        }
    }

    @Override
    public void executeEntityGlyph(PlayerEntity playerEntity, ItemStack itemStack, LivingEntity livingEntity) {}

}