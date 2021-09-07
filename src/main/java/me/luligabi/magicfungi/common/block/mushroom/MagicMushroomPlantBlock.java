package me.luligabi.magicfungi.common.block.mushroom;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Arrays;
import java.util.Random;

public abstract class MagicMushroomPlantBlock extends MushroomPlantBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public MagicMushroomPlantBlock(Settings settings) {
        super(settings, null);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    //TODO: Add custom behavior for growing giant Magic Mushrooms
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) { }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);

        Block[] bannedBlocks = {Blocks.SAND, Blocks.RED_SAND, Blocks.GRAVEL, Blocks.PACKED_ICE};

        if (blockState.isIn(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return true;
        } else {
            return world.getBaseLightLevel(pos, 0) < 13 && this.canPlantOnTop(blockState, world, blockPos) && !Arrays.asList(bannedBlocks).contains(blockState.getBlock());
        }
    }

    @Override
    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        return false;
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) { }

}