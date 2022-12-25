package committee.nova.flotage.compat;

import committee.nova.flotage.block.BrokenRaftBlock;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.init.FloProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;

import static committee.nova.flotage.init.FloBlocks.BLOCKS;

public class FDelight {
    public static final RegistryObject<Block> STRAW_BROKEN_RAFT = BLOCKS.register("straw_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT.instabreak(), Blocks.OAK_LOG.asItem()));

    public static final RegistryObject<Block> STRAW_RAFT = BLOCKS.register("straw_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.OAK_SLAB).instabreak(), STRAW_BROKEN_RAFT.get()));

    public static void load() {}
}
