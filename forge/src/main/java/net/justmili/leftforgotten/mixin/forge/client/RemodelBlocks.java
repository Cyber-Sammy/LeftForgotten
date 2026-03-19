package net.justmili.leftforgotten.mixin.forge.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.justmili.leftforgotten.init.LFBlocks;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderDispatcher.class)
public class RemodelBlocks {
    @WrapOperation(
        method = "renderBatched(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/BlockAndTintGetter;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZLnet/minecraft/util/RandomSource;Lnet/minecraftforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;)V",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/BakedModel;")
    )
    private BakedModel lf$swapBatchedModel(BlockRenderDispatcher dispatcher, BlockState state, Operation<BakedModel> original) {
        Level worldLevel = Minecraft.getInstance().level;
        if (worldLevel != null && worldLevel.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) {
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