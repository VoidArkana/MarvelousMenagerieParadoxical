package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.common.ToolAction;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import org.jetbrains.annotations.Nullable;

public class BranchingTrunkBlock extends PipeBlock implements SimpleWaterloggedBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BranchingTrunkBlock(Properties p_51707_) {
        super(0.25f, p_51707_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(WATERLOGGED, false)
                .setValue(NORTH, Boolean.valueOf(false))
                .setValue(EAST, Boolean.valueOf(false))
                .setValue(SOUTH, Boolean.valueOf(false))
                .setValue(WEST, Boolean.valueOf(false))
                .setValue(UP, Boolean.valueOf(false))
                .setValue(DOWN, Boolean.valueOf(false)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockGetter pLevel = pContext.getLevel();
        BlockPos pPos = pContext.getClickedPos();
        BlockState thisState = pLevel.getBlockState(pPos);

        return this.defaultBlockState()
                .setValue(DOWN, this.connectsTo(thisState, pLevel, pPos, Direction.DOWN))
                .setValue(UP, this.connectsTo(thisState, pLevel, pPos, Direction.UP))
                .setValue(NORTH, this.connectsTo(thisState, pLevel, pPos, Direction.NORTH))
                .setValue(EAST, this.connectsTo(thisState, pLevel, pPos, Direction.EAST))
                .setValue(SOUTH, this.connectsTo(thisState, pLevel, pPos, Direction.SOUTH))
                .setValue(WEST, this.connectsTo(thisState, pLevel, pPos, Direction.WEST))
                .setValue(AXIS, pContext.getClickedFace().getAxis())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }


    private boolean connectsTo(BlockState thisState, BlockGetter pLevel, BlockPos pPos, Direction direction) {
        BlockState otherState = pLevel.getBlockState(pPos.offset(direction.getNormal()));
        if (thisState.getBlock() instanceof BranchingTrunkBlock) {
            if (otherState.getBlock() instanceof BranchingTrunkBlock) {
                return thisState.getValue(AXIS) != otherState.getValue(AXIS) ||
                        (thisState.getValue(AXIS) == otherState.getValue(AXIS) && thisState.getValue(AXIS) == direction.getAxis());
            } else if (otherState.getBlock() instanceof WilliamsoniaLeaves) {
                if (otherState.getValue(WilliamsoniaLeaves.ATTACHMENT_STYLE) == WilliamsoniaLeaves.WilliamsoniaAttachFace.FLOOR)
                    return (direction == Direction.UP);
                return direction!=Direction.DOWN && thisState.getValue(AXIS) != otherState.getValue(WilliamsoniaLeaves.FACING).getAxis();
            } else if (otherState.getBlock() instanceof ThinLogBlock) {
                return thisState.getValue(AXIS) == otherState.getValue(ThinLogBlock.AXIS) && thisState.getValue(AXIS) == direction.getAxis();
            }
        }
        return otherState.isFaceSturdy(pLevel, pPos.offset(direction.getNormal()), direction.getOpposite());
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        boolean flag = this.connectsTo(pState, pLevel, pCurrentPos, pFacing);
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing), Boolean.valueOf(flag));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(UP, DOWN, NORTH, SOUTH, EAST, WEST, WATERLOGGED, AXIS);
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }


    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(MMBlocks.WILLIAMSONIA_PLANT.get())){
                return MMBlocks.STRIPPED_WILLIAMSONIA_PLANT.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(NORTH, state.getValue(NORTH))
                        .setValue(SOUTH, state.getValue(SOUTH))
                        .setValue(EAST, state.getValue(EAST))
                        .setValue(WEST, state.getValue(WEST))
                        .setValue(UP, state.getValue(UP))
                        .setValue(DOWN, state.getValue(DOWN))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
