package net.justmili.leftforgotten.forge.client;

import java.util.List;

import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFResources;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ClassicBlocksModelForge implements BakedModel {
    private final BakedModel wrapped;

    public ClassicBlocksModelForge(BakedModel wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
        Level level = Minecraft.getInstance().level;
        if (level != null && level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            if (state.is(Blocks.CRAFTING_TABLE)) {
                state = LFBlocks.REMODEL_CRAFTING_TABLE.get().defaultBlockState();

                return Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .getQuads(state, direction, random);
            } else if (state.is(Blocks.FURNACE)) {
                state = LFBlocks.REMODEL_FURNACE.get().withPropertiesOf(state);

                return Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .getQuads(state, direction, random);
            }
        }

        return this.wrapped.getQuads(state, direction, random);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.wrapped.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.wrapped.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.wrapped.isGui3d();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.wrapped.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return this.wrapped.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.wrapped.getOverrides();
    }
}
