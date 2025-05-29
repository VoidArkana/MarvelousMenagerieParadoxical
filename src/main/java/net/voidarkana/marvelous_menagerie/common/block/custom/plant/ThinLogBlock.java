package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class ThinLogBlock extends DirectionalBlock implements SimpleWaterloggedBlock{

    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    protected static final VoxelShape Y_AXIS_AABB = Block.box(4, 0, 4, 12, 16, 12);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(4, 4, 0, 12, 12, 16);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0, 4, 4, 16, 12, 12);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ThinLogBlock(Properties properties) {
        super(properties);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return !pState.getValue(WATERLOGGED);
    }

    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        switch (p_154346_.getValue(FACING).getAxis()) {
            case X:
            default:
                return X_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
        }
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        switch (pRot) {
            case CLOCKWISE_180:
                return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING))).setValue(NORTH, pState.getValue(SOUTH)).setValue(EAST, pState.getValue(WEST)).setValue(SOUTH, pState.getValue(NORTH)).setValue(WEST, pState.getValue(EAST));
            case COUNTERCLOCKWISE_90:
                return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING))).setValue(NORTH, pState.getValue(EAST)).setValue(EAST, pState.getValue(SOUTH)).setValue(SOUTH, pState.getValue(WEST)).setValue(WEST, pState.getValue(NORTH));
            case CLOCKWISE_90:
                return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING))).setValue(NORTH, pState.getValue(WEST)).setValue(EAST, pState.getValue(NORTH)).setValue(SOUTH, pState.getValue(EAST)).setValue(WEST, pState.getValue(SOUTH));
            default:
                return pState;
        }
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        switch (pMirror) {
            case LEFT_RIGHT:
                return pState.setValue(FACING, pMirror.mirror(pState.getValue(FACING))).setValue(NORTH, pState.getValue(SOUTH)).setValue(SOUTH, pState.getValue(NORTH));
            case FRONT_BACK:
                return pState.setValue(FACING, pMirror.mirror(pState.getValue(FACING))).setValue(EAST, pState.getValue(WEST)).setValue(WEST, pState.getValue(EAST));
            default:
                return super.mirror(pState, pMirror);
        }
    }

    public boolean isPathfindable(BlockState p_154341_, BlockGetter p_154342_, BlockPos p_154343_, PathComputationType p_154344_) {
        return false;
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
}
