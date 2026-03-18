package net.justmili.leftforgotten.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderDispatcher.class)
public class RemodelBlocks {
    @WrapOperation(method = "renderBatched", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;" +
            "getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)" +
            "Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel lf$swapBatchedModel(BlockRenderDispatcher dispatcher, BlockState state, Operation<BakedModel> original,
                                           BlockState stateArg, BlockPos pos, BlockAndTintGetter level, PoseStack poseStack,
                                           VertexConsumer consumer, boolean checkSides, RandomSource random) {
        if (level instanceof Level worldLevel
            && worldLevel.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
            if (state.is(Blocks.CRAFTING_TABLE)) {
                return original.call(dispatcher, LFBlocks.REMODEL_CRAFTING_TABLE.get().defaultBlockState());
            }
            if (state.is(Blocks.FURNACE)) {
                return original.call(dispatcher, LFBlocks.REMODEL_FURNACE.get().defaultBlockState());
            }
        }
        return original.call(dispatcher, state);
    }
}