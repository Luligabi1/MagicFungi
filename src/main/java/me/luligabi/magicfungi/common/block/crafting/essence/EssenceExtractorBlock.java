package me.luligabi.magicfungi.common.block.crafting.essence;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EssenceExtractorBlock extends BrewingStandBlock {

    public EssenceExtractorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EssenceExtractorBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, BlockRegistry.ESSENCE_EXTRACTOR_BLOCK_ENTITY_TYPE, EssenceExtractorBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof EssenceExtractorBlockEntity) {
            player.openHandledScreen((EssenceExtractorBlockEntity) blockEntity);
            //player.incrementStat(Stats.INTERACT_WITH_BREWINGSTAND);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EssenceExtractorBlockEntity) {
                ((EssenceExtractorBlockEntity) blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EssenceExtractorBlockEntity) {
                ItemScatterer.spawn(world, pos, (EssenceExtractorBlockEntity) blockEntity);
            }

            if (state.hasBlockEntity()) {
                world.removeBlockEntity(pos);
            }
        }
    }

}