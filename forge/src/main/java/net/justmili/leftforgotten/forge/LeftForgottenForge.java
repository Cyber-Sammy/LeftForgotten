package net.justmili.leftforgotten.forge;

import dev.architectury.platform.forge.EventBuses;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.datagen.LeftForgottenBlockTagProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenItemTagProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenLootTableProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenModelProvider;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LeftForgotten.MOD_ID)
public final class LeftForgottenForge {
    public LeftForgottenForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(LeftForgotten.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(LeftForgottenForge::onDatagenSetup);

        // Run our common setup.
        LeftForgotten.init();
    }

    public static void onDatagenSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) ((output) -> new LeftForgottenBlockTagProvider(output, event.getLookupProvider())));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) ((output) -> new LeftForgottenItemTagProvider(output, event.getLookupProvider())));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) (LeftForgottenLootTableProvider::new));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<? extends DataProvider>) (LeftForgottenModelProvider::new));
    }
}
