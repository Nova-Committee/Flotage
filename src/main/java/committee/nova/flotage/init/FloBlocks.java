package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Flotage.MODID);

    public static final RegistryObject<Block> OAK_BROKEN_RAFT = BLOCKS.register("oak_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> SPRUCE_BROKEN_RAFT = BLOCKS.register("spruce_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> BIRCH_BROKEN_RAFT = BLOCKS.register("birch_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> JUNGLE_BROKEN_RAFT = BLOCKS.register("jungle_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> ACACIA_BROKEN_RAFT = BLOCKS.register("acacia_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> DARK_OAK_BROKEN_RAFT = BLOCKS.register("dark_oak_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> CRIMSON_STEM_BROKEN_RAFT = BLOCKS.register("crimson_stem_broken_raft", BrokenRaftBlock::wood);
    public static final RegistryObject<Block> WARPED_STEM_BROKEN_RAFT = BLOCKS.register("warped_stem_broken_raft", BrokenRaftBlock::wood);

    public static final RegistryObject<Block> OAK_LOG_RAFT = BLOCKS.register("oak_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.OAK_SLAB), OAK_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> SPRUCE_LOG_RAFT = BLOCKS.register("spruce_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.SPRUCE_SLAB), SPRUCE_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> BIRCH_LOG_RAFT = BLOCKS.register("birch_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.BIRCH_SLAB), BIRCH_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> JUNGLE_LOG_RAFT = BLOCKS.register("jungle_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.JUNGLE_SLAB), JUNGLE_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> ACACIA_LOG_RAFT = BLOCKS.register("acacia_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.ACACIA_SLAB), ACACIA_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> DARK_OAK_LOG_RAFT = BLOCKS.register("dark_oak_log_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.DARK_OAK_SLAB), DARK_OAK_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> CRIMSON_STEM_RAFT = BLOCKS.register("crimson_stem_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.CRIMSON_STEM), CRIMSON_STEM_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> WARPED_STEM_RAFT = BLOCKS.register("warped_stem_raft",
            () -> new RaftBlock(BlockProperties.woodRaft(Blocks.WARPED_STEM), WARPED_STEM_BROKEN_RAFT.get()));

    public static final RegistryObject<Block> OAK_LOG_FENCE = BLOCKS.register("oak_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> SPRUCE_LOG_FENCE = BLOCKS.register("spruce_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.SPRUCE_FENCE)));
    public static final RegistryObject<Block> BIRCH_LOG_FENCE = BLOCKS.register("birch_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.BIRCH_FENCE)));
    public static final RegistryObject<Block> JUNGLE_LOG_FENCE = BLOCKS.register("jungle_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.JUNGLE_FENCE)));
    public static final RegistryObject<Block> ACACIA_LOG_FENCE = BLOCKS.register("acacia_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.ACACIA_FENCE)));
    public static final RegistryObject<Block> DARK_OAK_LOG_FENCE = BLOCKS.register("dark_oak_log_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.DARK_OAK_FENCE)));
    public static final RegistryObject<Block> CRIMSON_STEM_FENCE = BLOCKS.register("crimson_stem_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.CRIMSON_FENCE)));
    public static final RegistryObject<Block> WARPED_STEM_FENCE = BLOCKS.register("warped_stem_fence",
            () -> new SimpleFenceBlock(BlockProperties.woodFence(Blocks.WARPED_FENCE)));

    public static final RegistryObject<Block> OAK_DRYING_RACK = BLOCKS.register("oak_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> SPRUCE_DRYING_RACK = BLOCKS.register("spruce_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> BIRCH_DRYING_RACK = BLOCKS.register("birch_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> JUNGLE_DRYING_RACK = BLOCKS.register("jungle_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> ACACIA_DRYING_RACK = BLOCKS.register("acacia_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> DARK_OAK_DRYING_RACK = BLOCKS.register("dark_oak_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> CRIMSON_STEM_DRYING_RACK = BLOCKS.register("crimson_stem_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
    public static final RegistryObject<Block> WARPED_STEM_DRYING_RACK = BLOCKS.register("warped_stem_drying_rack",
            () -> new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK));
}
