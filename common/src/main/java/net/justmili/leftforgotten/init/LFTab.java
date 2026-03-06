package net.justmili.leftforgotten.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.justmili.leftforgotten.LeftForgotten;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LFTab {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(LeftForgotten.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> LEFT_FORGOTTEN = REGISTRY.register(LFResources.Tabs.creativeTabID, () ->
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable(LFResources.Tabs.transKey))
            .icon(() -> new ItemStack(LFBlocks.GRASS_BLOCK.get())).displayItems((parameters, tabData) -> {

                for (RegistrySupplier<Item> item : LFItems.REGISTRY) {
                    tabData.accept(item.get());
                }

            }).build());
    
    public static void register() {
        REGISTRY.register();
    }
}
