package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SegmentedAnglePrecision;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class CalamitesBranchBlock extends Block implements SimpleWaterloggedBlock{

    private static final SegmentedAnglePrecision SEGMENTED_ANGLE8 = new SegmentedAnglePrecision(3);
    public static final int MAX = SEGMENTED_ANGLE8.getMask();
    private static final int ROTATIONS = MAX;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    VoxelShape NW_SE_AABB = Shapes.or(
            Block.box(-1, 7, -1, 3, 9, 3),
            Block.box(1, 7, 1, 5, 9, 5),
            Block.box(3, 7, 3, 7, 9, 7),
            Block.box(5, 7, 5, 9, 9, 9),
            Block.box(7, 7, 7, 11, 9, 11),
            Block.box(9, 7, 9, 13, 9, 13),
            Block.box(11, 7, 11, 15, 9, 15),
            Block.box(13, 7, 13, 17, 9, 17));

    VoxelShape NE_SW_AABB = Shapes.or(
            Block.box(13, 7, -1, 17, 9, 3),
            Block.box(11, 7, 1, 15, 9, 5),
            Block.box(9, 7, 3, 13, 9, 7),
            Block.box(7, 7, 5, 11, 9, 9),
            Block.box(5, 7, 7, 9, 9, 11),
            Block.box(3, 7, 9, 7, 9, 13),
            Block.box(1, 7, 11, 5, 9, 15),
            Block.box(-1, 7, 13, 3, 9, 17));

    VoxelShape NORTHSOUTH_AABB = Block.box(7, 7, 0, 9, 9, 16);

    VoxelShape EASTWEST_AABB = Block.box(0, 7, 7, 16, 9, 9);

    public static final IntegerProperty ROTATION_8 = IntegerProperty.create("rotation", 0, MAX);

    public CalamitesBranchBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(ROTATION_8, Integer.valueOf(0))
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(ROTATION_8)){
            case 1, 5->NE_SW_AABB;
            case 2, 6 -> EASTWEST_AABB;
            case 3, 7 -> NW_SE_AABB;
            default -> NORTHSOUTH_AABB;
        };
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(ROTATION_8, Integer.valueOf(pRotation.rotate(pState.getValue(ROTATION_8), ROTATIONS)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.setValue(ROTATION_8, Integer.valueOf(pMirror.mirror(pState.getValue(ROTATION_8), ROTATIONS)));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());

        return this.defaultBlockState().setValue(ROTATION_8, convertToSegment(pContext.getRotation()))
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    public static int convertToSegment(float pAngle) {
        return SEGMENTED_ANGLE8.fromDegrees(pAngle);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ROTATION_8).add(WATERLOGGED);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        for (int x = -1; x<2 ; x++){
            for (int z = -1; z<2 ; z++){
                pLevel.updateNeighborsAt(pPos.offset(x, 0, z), this);
            }
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pMovedByPiston){
            for (int x = -1; x<2 ; x++){
                for (int z = -1; z<2 ; z++){
                    pLevel.updateNeighborsAt(pPos.offset(x, 0, z), this);
                }
            }
        }
        super.onRemove(pState,pLevel,pPos,pNewState,pMovedByPiston);
    }
}
