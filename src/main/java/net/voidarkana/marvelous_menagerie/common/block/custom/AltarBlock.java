package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.AltarBlockEntity;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.BlockEntityBase;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PedestalBlockEntity;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BaseEntityBlock {


    public AltarBlock(Properties pProperties) {
        super(pProperties);
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
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, MMBlockEntities.ALTAR_ENTITY.get(), AltarBlockEntity::skullAnimationTick) : null;
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

}
