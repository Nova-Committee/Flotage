package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FloTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Flotage.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("ntr", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.flotage"))
            .icon(() -> FloItems.OAK_RACK.get().getDefaultInstance())
            .displayItems((parameters, output) -> FloItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
