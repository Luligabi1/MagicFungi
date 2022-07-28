package me.luligabi.magicfungi.common.misc;

import io.netty.buffer.Unpooled;
import me.luligabi.magicfungi.common.MagicFungi;
import me.luligabi.magicfungi.mixin.EntityInvoker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class PacketRegistry {

    public static Packet<?> createS2CThrownItemPacket(Entity e, Identifier identifier) {
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

        return ServerPlayNetworking.createS2CPacket(identifier, byteBuf);
    }

    public static void clientInitProjectileEntity(Identifier identifier) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, (client, handler, buf, responseSender) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(buf.readVarInt());
            UUID uuid = buf.readUuid();
            int entityId = buf.readVarInt();
            Vec3d pos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
            float pitch = (buf.readByte() * 360) / 256F;
            float yaw = (buf.readByte() * 360) / 256F;
            client.execute(() -> {
                if (MinecraftClient.getInstance().world == null) throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity entity = et.create(MinecraftClient.getInstance().world);
                if (entity == null) throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                entity.updateTrackedPosition(pos.x, pos.y, pos.z);
                entity.setPos(pos.x, pos.y, pos.z);
                ((EntityInvoker) entity).setPitch(pitch);
                ((EntityInvoker) entity).setYaw(yaw);
                entity.setId(entityId);
                entity.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, entity);
            });
        });
    }

    public static final Identifier UTILIS_LASER_ID = new Identifier(MagicFungi.MOD_ID, "utilis_laser_spawn");

    public static final Identifier MORBUS_PROJECTILE_ID = new Identifier(MagicFungi.MOD_ID, "morbus_projectile");

}