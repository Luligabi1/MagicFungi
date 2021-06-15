package me.luligabi.magicfungi.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class UtilisMushroomBlock extends MagicMushroomBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.5D, 0.0D, 4.5D, 11.5D, 7.0D, 11.5D);

    public UtilisMushroomBlock(Settings settings) { super(settings); }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}