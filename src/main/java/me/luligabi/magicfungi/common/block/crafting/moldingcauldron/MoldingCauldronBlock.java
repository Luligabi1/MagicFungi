package me.luligabi.magicfungi.common.block.crafting.moldingcauldron;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MoldingCauldronBlock extends BlockWithEntity {

    public MoldingCauldronBlock() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MoldingCauldronBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(world, state, type);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    private static final VoxelShape RAYCAST_SHAPE = createCuboidShape(2, 4, 2, 14, 16, 14);
    private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(createCuboidShape(0, 0, 0, 16, 12, 16), VoxelShapes.union(createCuboidShape(0, 0, 4, 16, 3, 12), createCuboidShape(4, 0, 0, 12, 3, 16), createCuboidShape(2, 0, 2, 14, 3, 14), RAYCAST_SHAPE), BooleanBiFunction.ONLY_FIRST);

}
