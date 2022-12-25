package committee.nova.flotage.block;

import committee.nova.flotage.FlotageConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RaftBlock extends BaseRaftBlock {
    public static Map<Block, RaftBlock> MAP = new HashMap<>();
    private final Block brokenBlock;

    public RaftBlock(Properties properties, Block brokenBlock) {
        super(properties);
        this.brokenBlock = brokenBlock;
        MAP.put(brokenBlock, this);
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state1, boolean b) {
        SoundType soundtype = SoundType.WOOD;
        world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        world.getBlockTicks().scheduleTick(pos, this, 1);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isRainingAt(pos.above()) && new Random().nextFloat() < FlotageConfig.RAFT_DAMAGE_PROBABILITY.get()) {
            world.setBlock(pos, brokenBlock.defaultBlockState(), 3);
        }
    }
}
