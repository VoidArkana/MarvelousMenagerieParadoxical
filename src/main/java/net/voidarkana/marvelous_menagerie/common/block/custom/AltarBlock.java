package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.AltarBlockEntity;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.BlockEntityBase;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BaseEntityBlock implements SimpleWaterloggedBlock{
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public AltarBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 9, 16);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AltarBlockEntity(pPos, pState);
    }

    @javax.annotation.Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, MMBlockEntities.ALTAR_ENTITY.get(), AltarBlockEntity::altarTick) : null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (this instanceof EntityBlock) {
            BlockEntity te = pLevel.getBlockEntity(pPos);
            if (te instanceof BlockEntityBase tile) {
                return tile.onActivated(pState, pPos, pPlayer, pHand);
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void playerWillDestroy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        breakBlock(state, world, pos);
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void onBlockExploded(BlockState state, Level world, BlockPos pos, Explosion explosion) {
        breakBlock(state, world, pos);
        super.onBlockExploded(state, world, pos, explosion);
    }

    public void breakBlock(BlockState state, Level world, BlockPos pos) {
        BlockEntity te = world.getBlockEntity(pos);
        if (te instanceof AltarBlockEntity tile) {
            tile.onDestroyed(state, pos);
        }
    }

    @javax.annotation.Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        LevelAccessor levelaccessor = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        boolean flag = levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(flag));
    }
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(WATERLOGGED);
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pState.getValue(BlockStateProperties.WATERLOGGED) && pFluidState.getType() == Fluids.WATER) {
            pLevel.setBlock(pPos, pState.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        super.animateTick(pState, pLevel, pPos, pRandom);

//        if (pLevel.getBlockEntity(pPos) instanceof AltarBlockEntity altar){
//
//            int formationSize = altar.formationSize(pLevel, pPos);
//            int height = formationSize > 3 ? 1 : 0;
//            int radius = formationSize > 3 ? formationSize-3 : formationSize;
//
//            Fracture fracture = altar.getFracture(pPos);
//
//            if (fracture != null){
//                if (fracture.getSummoningTime() > 50 && fracture.getSummoningTime() <= 80){
//
//                    pLevel.addParticle(ParticleTypes.ENCHANT,
//                                (double)pPos.getX() + 0.5D,
//                                (double)pPos.getY() + 2.0D,
//                                (double)pPos.getZ() + 0.5D,
//                                (float) radius,
//                                ((float)height - 1.0F),
//                                (float)radius);
//
//                    pLevel.addParticle(ParticleTypes.ENCHANT,
//                            (double)pPos.getX() + 0.5D,
//                            (double)pPos.getY() + 2.0D,
//                            (double)pPos.getZ() + 0.5D,
//                            (float) -radius,
//                            ((float)height - 1.0F),
//                            (float)radius);
//
//                    pLevel.addParticle(ParticleTypes.ENCHANT,
//                            (double)pPos.getX() + 0.5D,
//                            (double)pPos.getY() + 2.0D,
//                            (double)pPos.getZ() + 0.5D,
//                            (float) radius,
//                            ((float)height - 1.0F),
//                            (float)-radius);
//
//                    pLevel.addParticle(ParticleTypes.ENCHANT,
//                            (double)pPos.getX() + 0.5D,
//                            (double)pPos.getY() + 2.0D,
//                            (double)pPos.getZ() + 0.5D,
//                            (float) -radius,
//                            ((float)height - 1.0F),
//                            (float)-radius);
//                }
//            }
//        }
    }
}
