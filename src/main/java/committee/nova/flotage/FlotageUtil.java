package committee.nova.flotage;

import committee.nova.flotage.compat.FDelight;
import committee.nova.flotage.init.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class FlotageUtil {
    private static final boolean FDelightKey = ModList.get().isLoaded("farmersdelight");

    public static void register(IEventBus bus) {
        if (FDelightKey) {
            FDelight.load();
        }

        FloRecipeTypes.register();
        FloTileEntities.TYPES.register(bus);
        FloContainerTypes.CONTAINERS.register(bus);
        FloRecipes.SERIALIZERS.register(bus);
        FloBlocks.BLOCKS.register(bus);
        FloItems.ITEMS.register(bus);
    }

    public static ResourceLocation modRL(String path) {
        return new ResourceLocation(Flotage.MODID, path);
    }
}
