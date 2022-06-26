package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.tiles.container.RackContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Flotage.MODID);
    public static final RegistryObject<ContainerType<RackContainer>> DRY_RACK_CONTAINER = CONTAINERS.register("rack_container",
            () -> IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> new RackContainer(windowId, inv)));
}
