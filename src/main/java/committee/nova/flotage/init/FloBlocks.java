package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.RaftBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Flotage.MODID);

    public static final RegistryObject<Block> OAK_LOG_RAFT = BLOCKS.register("oak_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.OAK_SLAB).strength(1.0F, 1.8F)));
}
