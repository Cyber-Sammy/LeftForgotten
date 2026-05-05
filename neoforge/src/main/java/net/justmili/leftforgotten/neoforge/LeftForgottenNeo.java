package net.justmili.leftforgotten.neoforge;

import dev.architectury.platform.Platform;
import net.justmili.leftforgotten.LeftForgotten;
import net.justmili.leftforgotten.core.datagen.*;
import net.justmili.leftforgotten.datagen.*;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(LeftForgotten.MOD_ID)
public final class LeftForgottenNeo {
    public LeftForgottenNeo(IEventBus modEventBus) {
        if (Platform.isModLoaded("true_end")) {
            throw new RuntimeException("""
                
                
                
                Left Forgotten and True End are incompatible! This is due duplicate code of each other's codebases
                caused by both being fully or/and partially made and managed by user JustMili.
                Please remove one of the mods from your instance.""");
        }

        modEventBus.addListener(LeftForgottenNeo::onDatagenSetup);

        LeftForgotten.init();
    }

    public static void onDatagenSetup(GatherDataEvent event) {
        var generator = event.getGenerator();
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFBlockTagProvider(output, event.getLookupProvider())));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) ((output) -> new LFItemTagProvider(output, event.getLookupProvider())));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) (LFLootTableProvider::new));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<? extends DataProvider>) (LFRecipeProvider::new));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<? extends DataProvider>) (LFModelProvider::new));
    }
}
