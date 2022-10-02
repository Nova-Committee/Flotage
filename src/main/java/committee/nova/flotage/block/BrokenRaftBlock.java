package committee.nova.flotage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BrokenRaftBlock extends BaseRaftBlock implements SimpleWaterloggedBlock {
    private final Item repairItem;

    public BrokenRaftBlock(Properties properties, Item repairItem) {
        super(properties.noCollission());
        this.repairItem = repairItem;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand playerHand, BlockHitResult result) {
        ItemStack handStack = player.getMainHandItem();
        if (handStack.getItem() == repairItem) {
            if (!player.isCreative()) {
                handStack.shrink(1);
            }
            Block block = RaftBlock.MAP.get(this);
            world.setBlock(pos, block.defaultBlockState(), 3);
            block.onPlace(state, world, pos, block.defaultBlockState(), false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}
