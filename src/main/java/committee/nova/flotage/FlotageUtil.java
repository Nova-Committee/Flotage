package committee.nova.flotage;

import committee.nova.flotage.init.FloBlocks;
import committee.nova.flotage.init.FloItems;
import net.minecraftforge.eventbus.api.IEventBus;

public class FlotageUtil {
    public static void register(final IEventBus bus) {
        FloItems.ITEMS.register(bus);
        FloBlocks.BLOCKS.register(bus);
    }
}
