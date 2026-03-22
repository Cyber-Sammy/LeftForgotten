package net.justmili.leftforgotten.client.fabric;

import java.util.function.Supplier;

import net.justmili.leftforgotten.client.ClassicBlocksModel;
import net.justmili.leftforgotten.init.LFResources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

public class ClassicBlocksModelFabric extends ClassicBlocksModel implements FabricBakedModel {
    public ClassicBlocksModelFabric(BakedModel wrapped) {
        super(wrapped);
    }

    @Override
    public boolean isVanillaAdapter() {
        return this.wrapped.isVanillaAdapter();
    }

    @Override
    public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
        Level level = Minecraft.getInstance().level;
        if (state != null && level != null && level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT) && state.is(Blocks.CHEST)) {
            return; // EBE please don't
        }

        this.wrapped.emitBlockQuads(blockView, state, pos, randomSupplier, context);
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<RandomSource> randomSupplier, RenderContext context) {
        this.wrapped.emitItemQuads(stack, randomSupplier, context);
    }
}
