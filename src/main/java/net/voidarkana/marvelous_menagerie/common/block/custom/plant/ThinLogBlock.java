package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import org.jetbrains.annotations.Nullable;

public class ThinLogBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    protected static final VoxelShape Y_AXIS_AABB = Block.box(4, 0, 4, 12, 16, 12);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(4, 4, 0, 12, 12, 16);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0, 4, 4, 16, 12, 12);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ThinLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(WATERLOGGED, false));
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return !pState.getValue(WATERLOGGED);
    }

    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        return switch (p_154346_.getValue(AXIS)) {
            case Z -> Z_AXIS_AABB;
            case Y -> Y_AXIS_AABB;
            default -> X_AXIS_AABB;
        };
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState()
                .setValue(AXIS, pContext.getClickedFace().getAxis())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    public boolean isPathfindable(BlockState p_154341_, BlockGetter p_154342_, BlockPos p_154343_, PathComputationType p_154344_) {
        return false;
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return switch (pState.getValue(AXIS)) {
            case X -> pState.setValue(AXIS, Direction.Axis.Z);
            case Z -> pState.setValue(AXIS, Direction.Axis.X);
            default -> pState;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(WATERLOGGED, AXIS);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(MMBlocks.WILLIAMSONIA_LOG.get())){
                return MMBlocks.STRIPPED_WILLIAMSONIA_LOG.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
            if(state.is(MMBlocks.WILLIAMSONIA_BARK.get())){
                return MMBlocks.STRIPPED_WILLIAMSONIA_BARK.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
