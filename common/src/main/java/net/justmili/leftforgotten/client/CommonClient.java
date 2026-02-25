package net.justmili.leftforgotten.client;

import dev.architectury.event.events.client.ClientTickEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CommonClient {
    public static void register() {
        ClientTickEvent.CLIENT_POST.register(VersionOverlay::onClientTick);
    }
}
