package me.luligabi.magicfungi.common.block.misc;

import me.luligabi.magicfungi.common.misc.TagRegistry;
import me.luligabi.magicfungi.common.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HostDirtBlock extends Block {

    public HostDirtBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if(!WorldUtil.isMorbusSpreadingActive(world)) return;
        if(!hasExposedSide(pos, world)) return;
        BlockState blockState = this.getDefaultState();
        for (int i = 0; i < 4; ++i) {
            BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

            if (world.getBlockState(blockPos).isIn(TagRegistry.MORBUS_DIRT_SPREADABLE) && !world.getBlockState(blockPos).isAir()) {
                world.setBlockState(blockPos, blockState);
            }
        }
    }

    private boolean hasExposedSide(BlockPos blockPos, World world) {
        return world.getBlockState(blockPos.north()).isAir() ||
                world.getBlockState(blockPos.south()).isAir() ||
                world.getBlockState(blockPos.west()).isAir() ||
                world.getBlockState(blockPos.east()).isAir() ||
                world.getBlockState(blockPos.down()).isAir();
    }

}