package net.justmili.leftforgotten.mixinsquared;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import net.justmili.leftforgotten.LeftForgotten;

import java.util.List;

public class CancelPerlinNoiseOverwrite implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> list, String mixinClassName) {
        if (mixinClassName.equals("com.ishland.c2me.opts.math.mixin.MixinOctavePerlinNoiseSampler") ||
            mixinClassName.equals("com.ishland.c2me.opts.math.mixin.MixinPerlinNoiseSampler")) {
            LeftForgotten.LOGGER.info("Logged MixinSquared");
            return true;
        }
        LeftForgotten.LOGGER.info("Couldn't Log MixinSquared");
        return false;
    }
}