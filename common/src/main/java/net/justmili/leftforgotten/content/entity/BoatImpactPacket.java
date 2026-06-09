package net.justmili.leftforgotten.content.entity;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BoatImpactPacket {
    public static final ResourceLocation ID = LeftForgotten.asResource("boat_impact");

    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ID, BoatImpactPacket::handle);
    }

    public static void send(int entityId) {
        RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.buffer(), RegistryAccess.EMPTY);
        buf.writeInt(entityId);
        NetworkManager.sendToServer(ID, buf);
    }

    private static void handle(RegistryFriendlyByteBuf buf, NetworkManager.PacketContext context) {
        int entityId = buf.readInt();
        context.queue(() -> {
            Entity entity = context.getPlayer().level().getEntity(entityId);
            if (entity instanceof LFBoatEntity boat) {
                boat.breakOnImpactOnServer();
            }
        });
    }
}
