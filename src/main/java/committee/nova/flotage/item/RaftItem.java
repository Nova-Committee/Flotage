package committee.nova.flotage.item;

import committee.nova.flotage.block.RaftBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class RaftItem extends Item {
    private final RaftBlock block;

    public RaftItem(RaftBlock block, Properties properties) {
        super(properties);
        this.block = block;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World world, PlayerEntity player,@Nonnull Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockRayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, itemstack, result);
        if (ret != null) return ret;
        if (result.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemstack);
        } else if (result.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemstack);
        } else {
            if (!world.isClientSide()) {
                BlockPos blockpos = result.getBlockPos();
                BlockState state = world.getBlockState(blockpos);
                if (world.getBlockState(blockpos.above()) != state) {
                    if (world.getBlockState(blockpos.above()).getValues().containsKey(BlockStateProperties.WATERLOGGED)){
                        if (world.getBlockState(blockpos.above()).getValue(BlockStateProperties.WATERLOGGED)) {
                            return ActionResult.pass(itemstack);
                        }
                    }else {
                        if (canPlaceIn(state)) {
                            world.setBlock(blockpos, getBlock().getOverState(), 3);
                            if (player instanceof ServerPlayerEntity) {
                                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, blockpos, itemstack);
                            }
                            world.playSound(player, blockpos, this.getPlaceSound(state, world, blockpos, player), SoundCategory.BLOCKS,1,1);
                            if (!player.abilities.instabuild) {
                                itemstack.shrink(1);
                            }
                            return ActionResult.sidedSuccess(itemstack, true);
                        } else {
                            return ActionResult.pass(itemstack);
                        }
                    }
                }else {
                    return ActionResult.pass(itemstack);
                }
            }
        }
        return ActionResult.pass(itemstack);
    }

    protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
        return state.getSoundType(world, pos, entity).getPlaceSound();
    }

    protected boolean canPlaceIn(BlockState state) {
        return state == Blocks.WATER.defaultBlockState();
    }

    public RaftBlock getBlock() {
        return block;
    }
}
