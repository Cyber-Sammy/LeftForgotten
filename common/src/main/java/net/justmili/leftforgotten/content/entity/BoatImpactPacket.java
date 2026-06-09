package net.justmili.leftforgotten.content.entity;

import dev.architectury.networking.NetworkManager;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public record BoatImpactPacket(int entityId) implements CustomPacketPayload {
    public static final Type<BoatImpactPacket> ID = new Type<>(LeftForgotten.asResource("boat_impact"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoatImpactPacket> CODEC = StreamCodec.of(
            (buf, packet) -> buf.writeInt(packet.entityId()),
            buf -> new BoatImpactPacket(buf.readInt()));

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ID, CODEC, BoatImpactPacket::handle);
    }

    public static void send(int entityId) {
        NetworkManager.sendToServer(new BoatImpactPacket(entityId));
    }

    private static void handle(BoatImpactPacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            Entity entity = context.getPlayer().level().getEntity(packet.entityId());
            if (entity instanceof LFBoatEntity boat) {
                boat.breakOnImpactOnServer();
            }
        });
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}