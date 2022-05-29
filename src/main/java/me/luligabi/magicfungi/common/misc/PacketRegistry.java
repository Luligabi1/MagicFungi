package me.luligabi.magicfungi.common.misc;

import io.netty.buffer.Unpooled;
import me.luligabi.magicfungi.common.MagicFungi;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

public class PacketRegistry {

    public static Packet<?> createS2CUtilisLaserPacket(Entity e) {
        if (e.world.isClient) throw new IllegalStateException("Packet cannot be created on logical client!");
        PacketByteBuf byteBuf = new PacketByteBuf(Unpooled.buffer());
        byteBuf.writeVarInt(Registry.ENTITY_TYPE.getRawId(e.getType()));
        byteBuf.writeUuid(e.getUuid());
        byteBuf.writeVarInt(e.getId());

        byteBuf.writeDouble(e.getPos().x);
        byteBuf.writeDouble(e.getPos().y);
        byteBuf.writeDouble(e.getPos().z);

        byteBuf.writeByte((byte) MathHelper.floor(e.getPitch() * 256 / 360));
        byteBuf.writeByte((byte) MathHelper.floor(e.getYaw() * 256 / 360));

        return ServerPlayNetworking.createS2CPacket(UTILIS_LASER_ID, byteBuf);
    }

    public static final Identifier UTILIS_LASER_ID = new Identifier(MagicFungi.MOD_ID, "utilis_laser_spawn");

}