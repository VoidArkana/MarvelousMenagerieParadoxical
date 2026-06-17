package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;

public class OtozamitesBush extends Block implements SimpleWaterloggedBlock, BonemealableBlock {

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    VoxelShape TRUNK = Block.box(6, 0, 6, 10, 16, 10);
    VoxelShape BUSH = Block.box(0, 4, 0, 16, 16, 16);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public OtozamitesBush(Properties pProperties, ResourceKey<ConfiguredFeature<?, ?>> pFeature) {
        super(pProperties);
        this.feature = pFeature;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos1 = pPos.below();
        switch (pState.getValue(AGE)){
            case 0:
                return this.mayPlaceOn(pLevel.getBlockState(blockpos1));
            case 1:
                return true;
            case 2:
                return this.isBase(pLevel.getBlockState(blockpos1));
            default:
                Direction direction = pState.getValue(FACING);
                BlockPos blockpos = pPos.relative(direction.getOpposite());
                BlockState blockstate = pLevel.getBlockState(blockpos);
                return this.connectsTo(pState, blockstate);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return super.getStateForPlacement(pContext)
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(AGE)>2)
            return pFacing.getOpposite() == pState.getValue(FACING) && !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : pState;

        return !pState.canSurvive(pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected boolean isBase(BlockState pState) {
        return pState.is(this);
    }

    protected boolean mayPlaceOn(BlockState pState) {
        return pState.is(Blocks.CLAY) || pState.is(BlockTags.DIRT) || pState.is(Blocks.FARMLAND);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(AGE)) {
            case 1 -> TRUNK;
            case 0, 2 -> Shapes.or(TRUNK, BUSH);
            default -> BUSH;
        };
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    private boolean connectsTo(BlockState thisState, BlockState otherState) {
        if (otherState.is(this))
            return otherState.getValue(AGE)<thisState.getValue(AGE);
        return false;
    }

    private boolean canConnectTo(BlockState otherState) {
        if (otherState.is(this))
            return otherState.getValue(AGE)>1 && otherState.getValue(AGE)<4;
        return false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE).add(WATERLOGGED).add(FACING);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE)==0 && pLevel.getFluidState(pPos.above()).isEmpty();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return (double)pLevel.random.nextFloat() < 0.25D;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        this.growBush(pLevel, pPos, pState, pRandom);
    }

    public boolean growBush(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {
        Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(this.feature);
        if (optional.isEmpty()) {
            return false;
        } else {
            var event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, optional.get());
            if (event.getResult().equals(net.minecraftforge.eventbus.api.Event.Result.DENY)) return false;
            pLevel.removeBlock(pPos, false);
            if (event.getFeature().value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos)) {
                return true;
            } else {
                pLevel.setBlock(pPos, pState, 3);
                return false;
            }
        }
    }
}
