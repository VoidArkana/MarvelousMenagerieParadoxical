package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.BlockEntityBase;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PaleontologyTableBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaleontologyTableBlock extends BaseEntityBlock {

    private final SoundEvent brushSound;
    private final SoundEvent brushCompletedSound;

    protected static final VoxelShape LEG_1 = Block.box(0, 0, 0, 2, 2, 2);
    protected static final VoxelShape LEG_2 = Block.box(14, 0, 0, 16, 2, 2);
    protected static final VoxelShape LEG_3 = Block.box(14, 0, 14, 16, 2, 16);
    protected static final VoxelShape LEG_4 = Block.box(0, 0, 14, 2, 2, 16);
    protected static final VoxelShape TABLE = Block.box(0, 2, 0, 16, 14, 16);

    protected static final VoxelShape SHAPE = Shapes.or(LEG_1, LEG_2, LEG_3, LEG_4, TABLE);

    public PaleontologyTableBlock(Properties pProperties, SoundEvent pBrushSound, SoundEvent pBrushCompletedSound) {
        super(pProperties);
        this.brushSound = pBrushSound;
        this.brushCompletedSound = pBrushCompletedSound;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        pLevel.scheduleTick(pPos, this, 2);
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        pLevel.scheduleTick(pPos, this, 2);
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof PaleontologyTableBlockEntity brushableblockentity) {
            brushableblockentity.checkReset();
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PaleontologyTableBlockEntity(pPos, pState);
    }

    public SoundEvent getBrushSound() {
        return this.brushSound;
    }

    public SoundEvent getBrushCompletedSound() {
        return this.brushCompletedSound;
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
        if (te instanceof PaleontologyTableBlockEntity tile) {
            tile.onDestroyed(state, pos);
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult ray) {
        if (this instanceof EntityBlock) {
            BlockEntity te = world.getBlockEntity(pos);
            if (te instanceof BlockEntityBase tile) {
                return tile.onActivated(state, pos, player, hand);
            }
        }
        return super.use(state, world, pos, player, hand, ray);
    }

}
