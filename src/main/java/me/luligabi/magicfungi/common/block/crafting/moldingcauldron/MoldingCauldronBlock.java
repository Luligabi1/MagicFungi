package me.luligabi.magicfungi.common.block.crafting.moldingcauldron;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MoldingCauldronBlock extends BlockWithEntity {

    public MoldingCauldronBlock() {
        super(FabricBlockSettings.copy(Blocks.CAULDRON));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(FULL, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()) return ActionResult.CONSUME;
        MoldingCauldronBlockEntity blockEntity = (MoldingCauldronBlockEntity) world.getBlockEntity(pos);
        ItemStack moldingStack = blockEntity.getStack(0);
        ItemStack handStack = player.getStackInHand(hand);

        if(!handStack.isEmpty()) {
            if(handStack.isFood() && moldingStack.isEmpty()) { // Place Item
                blockEntity.getInventory().set(0, copyStack(handStack));
                handStack.decrement(1);
                blockEntity.markDirty();
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            } else if(handStack.isOf(moldingStack.getItem())) { // Remove Item (same as hand case)
                removeItem(blockEntity, world, pos);
                return ActionResult.SUCCESS;
            } else if(handStack.isIn(ConventionalItemTags.WATER_BUCKETS) && !state.get(FULL)) { // Place Water
                Item recipeRemainder = handStack.getItem().getRecipeRemainder();
                player.setStackInHand(hand, ItemUsage.exchangeStack(handStack, player, recipeRemainder != null ? new ItemStack(recipeRemainder) : ItemStack.EMPTY));
                player.incrementStat(Stats.USED.getOrCreateStat(handStack.getItem()));
                world.setBlockState(pos, state.with(FULL, true));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            } else if(handStack.isIn(ConventionalItemTags.EMPTY_BUCKETS) && state.get(FULL)) { // Remove Water
                player.setStackInHand(hand, ItemUsage.exchangeStack(handStack, player, new ItemStack(Items.WATER_BUCKET))); // FIXME: return proper bucket
                player.incrementStat(Stats.USED.getOrCreateStat(handStack.getItem()));
                world.setBlockState(pos, state.with(FULL, false));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                return ActionResult.SUCCESS;
            }
        } else if(!blockEntity.isEmpty()) { // Remove Item (empty hand)
            removeItem(blockEntity, world, pos);
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }

    private void removeItem(MoldingCauldronBlockEntity blockEntity, World world, BlockPos pos) {
        blockEntity.standBy = true;
        blockEntity.processTime = 0;
        blockEntity.markDirty();
        ItemScatterer.spawn(world, pos, blockEntity);
        world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof MoldingCauldronBlockEntity) {
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
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FULL);
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

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty FULL = BooleanProperty.of("full");

    private static final VoxelShape RAYCAST_SHAPE = createCuboidShape(2, 4, 2, 14, 16, 14);
    private static final VoxelShape OUTLINE_SHAPE = VoxelShapes.combineAndSimplify(createCuboidShape(0, 0, 0, 16, 12, 16), VoxelShapes.union(createCuboidShape(0, 0, 4, 16, 3, 12), createCuboidShape(4, 0, 0, 12, 3, 16), createCuboidShape(2, 0, 2, 14, 3, 14), RAYCAST_SHAPE), BooleanBiFunction.ONLY_FIRST);

}
