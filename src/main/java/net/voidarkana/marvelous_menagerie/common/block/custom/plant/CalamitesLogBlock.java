package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

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
                .setValue(FACING, Direction.UP)
                .setValue(NORTH, Boolean.valueOf(false))
                .setValue(EAST, Boolean.valueOf(false))
                .setValue(SOUTH, Boolean.valueOf(false))
                .setValue(WEST, Boolean.valueOf(false))
                .setValue(NORTHEAST,  Boolean.valueOf(false))
                .setValue(SOUTHEAST, Boolean.valueOf(false))
                .setValue(SOUTHWEST,  Boolean.valueOf(false))
                .setValue(NORTHWEST, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false)));

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

        BlockState bsThis = blockgetter.getBlockState(blockpos);

        BlockState bsNorth = blockgetter.getBlockState(bpNorth);
        BlockState bsEast = blockgetter.getBlockState(bpEast);
        BlockState bsSouth = blockgetter.getBlockState(bpSouth);
        BlockState bsWest = blockgetter.getBlockState(bpWest);

        BlockState bsNorthEast = blockgetter.getBlockState(bpNorthEast);
        BlockState bsSouthEast = blockgetter.getBlockState(bpSouthEast);
        BlockState bsSouthWest = blockgetter.getBlockState(bpSouthWest);
        BlockState bsNorthWest = blockgetter.getBlockState(bpNorthWest);

        return super.getStateForPlacement(pContext)
                .setValue(FACING, pContext.getNearestLookingDirection().getOpposite())
                .setValue(NORTH, Boolean.valueOf(this.connectsTo(bsNorth, bpNorth, bsThis)))
                .setValue(EAST, Boolean.valueOf(this.connectsTo(bsEast, bpEast, bsThis)))
                .setValue(SOUTH, Boolean.valueOf(this.connectsTo(bsSouth, bpSouth, bsThis)))
                .setValue(WEST, Boolean.valueOf(this.connectsTo(bsWest, bpWest, bsThis)))
                .setValue(NORTHEAST,  Boolean.valueOf(this.connectsTo(bsNorthEast, bpNorthEast, bsThis)))
                .setValue(SOUTHEAST, Boolean.valueOf(this.connectsTo(bsSouthEast, bpSouthEast, bsThis)))
                .setValue(SOUTHWEST,  Boolean.valueOf(this.connectsTo(bsSouthWest, bpSouthWest, bsThis)))
                .setValue(NORTHWEST, Boolean.valueOf(this.connectsTo(bsNorthWest, bpNorthWest, bsThis)))
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    private boolean connectsTo(BlockState branchState, BlockPos branchPos, BlockState logState) {
        if (branchState.is(MMBlocks.CALAMITES_BRANCH.get()) && logState.is(this)){
            if (logState.getValue(FACING).getAxis().isVertical()){
            return true;
            }
        }
        return false;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (pFacing.getAxis().getPlane() == Direction.Plane.VERTICAL){
            BlockPos bpNorthEast = pCurrentPos.north().east();
            BlockPos bpSouthEast = pCurrentPos.south().east();
            BlockPos bpSouthWest = pCurrentPos.south().west();
            BlockPos bpNorthWest = pCurrentPos.north().west();

            BlockState bsThis = pLevel.getBlockState(pCurrentPos);

            BlockState bsNorthEast = pLevel.getBlockState(bpNorthEast);
            BlockState bsSouthEast = pLevel.getBlockState(bpSouthEast);
            BlockState bsSouthWest = pLevel.getBlockState(bpSouthWest);
            BlockState bsNorthWest = pLevel.getBlockState(bpNorthWest);

            return pState.setValue(PROPERTY_BY_DIRECTION.get(pFacing),
                    Boolean.valueOf(this.connectsTo(pFacingState, pFacingPos, pState)))
                    .setValue(NORTHEAST,  Boolean.valueOf(this.connectsTo(bsNorthEast, bpNorthEast, bsThis)))
                    .setValue(SOUTHEAST, Boolean.valueOf(this.connectsTo(bsSouthEast, bpSouthEast, bsThis)))
                    .setValue(SOUTHWEST,  Boolean.valueOf(this.connectsTo(bsSouthWest, bpSouthWest, bsThis)))
                    .setValue(NORTHWEST, Boolean.valueOf(this.connectsTo(bsNorthWest, bpNorthWest, bsThis)));
        }
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, NORTH, EAST, WEST, SOUTH, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, WATERLOGGED);
    }
}
