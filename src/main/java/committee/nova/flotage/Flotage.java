package committee.nova.flotage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Flotage.MODID)
public class Flotage {
    public static final String MODID = "flotage";

    public Flotage() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FlotageConfig.COMMON_CONFIG);
        FlotageUtil.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

}
