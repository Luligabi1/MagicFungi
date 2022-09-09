package me.luligabi.magicfungi.common.block.crafting.moldingcauldron;

import me.luligabi.magicfungi.common.block.BlockRegistry;
import me.luligabi.magicfungi.common.block.util.ClientSyncedBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

@SuppressWarnings("UnstableApiUsage")
public class MoldingCauldronBlockEntity extends ClientSyncedBlockEntity {

    public MoldingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockRegistry.MOLDING_CAULDRON_BLOCK_ENTITY_TYPE, pos, state);
    }

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {

        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) { return FluidConstants.BUCKET; }

        @Override
        protected void onFinalCommit() { markDirty(); }
    };


    @Override
    public void markDirty() {
        super.markDirty();
        if(!isClientSide()) {
            sync();
        }
    }

    @Override
    public void toTag(NbtCompound nbt) {
        nbt.put("fluidVariant", fluidStorage.variant.toNbt());
        nbt.putLong("amount", fluidStorage.amount);
    }

    @Override
    public void fromTag(NbtCompound nbt) {
        fluidStorage.variant = FluidVariant.fromNbt(nbt.getCompound("fluidVariant"));
        fluidStorage.amount = nbt.getLong("amount");
    }

    @Override
    public void toClientTag(NbtCompound nbt) {
        nbt.put("fluidVariant", fluidStorage.variant.toNbt());
        nbt.putLong("amount", fluidStorage.amount);
    }

    @Override
    public void fromClientTag(NbtCompound nbt) {
        fluidStorage.variant = FluidVariant.fromNbt(nbt.getCompound("fluidVariant"));
        fluidStorage.amount = nbt.getLong("amount");
    }


}