package me.luligabi.magicfungi.common.block.util;

import com.google.common.base.Preconditions;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/* A special implementation of BlockEntity, syncing nbt data to client, as a fix to old BEs using the now removed BlockEntityClientSerializable.
 *
 * This was made by Technici4n, so props there :)
 */
public abstract class ClientSyncedBlockEntity extends BlockEntity {

    public ClientSyncedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void sync(boolean shouldRemesh) {
        Preconditions.checkNotNull(world); // Maintain distinct failure case from below
        if (!(world instanceof ServerWorld serverWorld))
            throw new IllegalStateException("Cannot call sync() on the logical client! Did you check world.isClient first?");

        shouldClientRemesh = shouldRemesh | shouldClientRemesh;
        serverWorld.getChunkManager().markForUpdate(pos);
    }

    public void sync() {
        sync(true);
    }

    public abstract void toTag(NbtCompound nbt);

    public abstract void fromTag(NbtCompound nbt);

    public abstract void toClientTag(NbtCompound nbt);

    public abstract void fromClientTag(NbtCompound nbt);

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public final NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = super.toInitialChunkDataNbt();
        toClientTag(nbt);
        nbt.putBoolean("#c", shouldClientRemesh); // mark client tag
        shouldClientRemesh = false;
        return nbt;
    }

    @Override
    protected final void writeNbt(NbtCompound nbt) {
        toTag(nbt);
    }

    @Override
    public final void readNbt(NbtCompound nbt) {
        if (nbt.contains("#c")) {
            fromClientTag(nbt);
            if (nbt.getBoolean("#c")) {
                remesh();
            }
        } else {
            fromTag(nbt);
        }
    }

    public final void remesh() {
        Preconditions.checkNotNull(world);
        if (!(world instanceof ClientWorld))
            throw new IllegalStateException("Cannot call remesh() on the server!");

        world.updateListeners(pos, null, null, 0);
    }

    protected final boolean isClientSide() {
        if (world == null) {
            throw new IllegalStateException("Cannot determine if the BE is client-side if it has no level yet");
        }
        return world.isClient();
    }

    private boolean shouldClientRemesh = true;

}