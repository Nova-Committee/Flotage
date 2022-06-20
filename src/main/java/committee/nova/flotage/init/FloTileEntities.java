package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.tiles.RackTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Flotage.MODID);

    public static final RegistryObject<TileEntityType<RackTileEntity>> RACK_TILE = TYPES.register("rack",
            () -> TileEntityType.Builder.of(RackTileEntity::new,
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
