package committee.nova.flotage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RaftBlock extends BaseRaftBlock implements SimpleWaterloggedBlock {
    public static Map<Block, RaftBlock> MAP = new HashMap<>();
    private final Block brokenBlock;

    public RaftBlock(BlockBehaviour.Properties properties, Block brokenBlock) {
        super(properties);
        this.brokenBlock = brokenBlock;
        MAP.put(brokenBlock, this);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if (world.isRainingAt(pos.above())) {
            world.setBlock(pos, brokenBlock.defaultBlockState(), 3);
        }
    }
}
