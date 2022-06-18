package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.tiles.DryingRackTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Flotage.MODID);

    public static final RegistryObject<TileEntityType<DryingRackTileEntity>> DRYING_TILE = TYPES.register("drying",
            () -> TileEntityType.Builder.of(DryingRackTileEntity::new,
                    FloBlocks.ACACIA_DRYING_RACK.get(),
                    FloBlocks.BIRCH_DRYING_RACK.get(),
                    FloBlocks.OAK_DRYING_RACK.get(),
                    FloBlocks.SPRUCE_DRYING_RACK.get(),
                    FloBlocks.JUNGLE_DRYING_RACK.get(),
                    FloBlocks.DARK_OAK_DRYING_RACK.get(),
                    FloBlocks.WARPED_STEM_DRYING_RACK.get(),
                    FloBlocks.CRIMSON_STEM_DRYING_RACK.get()
            ).build(null));
}
