package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.item.RaftItem;
import committee.nova.flotage.item.Tabs;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FloItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Flotage.MODID);

    public static final RegistryObject<Item> OAK_BROKEN_RAFT = ITEMS.register("oak_broken_raft",
            () -> new RaftItem(FloBlocks.OAK_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_BROKEN_RAFT = ITEMS.register("spruce_broken_raft",
            () -> new RaftItem(FloBlocks.SPRUCE_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_BROKEN_RAFT = ITEMS.register("birch_broken_raft",
            () -> new RaftItem(FloBlocks.BIRCH_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_BROKEN_RAFT = ITEMS.register("jungle_broken_raft",
            () -> new RaftItem(FloBlocks.JUNGLE_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_BROKEN_RAFT = ITEMS.register("acacia_broken_raft",
            () -> new RaftItem(FloBlocks.ACACIA_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_BROKEN_RAFT = ITEMS.register("dark_oak_broken_raft",
            () -> new RaftItem(FloBlocks.DARK_OAK_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_STEM_BROKEN_RAFT = ITEMS.register("crimson_stem_broken_raft",
            () -> new RaftItem(FloBlocks.CRIMSON_STEM_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_STEM_BROKEN_RAFT = ITEMS.register("warped_stem_broken_raft",
            () -> new RaftItem(FloBlocks.WARPED_STEM_BROKEN_RAFT.get(), itemPro()));

    public static final RegistryObject<Item> OAK_LOG_RAFT = ITEMS.register("oak_log_raft",
            () -> new RaftItem(FloBlocks.OAK_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_LOG_RAFT = ITEMS.register("spruce_log_raft",
            () -> new RaftItem(FloBlocks.SPRUCE_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_LOG_RAFT = ITEMS.register("birch_log_raft",
            () -> new RaftItem(FloBlocks.BIRCH_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_LOG_RAFT = ITEMS.register("jungle_log_raft",
            () -> new RaftItem(FloBlocks.JUNGLE_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_LOG_RAFT = ITEMS.register("acacia_log_raft",
            () -> new RaftItem(FloBlocks.ACACIA_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_LOG_RAFT = ITEMS.register("dark_oak_log_raft",
            () -> new RaftItem(FloBlocks.DARK_OAK_LOG_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_STEM_RAFT = ITEMS.register("crimson_stem_raft",
            () -> new RaftItem(FloBlocks.CRIMSON_STEM_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_STEM_RAFT = ITEMS.register("warped_stem_raft",
            () -> new RaftItem(FloBlocks.WARPED_STEM_RAFT.get(), itemPro()));

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

    public static final RegistryObject<Item> OAK_DRYING_RACK = ITEMS.register("oak_drying_rack",
            () -> new BlockItem(FloBlocks.OAK_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_DRYING_RACK = ITEMS.register("spruce_drying_rack",
            () -> new BlockItem(FloBlocks.SPRUCE_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_DRYING_RACK = ITEMS.register("birch_drying_rack",
            () -> new BlockItem(FloBlocks.BIRCH_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_DRYING_RACK = ITEMS.register("jungle_drying_rack",
            () -> new BlockItem(FloBlocks.JUNGLE_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_DRYING_RACK = ITEMS.register("acacia_drying_rack",
            () -> new BlockItem(FloBlocks.ACACIA_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_DRYING_RACK = ITEMS.register("dark_oak_drying_rack",
            () -> new BlockItem(FloBlocks.DARK_OAK_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_DRYING_RACK = ITEMS.register("crimson_stem_drying_rack",
            () -> new BlockItem(FloBlocks.CRIMSON_STEM_DRYING_RACK.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_DRYING_RACK = ITEMS.register("warped_stem_drying_rack",
            () -> new BlockItem(FloBlocks.WARPED_STEM_DRYING_RACK.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties().tab(Tabs.MAIN);
    }
}
