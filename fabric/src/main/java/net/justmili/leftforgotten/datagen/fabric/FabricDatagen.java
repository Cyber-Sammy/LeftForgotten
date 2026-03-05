package net.justmili.leftforgotten.datagen.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.justmili.leftforgotten.datagen.LeftForgottenBlockTagProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenItemTagProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenLootTableProvider;
import net.justmili.leftforgotten.datagen.LeftForgottenModelProvider;

public class FabricDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider((output, lookup) -> new LeftForgottenLootTableProvider(output));
        pack.addProvider(LeftForgottenBlockTagProvider::new);
        pack.addProvider(LeftForgottenItemTagProvider::new);
        pack.addProvider((output, lookup) -> new LeftForgottenModelProvider(output));
    }
}
