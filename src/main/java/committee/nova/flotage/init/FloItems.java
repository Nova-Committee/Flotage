package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import committee.nova.flotage.item.RaftItem;
import committee.nova.flotage.util.FloTabs;
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
    public static final RegistryObject<Item> BAMBOO_BROKEN_RAFT = ITEMS.register("bamboo_broken_raft",
            () -> new RaftItem(FloBlocks.BAMBOO_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_BROKEN_RAFT = ITEMS.register("crimson_broken_raft",
            () -> new RaftItem(FloBlocks.CRIMSON_BROKEN_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_BROKEN_RAFT = ITEMS.register("warped_broken_raft",
            () -> new RaftItem(FloBlocks.WARPED_BROKEN_RAFT.get(), itemPro()));

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
    public static final RegistryObject<Item> BAMBOO_RAFT = ITEMS.register("bamboo_raft",
            () -> new RaftItem(FloBlocks.BAMBOO_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_RAFT = ITEMS.register("crimson_raft",
            () -> new RaftItem(FloBlocks.CRIMSON_RAFT.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_RAFT = ITEMS.register("warped_raft",
            () -> new RaftItem(FloBlocks.WARPED_RAFT.get(), itemPro()));

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
    public static final RegistryObject<Item> CRIMSON_FENCE = ITEMS.register("crimson_fence",
            () -> new BlockItem(FloBlocks.CRIMSON_FENCE.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_FENCE = ITEMS.register("warped_fence",
            () -> new BlockItem(FloBlocks.WARPED_FENCE.get(), itemPro()));

    public static final RegistryObject<Item> OAK_CROSSED_FENCE = ITEMS.register("oak_log_crossed_fence",
            () -> new BlockItem(FloBlocks.OAK_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> SPRUCE_CROSSED_FENCE = ITEMS.register("spruce_log_crossed_fence",
            () -> new BlockItem(FloBlocks.SPRUCE_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> BIRCH_CROSSED_FENCE = ITEMS.register("birch_log_crossed_fence",
            () -> new BlockItem(FloBlocks.BIRCH_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> JUNGLE_CROSSED_FENCE = ITEMS.register("jungle_log_crossed_fence",
            () -> new BlockItem(FloBlocks.JUNGLE_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> ACACIA_CROSSED_FENCE = ITEMS.register("acacia_log_crossed_fence",
            () -> new BlockItem(FloBlocks.ACACIA_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> DARK_OAK_CROSSED_FENCE = ITEMS.register("dark_oak_log_crossed_fence",
            () -> new BlockItem(FloBlocks.DARK_OAK_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> CRIMSON_CROSSED_FENCE = ITEMS.register("crimson_crossed_fence",
            () -> new BlockItem(FloBlocks.CRIMSON_CROSSED_FENCE.get(), nuPro()));
    public static final RegistryObject<Item> WARPED_CROSSED_FENCE = ITEMS.register("warped_crossed_fence",
            () -> new BlockItem(FloBlocks.WARPED_CROSSED_FENCE.get(), nuPro()));

    public static final RegistryObject<Item> OAK_RACK = ITEMS.register("oak_rack",
            () -> new BlockItem(FloBlocks.OAK_RACK.get(), itemPro()));
    public static final RegistryObject<Item> SPRUCE_RACK = ITEMS.register("spruce_rack",
            () -> new BlockItem(FloBlocks.SPRUCE_RACK.get(), itemPro()));
    public static final RegistryObject<Item> BIRCH_RACK = ITEMS.register("birch_rack",
            () -> new BlockItem(FloBlocks.BIRCH_RACK.get(), itemPro()));
    public static final RegistryObject<Item> JUNGLE_RACK = ITEMS.register("jungle_rack",
            () -> new BlockItem(FloBlocks.JUNGLE_RACK.get(), itemPro()));
    public static final RegistryObject<Item> ACACIA_RACK = ITEMS.register("acacia_rack",
            () -> new BlockItem(FloBlocks.ACACIA_RACK.get(), itemPro()));
    public static final RegistryObject<Item> DARK_OAK_RACK = ITEMS.register("dark_oak_rack",
            () -> new BlockItem(FloBlocks.DARK_OAK_RACK.get(), itemPro()));
    public static final RegistryObject<Item> CRIMSON_RACK = ITEMS.register("crimson_rack",
            () -> new BlockItem(FloBlocks.CRIMSON_RACK.get(), itemPro()));
    public static final RegistryObject<Item> WARPED_RACK = ITEMS.register("warped_rack",
            () -> new BlockItem(FloBlocks.WARPED_RACK.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties().tab(FloTabs.MAIN);
    }

    public static Item.Properties nuPro() {
        return new Item.Properties();
    }
}
