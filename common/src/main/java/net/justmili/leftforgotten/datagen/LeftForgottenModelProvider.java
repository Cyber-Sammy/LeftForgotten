package net.justmili.leftforgotten.datagen;

import net.justmili.leftforgotten.datagen.impl.ImprovedModelProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

public class LeftForgottenModelProvider extends ImprovedModelProvider {
    public LeftForgottenModelProvider(PackOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {

    }
}
