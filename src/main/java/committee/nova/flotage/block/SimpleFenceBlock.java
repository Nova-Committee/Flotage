package committee.nova.flotage.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.world.phys.shapes.Shapes.or;

public class SimpleFenceBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static Map<Block, SimpleFenceBlock> MAP = new HashMap<>();
    private final Block crossedFence;

    public SimpleFenceBlock(Properties properties, Block crossedFence) {
        super(properties);
        this.crossedFence = crossedFence;
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
        MAP.put(crossedFence, this);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand playerHand, BlockHitResult result) {
        ItemStack handStack = player.getItemInHand(playerHand);
        if (handStack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof SimpleFenceBlock fence) {
                if (fence == this) {
                    world.setBlock(pos, crossedFence.defaultBlockState(), 3);
                    if (!player.isCreative()) {
                        handStack.shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return createShape(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST);
    }

    protected static VoxelShape createShape(boolean rotated) {
        VoxelShape shape1;
        VoxelShape shape2;
        VoxelShape shape3;
        VoxelShape shape4;
        if (rotated) {
            shape1 = Block.box(6.75, 0, 3, 9.25, 16, 5.5);
            shape2 = Block.box(6.75, 0, 10.5, 9.25, 16, 13);
            shape3 = Block.box(7,5.5,0,9, 7.5, 16);
            shape4 = Block.box(7,11,0,9, 13, 16);
        }else {
            shape1 = Block.box(3, 0, 6.75, 5.5, 16, 9.25);
            shape2 = Block.box(10.5, 0, 6.75, 13, 16, 9.25);
            shape3 = Block.box(0,5.5,7,16, 7.5, 9);
            shape4 = Block.box(0,11,7,16, 13, 9);
        }
        return or(shape1, shape2, shape3, shape4);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter getter, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter getter, BlockPos pos, Direction face) {
        return 8;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);

        return this.defaultBlockState()
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor world, BlockPos pos, BlockPos pos1) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, blockState, world, pos, pos1);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }
}
