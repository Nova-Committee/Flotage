package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.tile.container.RackContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FloContainerTypes {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Flotage.MODID);
    public static final RegistryObject<MenuType<RackContainer>> RACK_CONTAINER = CONTAINERS.register("rack_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> new RackContainer(windowId))));
}
