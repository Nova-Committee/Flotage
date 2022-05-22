package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.block.SimpleFenceBlock;
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
    public static final RegistryObject<Block> SPRUCE_LOG_RAFT = BLOCKS.register("spruce_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_SLAB).strength(1.0F, 1.8F)));
    public static final RegistryObject<Block> BIRCH_LOG_RAFT = BLOCKS.register("birch_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.BIRCH_SLAB).strength(1.0F, 1.8F)));
    public static final RegistryObject<Block> JUNGLE_LOG_RAFT = BLOCKS.register("jungle_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.JUNGLE_SLAB).strength(1.0F, 1.8F)));
    public static final RegistryObject<Block> ACACIA_LOG_RAFT = BLOCKS.register("acacia_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.ACACIA_SLAB).strength(1.0F, 1.8F)));
    public static final RegistryObject<Block> DARK_OAK_LOG_RAFT = BLOCKS.register("dark_oak_log_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.DARK_OAK_SLAB).strength(1.0F, 1.8F)));

    public static final RegistryObject<Block> CRIMSON_STEM_RAFT = BLOCKS.register("crimson_stem_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_SLAB).strength(1.5F, 2.2F)));
    public static final RegistryObject<Block> WARPED_STEM_RAFT = BLOCKS.register("warped_stem_raft",
            () -> new RaftBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_SLAB).strength(1.5F, 2.2F)));

    public static final RegistryObject<Block> OAK_LOG_FENCE = BLOCKS.register("oak_log_fence",
            () -> new SimpleFenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_FENCE).strength(1.5F, 2.2F)));
}
