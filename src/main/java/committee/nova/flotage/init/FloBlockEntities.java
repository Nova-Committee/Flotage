package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.tile.RackBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FloBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Flotage.MODID);

    public static final RegistryObject<BlockEntityType<RackBlockEntity>> RACK_TILE = TYPES.register("rack",
            () -> BlockEntityType.Builder.of(RackBlockEntity::new,
                    FloBlocks.ACACIA_RACK.get(),
                    FloBlocks.BIRCH_RACK.get(),
                    FloBlocks.OAK_RACK.get(),
                    FloBlocks.SPRUCE_RACK.get(),
                    FloBlocks.JUNGLE_RACK.get(),
                    FloBlocks.DARK_OAK_RACK.get(),
                    FloBlocks.WARPED_RACK.get(),
                    FloBlocks.CRIMSON_RACK.get()
            ).build(null));
}
