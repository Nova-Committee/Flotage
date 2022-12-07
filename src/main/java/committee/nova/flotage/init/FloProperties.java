package committee.nova.flotage.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class FloProperties {
    public static final BlockBehaviour.Properties WOOD_BROKEN_RAFT = BlockBehaviour.Properties.of(Material.WOOD).strength(1.0F, 1.8F);
    public static final BlockBehaviour.Properties WOOD_RACK = BlockBehaviour.Properties.of(Material.WOOD).strength(2.2F,2.7F);

    public static BlockBehaviour.Properties woodRaft(Block copy) {
        return BlockBehaviour.Properties.copy(copy).strength(1.2F, 1.8F);
    }

    public static BlockBehaviour.Properties woodFence(Block copy) {
        return BlockBehaviour.Properties.copy(copy).strength(2.5F, 3F);
    }
}
