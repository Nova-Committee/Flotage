package committee.nova.flotage.data;

import committee.nova.flotage.data.provider.FloBlocksProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new FloBlocksProvider(event.getGenerator(), event.getExistingFileHelper()));
    }
}

