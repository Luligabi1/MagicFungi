package me.luligabi.magicfungi.common.misc.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class MorbusCorruptionComponent implements FloatComponent, AutoSyncedComponent {

    private float corruptionPercentage = 0;
    private final Object provider;

    public MorbusCorruptionComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public float getValue() { return corruptionPercentage; }

    @Override
    public void setValue(float value) {
        if(value > 100F || value < 0F) throw new IllegalArgumentException("Value must be between 0 and 100 range!");
        this.corruptionPercentage = value;
        MagicFungiComponents.MORBUS_CORRUPTION.sync(this.provider);
    }

    @Override
    public float increaseBy(float increase) {
        return Math.min(corruptionPercentage + increase, 100F);
    }

    @Override
    public float decreaseBy(float decrease) {
        return Math.max(corruptionPercentage - decrease, 0F);
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound tag) { corruptionPercentage = tag.getFloat("corruptionPercentage"); }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag) { tag.putFloat("corruptionPercentage", corruptionPercentage); }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity player) { buf.writeFloat(this.corruptionPercentage); }

    @Override
    public void applySyncPacket(PacketByteBuf buf) { this.corruptionPercentage = buf.readFloat(); }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) { return player == this.provider; }

}