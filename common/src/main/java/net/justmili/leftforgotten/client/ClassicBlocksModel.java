package net.justmili.leftforgotten.client;

import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ClassicBlocksModel implements BakedModel {
    protected final BakedModel wrapped;

    public ClassicBlocksModel(BakedModel wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
        Level level = Minecraft.getInstance().level;
        if (state != null && level != null && level.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            if (state.is(Blocks.CRAFTING_TABLE)) {
                state = LFBlocks.REMODEL_CRAFTING_TABLE.get().defaultBlockState();

                return Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .getQuads(state, direction, random);
            } else if (state.is(Blocks.FURNACE)) {
                state = LFBlocks.REMODEL_FURNACE.get().withPropertiesOf(state);

                return Minecraft.getInstance().getBlockRenderer().getBlockModel(state)
                    .getQuads(state, direction, random);
            } else if (state.is(Blocks.CHEST)) {
                return List.of(); // only exists purely for EBE purposes really
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
        return this.wrapped.usesBlockLight();
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
    public ItemTransforms getTransforms() {
        return this.wrapped.getTransforms();
    }

    @Override
    public ItemOverrides getOverrides() {
        return this.wrapped.getOverrides();
    }
}

