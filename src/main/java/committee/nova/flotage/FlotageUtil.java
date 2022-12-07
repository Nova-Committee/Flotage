package committee.nova.flotage;

import committee.nova.flotage.init.*;
import net.minecraftforge.eventbus.api.IEventBus;

public class FlotageUtil {
    public static void register(final IEventBus bus) {
        FloRecipeTypes.RECIPE_TYPES.register(bus);
        FloBlockEntities.TYPES.register(bus);
        FloBlocks.BLOCKS.register(bus);
        FloItems.ITEMS.register(bus);
        FloRecipeSerializers.SECS.register(bus);
    }
}
