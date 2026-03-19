package net.justmili.leftforgotten.client.fabric;

import java.util.function.Supplier;

import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFResources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

public class ClassicBlocksModel extends ForwardingBakedModel {
    public ClassicBlocksModel(BakedModel original) {
        this.wrapped = original;
    }

    @Override
    public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
        Level level = Minecraft.getInstance().level; // blockView is not necessarily Level
        if (state == null) return;
        if (level != null && level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            if (state.is(Blocks.CRAFTING_TABLE)) {
                state = LFBlocks.REMODEL_CRAFTING_TABLE.get().defaultBlockState();
                
                Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .emitBlockQuads(blockView, state, pos, randomSupplier, context);
                return;
            } else if (state.is(Blocks.FURNACE)) {
                state = LFBlocks.REMODEL_FURNACE.get().withPropertiesOf(state);

                Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .emitBlockQuads(blockView, state, pos, randomSupplier, context);
                return;
            }
        }

        super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }
}
