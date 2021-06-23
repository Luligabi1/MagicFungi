package me.luligabi.magicfungi.common.block.mushroom;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public abstract class MagicMushroomBlock extends MushroomPlantBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public MagicMushroomBlock(Settings settings) {
        super(settings, null);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    //TODO: Add custom behavior for growing giant Magic Mushrooms
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) { }

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
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
    }


    public static final AbstractBlock.Settings MUSHROOM_SETTINGS =  AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS);
}