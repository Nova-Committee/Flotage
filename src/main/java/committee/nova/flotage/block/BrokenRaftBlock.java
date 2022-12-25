package committee.nova.flotage.block;

import committee.nova.flotage.FlotageConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class BrokenRaftBlock extends BaseRaftBlock implements IWaterLoggable {
    private final Item repairItem;

    public BrokenRaftBlock(Properties properties, Item repairItem) {
        super(properties.noCollission());
        this.repairItem = repairItem;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand playerHand, BlockRayTraceResult result) {
        ItemStack handStack = player.getMainHandItem();
        if (handStack.getItem() == repairItem) {
            if (!player.isCreative() && new Random().nextFloat() < FlotageConfig.RAFT_FIX_COST_CHANCE.get()) {
                handStack.shrink(1);
            }
            Block block = RaftBlock.MAP.get(this);
            world.setBlock(pos, block.defaultBlockState(), 3);
            block.onPlace(state, world, pos, block.defaultBlockState(), false);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state1, boolean b) {
        SoundType soundtype = SoundType.WOOD;
        world.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,(soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        world.getBlockTicks().scheduleTick(pos, this, 1);
    }
}
