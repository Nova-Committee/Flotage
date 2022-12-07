package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FloBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Flotage.MODID);

    public static final RegistryObject<Block> OAK_BROKEN_RAFT = BLOCKS.register("oak_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.OAK_LOG.asItem()));
    public static final RegistryObject<Block> SPRUCE_BROKEN_RAFT = BLOCKS.register("spruce_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.SPRUCE_LOG.asItem()));
    public static final RegistryObject<Block> BIRCH_BROKEN_RAFT = BLOCKS.register("birch_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.BIRCH_LOG.asItem()));
    public static final RegistryObject<Block> JUNGLE_BROKEN_RAFT = BLOCKS.register("jungle_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.JUNGLE_LOG.asItem()));
    public static final RegistryObject<Block> ACACIA_BROKEN_RAFT = BLOCKS.register("acacia_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.ACACIA_LOG.asItem()));
    public static final RegistryObject<Block> DARK_OAK_BROKEN_RAFT = BLOCKS.register("dark_oak_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.DARK_OAK_LOG.asItem()));
    public static final RegistryObject<Block> BAMBOO_BROKEN_RAFT = BLOCKS.register("bamboo_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Items.BAMBOO));
    public static final RegistryObject<Block> CRIMSON_BROKEN_RAFT = BLOCKS.register("crimson_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.CRIMSON_STEM.asItem()));
    public static final RegistryObject<Block> WARPED_BROKEN_RAFT = BLOCKS.register("warped_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT, Blocks.WARPED_STEM.asItem()));

    public static final RegistryObject<Block> OAK_LOG_RAFT = BLOCKS.register("oak_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.OAK_SLAB), OAK_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> SPRUCE_LOG_RAFT = BLOCKS.register("spruce_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.SPRUCE_SLAB), SPRUCE_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> BIRCH_LOG_RAFT = BLOCKS.register("birch_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.BIRCH_SLAB), BIRCH_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> JUNGLE_LOG_RAFT = BLOCKS.register("jungle_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.JUNGLE_SLAB), JUNGLE_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> ACACIA_LOG_RAFT = BLOCKS.register("acacia_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.ACACIA_SLAB), ACACIA_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> DARK_OAK_LOG_RAFT = BLOCKS.register("dark_oak_log_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.DARK_OAK_SLAB), DARK_OAK_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> BAMBOO_RAFT = BLOCKS.register("bamboo_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.BAMBOO), BAMBOO_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> CRIMSON_RAFT = BLOCKS.register("crimson_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.CRIMSON_STEM), CRIMSON_BROKEN_RAFT.get()));
    public static final RegistryObject<Block> WARPED_RAFT = BLOCKS.register("warped_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.WARPED_STEM), WARPED_BROKEN_RAFT.get()));

    public static final RegistryObject<Block> OAK_CROSSED_FENCE = BLOCKS.register("oak_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> SPRUCE_CROSSED_FENCE = BLOCKS.register("spruce_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.SPRUCE_FENCE)));
    public static final RegistryObject<Block> BIRCH_CROSSED_FENCE = BLOCKS.register("birch_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.BIRCH_FENCE)));
    public static final RegistryObject<Block> JUNGLE_CROSSED_FENCE = BLOCKS.register("jungle_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.JUNGLE_FENCE)));
    public static final RegistryObject<Block> ACACIA_CROSSED_FENCE = BLOCKS.register("acacia_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.ACACIA_FENCE)));
    public static final RegistryObject<Block> DARK_OAK_CROSSED_FENCE = BLOCKS.register("dark_oak_log_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.DARK_OAK_FENCE)));
    public static final RegistryObject<Block> CRIMSON_CROSSED_FENCE = BLOCKS.register("crimson_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.CRIMSON_FENCE)));
    public static final RegistryObject<Block> WARPED_CROSSED_FENCE = BLOCKS.register("warped_crossed_fence",
            () -> new CrossedFenceBlock(FloProperties.woodFence(Blocks.WARPED_FENCE)));

    public static final RegistryObject<Block> OAK_LOG_FENCE = BLOCKS.register("oak_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.OAK_FENCE), OAK_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> SPRUCE_LOG_FENCE = BLOCKS.register("spruce_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.SPRUCE_FENCE), SPRUCE_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> BIRCH_LOG_FENCE = BLOCKS.register("birch_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.BIRCH_FENCE), BIRCH_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> JUNGLE_LOG_FENCE = BLOCKS.register("jungle_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.JUNGLE_FENCE), JUNGLE_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> ACACIA_LOG_FENCE = BLOCKS.register("acacia_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.ACACIA_FENCE), ACACIA_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> DARK_OAK_LOG_FENCE = BLOCKS.register("dark_oak_log_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.DARK_OAK_FENCE), DARK_OAK_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> CRIMSON_FENCE = BLOCKS.register("crimson_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.CRIMSON_FENCE), CRIMSON_CROSSED_FENCE.get()));
    public static final RegistryObject<Block> WARPED_FENCE = BLOCKS.register("warped_fence",
            () -> new SimpleFenceBlock(FloProperties.woodFence(Blocks.WARPED_FENCE), WARPED_CROSSED_FENCE.get()));

    public static final RegistryObject<Block> OAK_RACK = BLOCKS.register("oak_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> SPRUCE_RACK = BLOCKS.register("spruce_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> BIRCH_RACK = BLOCKS.register("birch_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> JUNGLE_RACK = BLOCKS.register("jungle_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> ACACIA_RACK = BLOCKS.register("acacia_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> DARK_OAK_RACK = BLOCKS.register("dark_oak_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> CRIMSON_RACK = BLOCKS.register("crimson_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
    public static final RegistryObject<Block> WARPED_RACK = BLOCKS.register("warped_rack",
            () -> new RackBlock(FloProperties.WOOD_RACK));
}
