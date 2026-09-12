package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.Optional;

public class WilliamsoniaLeaves extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, BonemealableBlock {

    public static final EnumProperty<WilliamsoniaAttachFace> ATTACHMENT_STYLE = EnumProperty.create("face", WilliamsoniaAttachFace.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape FLOOR_AABB = Block.box(4, 0, 4, 12, 5, 12);
    private static final VoxelShape EAST_AABB = Block.box(0, 0, 4, 5, 8, 12);
    private static final VoxelShape WEST_AABB = Block.box(11, 0, 4, 16, 8, 12);
    private static final VoxelShape NORTH_AABB = Block.box(4, 0, 11, 12, 8, 16);
    private static final VoxelShape SOUTH_AABB = Block.box(4, 0, 0, 12, 8, 5);

    @Nullable
    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public WilliamsoniaLeaves(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> pFeature) {
        super(properties);
        this.feature = pFeature;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(ATTACHMENT_STYLE, WilliamsoniaAttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH));
    }

    public WilliamsoniaLeaves(Properties properties) {
        this(properties, null);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return !pState.getValue(WATERLOGGED);
    }

    public boolean canSurvive(BlockState p_53186_, LevelReader p_53187_, BlockPos p_53188_) {
        return canAttach(p_53187_, p_53188_, getConnectedDirection(p_53186_).getOpposite());
    }

    public static boolean canAttach(LevelReader pLevel, BlockPos pPos, Direction pDirection) {
        BlockPos blockpos = pPos.relative(pDirection);
        BlockState state = pLevel.getBlockState(blockpos);
        if (state.is(MMTags.Blocks.THIN_LOGS) || state.getBlock() instanceof ThinLogBlock){
            return state.getValue(ThinLogBlock.AXIS) == pDirection.getAxis();
        }
        return state.isFaceSturdy(pLevel, blockpos, pDirection.getOpposite()) || state.is(MMBlocks.WILLIAMSONIA_PLANT.get()) || state.is(MMBlocks.STRIPPED_WILLIAMSONIA_PLANT.get());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);

        for(Direction direction : pContext.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = blockstate
                        .setValue(ATTACHMENT_STYLE, WilliamsoniaAttachFace.FLOOR)
                        .setValue(FACING, pContext.getHorizontalDirection());
            } else {
                blockstate = blockstate
                        .setValue(ATTACHMENT_STYLE, WilliamsoniaAttachFace.WALL)
                        .setValue(FACING, direction.getOpposite());
            }

            if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate;
            }
        }

        return null;
    }

    public VoxelShape getShape(BlockState p_51104_, BlockGetter p_51105_, BlockPos p_51106_, CollisionContext p_51107_) {
        Direction direction = p_51104_.getValue(FACING);

        if (p_51104_.getValue(ATTACHMENT_STYLE) == WilliamsoniaAttachFace.FLOOR) {
            return FLOOR_AABB;
        } else {
            VoxelShape voxelshape = switch (direction) {
                case EAST -> EAST_AABB;
                case WEST -> WEST_AABB;
                case SOUTH -> SOUTH_AABB;
                case NORTH -> NORTH_AABB;
                default -> throw new IncompatibleClassChangeError();
            };

            return voxelshape;
        }
    }

    public BlockState updateShape(BlockState p_53190_, Direction p_53191_, BlockState p_53192_, LevelAccessor p_53193_, BlockPos p_53194_, BlockPos p_53195_) {
        return getConnectedDirection(p_53190_).getOpposite() == p_53191_ && !p_53190_.canSurvive(p_53193_, p_53194_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_53190_, p_53191_, p_53192_, p_53193_, p_53194_, p_53195_);
    }

    protected static Direction getConnectedDirection(BlockState p_53201_) {
        if (p_53201_.getValue(ATTACHMENT_STYLE) == WilliamsoniaAttachFace.FLOOR) {
            return Direction.UP;
        }
        return p_53201_.getValue(FACING);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, ATTACHMENT_STYLE, WATERLOGGED);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256559_, BlockPos p_50898_, BlockState pState, boolean p_50900_) {
        return pState.is(MMBlocks.WILLIAMSONIA_LEAVES.get()) && !pState.getValue(WATERLOGGED) && (p_256559_.getBlockState(p_50898_.below()).is(BlockTags.DIRT) || p_256559_.getBlockState(p_50898_.below()).is(BlockTags.SAND));
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource pRandom, BlockPos p_220880_, BlockState p_220881_) {
        return pRandom.nextInt(3)==0;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        if (this.feature != null)
            this.grow(pLevel, pPos, pState, pRandom);
    }

    public void grow(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {
        Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(this.feature);
        if (!optional.isEmpty()) {
            var event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, optional.get());
            if (!event.getResult().equals(net.minecraftforge.eventbus.api.Event.Result.DENY)) {
                pLevel.removeBlock(pPos, false);
                if (!event.getFeature().value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos)) {
                    pLevel.setBlock(pPos, pState, 3);
                }
            }
        }
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    public enum WilliamsoniaAttachFace implements StringRepresentable {
        FLOOR("floor"),
        WALL("wall");

        private final String name;

        private WilliamsoniaAttachFace(String p_61311_) {
            this.name = p_61311_;
        }

        public String getSerializedName() {
            return this.name;
        }
    }
}
