package net.justmili.leftforgotten.forge.client;

import net.justmili.leftforgotten.client.CommonClient;
import net.justmili.leftforgotten.init.LFResources;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClient {
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        /// DEV NOTE: DEPRECATED API USAGE
        for (Block block : LFResources.getBlocks()) {
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
        }
        CommonClient.register();
    }

    @SubscribeEvent
    public static void wrapModelsForRemodelBlocks(ModelEvent.ModifyBakingResult event) {
        for (ResourceLocation id : event.getModels().keySet()) {
            if (id.getNamespace().equals("minecraft") && (id.getPath().startsWith("crafting_table") || id.getPath().startsWith("furnace"))) {
                event.getModels().put(id, new ClassicBlocksModelForge(event.getModels().get(id)));
            }
        }
    }
}