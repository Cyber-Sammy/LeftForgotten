package net.justmili.leftforgotten.datagen.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.core.datagen.*;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider((output, lookup) -> new LFLootTableProvider(output));
        pack.addProvider(LFBlockTagProvider::new);
        pack.addProvider(LFItemTagProvider::new);
        pack.addProvider((output, lookup) -> new LFModelProvider(output));
        pack.addProvider((output, lookup) -> new LFRecipeProvider(output));
    }
}
