package committee.nova.flotage.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class SimpleFenceBlock extends Block implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalBlock.FACING;


    public SimpleFenceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
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
        return VoxelShapes.or(shape1, shape2, shape3, shape4);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
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
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);

        return this.defaultBlockState()
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, IWorld world, BlockPos pos, BlockPos pos1) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, blockState, world, pos, pos1);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
