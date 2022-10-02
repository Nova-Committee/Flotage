package committee.nova.flotage.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

public class RaftItem extends BlockItem {

    public RaftItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use( Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
        if (result.getType() == BlockHitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else if (result.getType() != BlockHitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            if (!world.isClientSide()) {
                BlockPos blockpos = result.getBlockPos();
                BlockState state = world.getBlockState(blockpos);
                BlockState aboveBlock = world.getBlockState(blockpos.above());
                if (aboveBlock != state) {
                    if (aboveBlock.getValues().containsKey(BlockStateProperties.WATERLOGGED)){
                        if (aboveBlock.getValue(BlockStateProperties.WATERLOGGED)) {
                            return InteractionResultHolder.pass(itemstack);
                        }
                    }else {
                        if (canPlaceIn(state)) {
                            world.setBlock(blockpos, getBlock().defaultBlockState(), 3);
                            if (player instanceof ServerPlayer) {
                                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
                            }
                            if (!player.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }
                            return InteractionResultHolder.sidedSuccess(itemstack, true);
                        }
                    }
                }
            }
            return InteractionResultHolder.pass(itemstack);
        }
    }

    protected boolean canPlaceIn(BlockState state) {
        return state == Blocks.WATER.defaultBlockState();
    }
}
