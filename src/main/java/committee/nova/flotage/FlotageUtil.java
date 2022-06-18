package committee.nova.flotage;

import committee.nova.flotage.init.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;

public class FlotageUtil {
    public static void register(final IEventBus bus) {
        FloRecipeTypes.register();
        FloTileEntities.TYPES.register(bus);
        FloRecipes.SERIALIZERS.register(bus);
        FloBlocks.BLOCKS.register(bus);
        FloItems.ITEMS.register(bus);
    }

    public static ResourceLocation modRL(String path) {
        return new ResourceLocation(Flotage.MODID, path);
    }
}
