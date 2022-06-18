package committee.nova.flotage.block;

import committee.nova.flotage.tiles.DryingRackTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class DryingRackBlock extends Block {
    public DryingRackBlock(Properties properties) {
        super(properties);
    }

    public static DryingRackBlock wood() {
        return new DryingRackBlock(BlockProperties.WOOD_DRYING_RACK);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        VoxelShape shape1 = Block.box(0,0,0,1,16,1);
        VoxelShape shape2 = Block.box(15,0,0,16,16,1);
        VoxelShape shape3 = Block.box(0,0,15,1,16,16);
        VoxelShape shape4 = Block.box(15,0,15,16,16,16);
        VoxelShape shape5 = Block.box(0,14.05,0,16,15.5,16);
        return VoxelShapes.or(shape1,shape2,shape3,shape4,shape5);
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, IBlockReader reader, BlockPos pos) {
        return 0.8F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 8;
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState blockState, boolean b) {
        world.getBlockTicks().scheduleTick(pos, this, 1);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (result.getDirection() == Direction.UP && world.getBlockEntity(pos) != null) {
            DryingRackTileEntity dryingTile = (DryingRackTileEntity) world.getBlockEntity(pos);
            if (dryingTile != null) {
                ItemStack handStack = player.getMainHandItem().copy();
                ItemStack inStack = dryingTile.getItem(0);
                if (inStack.isEmpty()) {
                    dryingTile.setItem(0, handStack);
                    player.getItemInHand(hand).shrink(handStack.getCount());
                }else {
                    if (inStack.getItem() == handStack.getItem()) {
                        if (inStack.getCount() == 64) {
                            return ActionResultType.PASS;
                        }
                        int totalQuantity = inStack.getCount() + handStack.getCount();
                        if (totalQuantity <=64) {
                            dryingTile.setItem(0, new ItemStack(inStack.getItem(), totalQuantity));
                            player.getItemInHand(hand).shrink(handStack.getCount());
                        }else {
                            dryingTile.setItem(0, new ItemStack(inStack.getItem(), 64));
                            player.getItemInHand(hand).setCount(totalQuantity - 64);
                        }
                    }else {
                        player.getItemInHand(hand).shrink(handStack.getCount());
                        ItemHandlerHelper.giveItemToPlayer(player, inStack);
                        dryingTile.setItem(0, handStack);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }else {
            return ActionResultType.PASS;
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        TileEntity tileentity = world.getBlockEntity(pos);
        if (tileentity != null) {
            if (tileentity instanceof DryingRackTileEntity) {
                DryingRackTileEntity dryingTile = (DryingRackTileEntity) tileentity;
                dryingTile.tick(world, pos, state, dryingTile);
            }
        }
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof DryingRackTileEntity) {
                InventoryHelper.dropContents(world, pos, (DryingRackTileEntity) tileentity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean triggerEvent(BlockState state, World world, BlockPos pos, int eventID, int eventParam) {
        TileEntity tileentity = world.getBlockEntity(pos);
        return tileentity != null && tileentity.triggerEvent(eventID, eventParam);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DryingRackTileEntity();
    }
}