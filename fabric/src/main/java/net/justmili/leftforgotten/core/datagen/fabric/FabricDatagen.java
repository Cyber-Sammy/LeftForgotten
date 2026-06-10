package net.justmili.leftforgotten.core.datagen.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.core.datagen.*;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator event) {
        var generator = event.createPack();
        generator.addProvider(LFLootTableProvider::new);
        generator.addProvider(LFBlockTagProvider::new);
        generator.addProvider(LFItemTagProvider::new);
        generator.addProvider((output, lookup) -> new LFModelProvider(output));
        generator.addProvider(LFRecipeProvider::new);
    }
}