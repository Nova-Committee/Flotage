package committee.nova.flotage.compat;

import committee.nova.flotage.block.BrokenRaftBlock;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.init.FloProperties;
import committee.nova.flotage.item.RaftItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static committee.nova.flotage.init.FloBlocks.BLOCKS;
import static committee.nova.flotage.init.FloItems.ITEMS;
import static committee.nova.flotage.init.FloItems.itemPro;

public class FDelight {
    public static final RegistryObject<Block> STRAW_BROKEN_RAFT_BLOCK = BLOCKS.register("straw_broken_raft",
            () -> new BrokenRaftBlock(FloProperties.WOOD_BROKEN_RAFT.instabreak(), ForgeRegistries.ITEMS.getValue(new ResourceLocation("farmersdelight:straw"))));

    public static final RegistryObject<Block> STRAW_RAFT_BLOCK = BLOCKS.register("straw_raft",
            () -> new RaftBlock(FloProperties.woodRaft(Blocks.OAK_SLAB).instabreak(), STRAW_BROKEN_RAFT_BLOCK.get()));

    public static final RegistryObject<Item> STRAW_BROKEN_RAFT = ITEMS.register("straw_broken_raft",
            () -> new RaftItem(STRAW_BROKEN_RAFT_BLOCK.get(), itemPro()));

    public static final RegistryObject<Item> STRAW_RAFT = ITEMS.register("straw_raft",
            () -> new RaftItem(STRAW_RAFT_BLOCK.get(), itemPro()));

    public static void load() {}
}
