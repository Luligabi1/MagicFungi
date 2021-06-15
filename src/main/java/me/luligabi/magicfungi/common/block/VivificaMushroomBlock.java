package me.luligabi.magicfungi.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class VivificaMushroomBlock extends MagicMushroomBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.5D, 0.0D, 4.5D, 11.5D, 9.5D, 11.5D);

    public VivificaMushroomBlock(Settings settings) { super(settings); }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}