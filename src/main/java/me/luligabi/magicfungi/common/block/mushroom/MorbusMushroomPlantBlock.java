package me.luligabi.magicfungi.common.block.mushroom;

import me.luligabi.magicfungi.common.util.MushroomType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MorbusMushroomPlantBlock extends MagicMushroomPlantBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.5D, 13.0D);

    public MorbusMushroomPlantBlock(Settings settings) { super(settings, MushroomType.MORBUS); }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}