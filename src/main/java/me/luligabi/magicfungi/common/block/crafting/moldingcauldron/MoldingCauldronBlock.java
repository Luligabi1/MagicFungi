package me.luligabi.magicfungi.common.block.crafting.moldingcauldron;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
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

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()) return ActionResult.CONSUME;
        MoldingCauldronBlockEntity blockEntity = (MoldingCauldronBlockEntity) world.getBlockEntity(pos);
        ItemStack moldingItem = blockEntity.getInventory().get(0);
        ItemStack handStack = player.getStackInHand(hand);

        if(!blockEntity.standBy) return ActionResult.PASS;
        if(moldingItem.isEmpty()) {
            if(!handStack.isEmpty() && handStack.isFood()) {
                blockEntity.getInventory().set(0, copyStack(handStack));
                handStack.decrement(1);
                blockEntity.markDirty();
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        } else if(handStack.isEmpty()) {
            blockEntity.standBy = true;
            blockEntity.processTime = 0;
            blockEntity.markDirty();
            ItemScatterer.spawn(world, pos, blockEntity);
            world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MoldingCauldronBlockEntity) {
                ItemScatterer.spawn(world, pos, (MoldingCauldronBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MoldingCauldronBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, BlockRegistry.MOLDING_CAULDRON_BLOCK_ENTITY_TYPE, MoldingCauldronBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    private ItemStack copyStack(ItemStack stack) {
        if(stack.isEmpty()) return ItemStack.EMPTY;

        ItemStack itemStack = new ItemStack(stack.getItem());
        itemStack.setBobbingAnimationTime(stack.getBobbingAnimationTime());
        if(stack.getNbt() != null) {
            itemStack.setNbt(stack.getNbt().copy());
        }
        return itemStack;
    }

    private static final VoxelShape RAYCAST_SHAPE = createCuboidShape(2, 4, 2, 14, 16, 14);
    private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(createCuboidShape(0, 0, 0, 16, 12, 16), VoxelShapes.union(createCuboidShape(0, 0, 4, 16, 3, 12), createCuboidShape(4, 0, 0, 12, 3, 16), createCuboidShape(2, 0, 2, 14, 3, 14), RAYCAST_SHAPE), BooleanBiFunction.ONLY_FIRST);

}
