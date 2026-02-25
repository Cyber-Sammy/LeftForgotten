package net.justmili.leftforgotten.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class Common {
    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return false;
    }
    public static Boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return true;
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }
    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }
}
