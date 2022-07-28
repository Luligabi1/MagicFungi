package me.luligabi.magicfungi.common.block.misc;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class HostFernBlock extends FernBlock {

    public HostFernBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void grow(ServerWorld world, net.minecraft.util.math.random.Random random, BlockPos pos, BlockState state) {
        TallPlantBlock tallPlantBlock = (TallPlantBlock) (state.isOf(BlockRegistry.HOST_FERN) ? BlockRegistry.LARGE_HOST_FERN : BlockRegistry.HOST_TALL_GRASS);
        if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
            TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
        }
    }
}