package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.block.RaftBlock;
import committee.nova.flotage.item.RaftItem;
import committee.nova.flotage.item.Tabs;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Flotage.MODID);

    public static final RegistryObject<Item> OAK_LOG_RAFT = ITEMS.register("oak_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.OAK_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_LOG_RAFT = ITEMS.register("spruce_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.SPRUCE_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_LOG_RAFT = ITEMS.register("birch_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.BIRCH_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_LOG_RAFT = ITEMS.register("jungle_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.JUNGLE_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_LOG_RAFT = ITEMS.register("acacia_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.ACACIA_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_LOG_RAFT = ITEMS.register("dark_oak_log_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.DARK_OAK_LOG_RAFT.get(), itemPro()));

    public static final RegistryObject<Item> CRIMSON_STEM_RAFT = ITEMS.register("crimson_stem_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.CRIMSON_STEM_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_STEM_RAFT = ITEMS.register("warped_stem_raft",
            () -> new RaftItem((RaftBlock) FloBlocks.WARPED_STEM_RAFT.get(), itemPro()));

    public static final RegistryObject<Item> OAK_LOG_FENCE = ITEMS.register("oak_log_fence",
            () -> new BlockItem(FloBlocks.OAK_LOG_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_LOG_FENCE = ITEMS.register("spruce_log_fence",
            () -> new BlockItem(FloBlocks.SPRUCE_LOG_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_LOG_FENCE = ITEMS.register("birch_log_fence",
            () -> new BlockItem(FloBlocks.BIRCH_LOG_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_LOG_FENCE = ITEMS.register("jungle_log_fence",
            () -> new BlockItem(FloBlocks.JUNGLE_LOG_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_LOG_FENCE = ITEMS.register("acacia_log_fence",
            () -> new BlockItem(FloBlocks.ACACIA_LOG_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_LOG_FENCE = ITEMS.register("dark_oak_log_fence",
            () -> new BlockItem(FloBlocks.DARK_OAK_LOG_FENCE.get(), itemPro()));

    public static final RegistryObject<Item> CRIMSON_STEM_FENCE = ITEMS.register("crimson_stem_fence",
            () -> new BlockItem(FloBlocks.CRIMSON_STEM_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_STEM_FENCE = ITEMS.register("warped_stem_fence",
            () -> new BlockItem(FloBlocks.WARPED_STEM_FENCE.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties().tab(Tabs.MAIN);
    }

}
