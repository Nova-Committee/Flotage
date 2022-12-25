package committee.nova.flotage.compat;

import committee.nova.flotage.block.BrokenRaftBlock;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.util.FloProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

import static committee.nova.flotage.init.FloBlocks.BLOCKS;

public class FDelight {
    public static final RegistryObject<Block> STRAW_BROKEN_RAFT = BLOCKS.register("straw_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT.instabreak(), Registry.ITEM.get(new ResourceLocation("farmersdelight:straw"))));

    public static final RegistryObject<Block> STRAW_RAFT = BLOCKS.register("straw_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.OAK_SLAB).instabreak(), STRAW_BROKEN_RAFT.get()));

    public static void load() {}
}
