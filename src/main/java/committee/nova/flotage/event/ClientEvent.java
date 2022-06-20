package committee.nova.flotage.event;

import committee.nova.flotage.init.FloTileEntities;
import committee.nova.flotage.tiles.renderer.RackTER;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientRegistry.bindTileEntityRenderer(FloTileEntities.RACK_TILE.get(), RackTER::new);
        });
    }
}
