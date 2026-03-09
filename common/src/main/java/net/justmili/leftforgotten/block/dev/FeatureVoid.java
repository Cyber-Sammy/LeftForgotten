package net.justmili.leftforgotten.block.dev;

import net.justmili.leftforgotten.init.LFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class FeatureVoid extends Block {
    public FeatureVoid() {
        super(Properties.of().replaceable().noCollission().noLootTable().noParticlesOnBreak().pushReaction(PushReaction.DESTROY));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState above = level.getBlockState(pos.above());
        boolean hasLeavesAbove = above.is(LFBlocks.LEAVES.get());

        if (!hasLeavesAbove) {
            level.removeBlock(pos, false);
            return;
        }

        int roll = random.nextInt(100);

        if (roll < 1) {
            level.setBlock(pos, LFBlocks.WOOD.get().defaultBlockState(), 3);
            level.setBlock(pos.below(), LFBlocks.WOOD.get().defaultBlockState(), 3);
        } else if (roll < 13) {
            level.setBlock(pos, LFBlocks.WOOD.get().defaultBlockState(), 3);
            level.setBlock(pos.below(), LFBlocks.WOOD.get().defaultBlockState(), 3);
        } else if (roll < 38) {
            level.setBlock(pos, LFBlocks.WOOD.get().defaultBlockState(), 3);
        } else {
            level.removeBlock(pos, false);
        }
    }
}