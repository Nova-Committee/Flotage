package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.FlotageUtil;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Flotage.MODID);
    public static final RegistryObject<SoundEvent> RACK_DESTROYED = SOUNDS.register("rack_destroyed",
            () -> new SoundEvent(FlotageUtil.modRL("rack_destroyed")));
}
