package net.justmili.leftforgotten.mixin;

import net.justmili.leftforgotten.init.LFResources;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public class ReshapeChest {
    private static final VoxelShape ALPHA_SINGLE = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape ALPHA_NORTH  = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape ALPHA_SOUTH  = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape ALPHA_WEST   = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape ALPHA_EAST   = Block.box(0, 0, 0, 16, 16, 16);

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void lf$alphaChestShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context,
                                    CallbackInfoReturnable<VoxelShape> cir) {
        if (!(level instanceof net.minecraft.world.level.Level world)) return;
        if (!world.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;

        if (state.getValue(ChestBlock.TYPE) == ChestType.SINGLE) {
            cir.setReturnValue(ALPHA_SINGLE);
            return;
        }

        switch (ChestBlock.getConnectedDirection(state)) {
            case NORTH -> cir.setReturnValue(ALPHA_NORTH);
            case SOUTH -> cir.setReturnValue(ALPHA_SOUTH);
            case WEST  -> cir.setReturnValue(ALPHA_WEST);
            case EAST  -> cir.setReturnValue(ALPHA_EAST);
            default    -> cir.setReturnValue(ALPHA_SINGLE);
        }
    }
}