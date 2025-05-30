package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ToolAction;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CalamitesLogBlock extends ThinLogBlock{

    public static final BooleanProperty PNORTHEAST = BooleanProperty.create("northeast");
    public static final BooleanProperty PSOUTHEAST = BooleanProperty.create("southeast");
    public static final BooleanProperty PSOUTHWEST = BooleanProperty.create("southwest");
    public static final BooleanProperty PNORTHWEST = BooleanProperty.create("northwest");

    public static final BooleanProperty NORTHEAST = PNORTHEAST;
    public static final BooleanProperty SOUTHEAST = PSOUTHEAST;
    public static final BooleanProperty SOUTHWEST = PSOUTHWEST;
    public static final BooleanProperty NORTHWEST = PNORTHWEST;

    protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION
            = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((p_52346_) -> {
        return p_52346_.getKey().getAxis().isHorizontal();
    }).collect(Util.toMap());

    public CalamitesLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(NORTH, Boolean.FALSE)
                .setValue(EAST, Boolean.FALSE)
                .setValue(SOUTH, Boolean.FALSE)
                .setValue(WEST, Boolean.FALSE)
                .setValue(NORTHEAST, Boolean.FALSE)
                .setValue(SOUTHEAST, Boolean.FALSE)
                .setValue(SOUTHWEST, Boolean.FALSE)
                .setValue(NORTHWEST, Boolean.FALSE)
                .setValue(WATERLOGGED, Boolean.FALSE));

    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter blockgetter = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());

        BlockPos bpNorth = blockpos.north();
        BlockPos bpEast = blockpos.east();
        BlockPos bpSouth = blockpos.south();
        BlockPos bpWest = blockpos.west();

        BlockPos bpNorthEast = blockpos.north().east();
        BlockPos bpSouthEast = blockpos.south().east();
        BlockPos bpSouthWest = blockpos.south().west();
        BlockPos bpNorthWest = blockpos.north().west();

        BlockState bsNorth = blockgetter.getBlockState(bpNorth);
        BlockState bsEast = blockgetter.getBlockState(bpEast);
        BlockState bsSouth = blockgetter.getBlockState(bpSouth);
        BlockState bsWest = blockgetter.getBlockState(bpWest);

        BlockState bsNorthEast = blockgetter.getBlockState(bpNorthEast);
        BlockState bsSouthEast = blockgetter.getBlockState(bpSouthEast);
        BlockState bsSouthWest = blockgetter.getBlockState(bpSouthWest);
        BlockState bsNorthWest = blockgetter.getBlockState(bpNorthWest);

        boolean isAxisVertical = pContext.getClickedFace().getAxis().isVertical();

        return super.getStateForPlacement(pContext)
                .setValue(AXIS, pContext.getClickedFace().getAxis())
                .setValue(NORTH, this.connectsTo(bsNorth, bpNorth, blockpos, isAxisVertical))
                .setValue(EAST, this.connectsTo(bsEast, bpEast, blockpos, isAxisVertical))
                .setValue(SOUTH, this.connectsTo(bsSouth, bpSouth, blockpos, isAxisVertical))
                .setValue(WEST, this.connectsTo(bsWest, bpWest, blockpos, isAxisVertical))
                .setValue(NORTHEAST, this.connectsTo(bsNorthEast, bpNorthEast, blockpos, isAxisVertical))
                .setValue(SOUTHEAST, this.connectsTo(bsSouthEast, bpSouthEast, blockpos, isAxisVertical))
                .setValue(SOUTHWEST, this.connectsTo(bsSouthWest, bpSouthWest, blockpos, isAxisVertical))
                .setValue(NORTHWEST, this.connectsTo(bsNorthWest, bpNorthWest, blockpos, isAxisVertical))
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    private boolean connectsTo(BlockState branchState, BlockPos branchPos, BlockPos logPos, Boolean isVertical) {
        int x = logPos.getX() - branchPos.getX();
        int z = logPos.getZ() - branchPos.getZ();
        if (branchState.is(MMBlocks.CALAMITES_BRANCH.get()) && isVertical){
            if (z != 0 && x == 0 && (branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 0
                    || branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 4)){
                return true;
            }
            if (z != x && z != 0 && x != 0 && (branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 1
                    || branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 5)){
                return true;
            }

            if (z == 0 && x != 0 && (branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 2
                    || branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 6)){
                return true;
            }

            if (z == x && z != 0 && (branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 3
                    || branchState.getValue(CalamitesBranchBlock.ROTATION_8) == 7)){
                return true;
            }

        }
        return false;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        pLevel.scheduleTick(pCurrentPos, this, 2);

        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (pState.getValue(AXIS).isVertical() && pFacing.getAxis().getPlane() == Direction.Plane.HORIZONTAL){

            BlockPos bpNorthEast = pCurrentPos.north().east();
            BlockPos bpSouthEast = pCurrentPos.south().east();
            BlockPos bpSouthWest = pCurrentPos.south().west();
            BlockPos bpNorthWest = pCurrentPos.north().west();

            BlockState bsNorthEast = pLevel.getBlockState(bpNorthEast);
            BlockState bsSouthEast = pLevel.getBlockState(bpSouthEast);
            BlockState bsSouthWest = pLevel.getBlockState(bpSouthWest);
            BlockState bsNorthWest = pLevel.getBlockState(bpNorthWest);

            return pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing), Boolean.valueOf(this.connectsTo(pFacingState, pFacingPos, pCurrentPos, true)))
                    .setValue(NORTHEAST, this.connectsTo(bsNorthEast, bpNorthEast, pCurrentPos,true))
                    .setValue(SOUTHEAST, this.connectsTo(bsSouthEast, bpSouthEast, pCurrentPos,true))
                    .setValue(SOUTHWEST, this.connectsTo(bsSouthWest, bpSouthWest, pCurrentPos,true))
                    .setValue(NORTHWEST, this.connectsTo(bsNorthWest, bpNorthWest, pCurrentPos,true));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS, NORTH, EAST, WEST, SOUTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, WATERLOGGED);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(MMBlocks.CALAMITES_LOG.get())){
                return MMBlocks.STRIPPED_CALAMITES_LOG.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(NORTH, state.getValue(NORTH))
                        .setValue(EAST, state.getValue(EAST))
                        .setValue(WEST, state.getValue(WEST))
                        .setValue(SOUTH, state.getValue(SOUTH))
                        .setValue(NORTHEAST, state.getValue(NORTHEAST))
                        .setValue(NORTHWEST, state.getValue(NORTHWEST))
                        .setValue(SOUTHEAST, state.getValue(SOUTHEAST))
                        .setValue(SOUTHWEST, state.getValue(SOUTHWEST))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
            if(state.is(MMBlocks.CALAMITES_BARK.get())){
                return MMBlocks.STRIPPED_CALAMITES_BARK.get().defaultBlockState()
                        .setValue(AXIS, state.getValue(AXIS))
                        .setValue(NORTH, state.getValue(NORTH))
                        .setValue(EAST, state.getValue(EAST))
                        .setValue(WEST, state.getValue(WEST))
                        .setValue(SOUTH, state.getValue(SOUTH))
                        .setValue(NORTHEAST, state.getValue(NORTHEAST))
                        .setValue(NORTHWEST, state.getValue(NORTHWEST))
                        .setValue(SOUTHEAST, state.getValue(SOUTHEAST))
                        .setValue(SOUTHWEST, state.getValue(SOUTHWEST))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
