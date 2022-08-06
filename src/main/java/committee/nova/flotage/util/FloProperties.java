package committee.nova.flotage.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FloProperties {
    public static final AbstractBlock.Properties WOOD_BROKEN_RAFT = AbstractBlock.Properties.of(Material.WOOD).strength(1.0F, 1.8F);
    public static final AbstractBlock.Properties WOOD_RACK = AbstractBlock.Properties.of(Material.WOOD).strength(2.2F,2.7F);

    public static AbstractBlock.Properties woodRaft(Block copy) {
        return AbstractBlock.Properties.copy(copy).strength(1.2F, 1.8F);
    }

    public static AbstractBlock.Properties woodFence(Block copy) {
        return AbstractBlock.Properties.copy(copy).strength(2.5F, 3F);
    }
}
