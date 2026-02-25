package net.justmili.leftforgotten.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.justmili.leftforgotten.LeftForgotten;

@Mod(LeftForgotten.MOD_ID)
public final class LeftForgottenForge {
    public LeftForgottenForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(LeftForgotten.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        LeftForgotten.init();
    }
}
