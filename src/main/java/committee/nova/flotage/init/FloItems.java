package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.item.RaftItem;
import committee.nova.flotage.item.Tabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Flotage.MODID);

    public static final RegistryObject<Item> OAK_LOG_RAFT = ITEMS.register("oak_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.OAK_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
    public static final RegistryObject<Item> SPRUCE_LOG_RAFT = ITEMS.register("spruce_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.SPRUCE_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
    public static final RegistryObject<Item> BIRCH_LOG_RAFT = ITEMS.register("birch_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.BIRCH_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
    public static final RegistryObject<Item> JUNGLE_LOG_RAFT = ITEMS.register("jungle_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.JUNGLE_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
    public static final RegistryObject<Item> ACACIA_LOG_RAFT = ITEMS.register("acacia_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.ACACIA_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
    public static final RegistryObject<Item> DARK_OAK_LOG_RAFT = ITEMS.register("dark_oak_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.DARK_OAK_LOG_RAFT.get(), new Item.Properties().tab(Tabs.MAIN)));
}
