package net.justmili.leftforgotten.fabric;

import net.fabricmc.api.ModInitializer;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.registries.fabric.LFBiomeModifiers;

public final class LeftForgottenFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LeftForgotten.init();
        LFBiomeModifiers.register();
    }
}
