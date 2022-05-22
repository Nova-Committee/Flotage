package committee.nova.flotage.event;

import committee.nova.flotage.init.FloBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.OAK_LOG_RAFT.get(), RenderType.cutout()));
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.SPRUCE_LOG_RAFT.get(), RenderType.cutout()));
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.BIRCH_LOG_RAFT.get(), RenderType.cutout()));
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.JUNGLE_LOG_RAFT.get(), RenderType.cutout()));
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.ACACIA_LOG_RAFT.get(), RenderType.cutout()));
        event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(FloBlocks.DARK_OAK_LOG_RAFT.get(), RenderType.cutout()));
    }

}
