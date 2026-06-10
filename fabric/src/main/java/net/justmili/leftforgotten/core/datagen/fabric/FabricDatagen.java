package net.justmili.leftforgotten.core.datagen.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.core.datagen.*;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator event) {
        var generator = event.createPack();
        generator.addProvider((output, lookup) -> new LFLootTableProvider(output));
        generator.addProvider(LFBlockTagProvider::new);
        generator.addProvider(LFItemTagProvider::new);
        generator.addProvider((output, lookup) -> new LFModelProvider(output));
        generator.addProvider((output, lookup) -> new LFRecipeProvider(output));
    }
}
