package net.justmili.leftforgotten.mechanics.compatibility;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import net.minecraft.world.entity.player.Player;

import static net.justmili.leftforgotten.init.DimKeys.ALPHA_MINECRAFT;

public class NostalgicTweaksCompatibiliy {
    public static boolean justOut = false;

    public static void onPlayerTick(Player player) {
        // check for client side
        if (!Platform.isModLoaded("nostalgic_tweaks")) return;
        if (player.level().dimension() == ALPHA_MINECRAFT) {
            loadVars();
            justOut = true;
        } else {

            if (justOut) {

                justOut = false;
                restoreVars();
            }
            storeVars();
        }
    }

    @ExpectPlatform
    public static void loadVars() {

    }

    @ExpectPlatform
    public static void restoreVars() {

    }

    @ExpectPlatform
    public static void storeVars() {

    }
}
