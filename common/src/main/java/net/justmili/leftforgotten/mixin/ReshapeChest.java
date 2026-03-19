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
    private static final VoxelShape FULL_BLOCK = Block.box(0, 0, 0, 16, 16, 16);

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void lf$alphaChestShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context,
                                    CallbackInfoReturnable<VoxelShape> cir) {
        if (!(level instanceof net.minecraft.world.level.Level world)) return;
        if (!world.dimension().equals(LFResources.Levels.ALPHA_MINECRAFT)) return;

        if (state.getValue(ChestBlock.TYPE) == ChestType.SINGLE) {
            cir.setReturnValue(FULL_BLOCK);
            return;
        }

        switch (ChestBlock.getConnectedDirection(state)) {
            case NORTH -> cir.setReturnValue(FULL_BLOCK);
            case SOUTH -> cir.setReturnValue(FULL_BLOCK);
            case WEST  -> cir.setReturnValue(FULL_BLOCK);
            case EAST  -> cir.setReturnValue(FULL_BLOCK);
            default    -> cir.setReturnValue(FULL_BLOCK);
        }
    }
}