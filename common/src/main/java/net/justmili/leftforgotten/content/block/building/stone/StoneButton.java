
package net.justmili.leftforgotten.content.block.building.stone;

import net.justmili.leftforgotten.content.block.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class StoneButton extends ButtonBlock {
    public StoneButton() {
        super(BlockSetType.STONE, 20, CommonBlock.button());
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }
}
