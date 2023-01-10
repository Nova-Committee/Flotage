package committee.nova.flotage;

import committee.nova.flotage.compat.FDelight;
import committee.nova.flotage.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

import static committee.nova.flotage.Flotage.MODID;

public class FlotageUtil {
    private static final boolean FDelightKey = ModList.get().isLoaded("farmersdelight");

    public static void register(final IEventBus bus) {
        if (FDelightKey) {
            FDelight.load();
        }

        FloRecipeTypes.RECIPE_TYPES.register(bus);
        FloBlockEntities.TYPES.register(bus);
        FloBlocks.BLOCKS.register(bus);
        FloItems.ITEMS.register(bus);
        FloRecipeSerializers.SECS.register(bus);
    }

    public static ResourceLocation asRes(String path) {
        return new ResourceLocation(MODID, path);
    }
}
