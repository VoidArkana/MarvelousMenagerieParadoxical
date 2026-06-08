package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FourEggsBlock  extends Block {

    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;

    VoxelShape SHAPE_1 = Block.box(6, 0, 6, 10, 5, 10);
    VoxelShape SHAPE_2 = Block.box(4, 0, 4, 13, 7, 11);
    VoxelShape SHAPE_3 = Block.box(3, 0, 3, 13, 7, 13);
    VoxelShape SHAPE_4 = Block.box(2, 0, 2, 14, 7, 14);
    private Supplier<? extends EntityType> creature;
    private TagKey<Block> validBlock;

    public FourEggsBlock(Properties pProperties, Supplier<? extends EntityType> entityType, TagKey<Block> nestBlock) {
        super(pProperties);

        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0).setValue(EGGS, 1));

        this.creature = entityType;
        this.validBlock = nestBlock;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH, EGGS);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (this.getEggs(pState)){
            case 2 -> SHAPE_2;
            case 3 -> SHAPE_3;
            case 4 -> SHAPE_4;
            default -> SHAPE_1;
        };
    }

    public int getEggs(BlockState state) {
        return state.getValue(EGGS);
    }

    public int getHatchLevel(BlockState state) {
        return state.getValue(HATCH);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.shouldUpdateHatchLevel(level, pos.below()) && state.is(this)) {
            int i = state.getValue(HATCH);

            if (i < 2) {
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.setBlock(pos, state.setValue(HATCH, this.getHatchLevel(state) + 1), 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
            } else {
                level.levelEvent(2001, pos, Block.getId(state));
                for (int e = this.getEggs(state); e > 0; e--){
                    MarvelousAnimal baby = (MarvelousAnimal) creature.get().create(level);
                    if (baby != null) {
                        baby.setAge(-24000);
                        Vec3 vec3 = pos.getCenter();
                        baby.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.BREEDING, null, null);
                        baby.moveTo(vec3.x()+(level.getRandom().nextFloat()/2), vec3.y(), vec3.z()+(level.getRandom().nextFloat()/2),
                                Mth.wrapDegrees(level.random.nextFloat() * 360.0F), 0.0F);
                        level.addFreshEntity(baby);
                    }
                }

                level.playSound(null, pos, SoundEvents.SNIFFER_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.removeBlock(pos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
                this.onEggBreak(state, level, pos, random);
            }
        }
    }

    void onEggBreak(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){

    }

    private boolean shouldUpdateHatchLevel(Level level, BlockPos ground) {
        if (this.isValidBlock(level, ground)){
            float f = level.getTimeOfDay(1.0F);
            if ((double)f < 0.69D && (double)f > 0.65D) {
                return true;
            } else {
                return level.random.nextInt(250) == 0;
            }
        }else {
            return false;
        }
    }

    public boolean isValidBlock(BlockGetter level, BlockPos pos){
        return level.getBlockState(pos).is(validBlock);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(4, blockstate.getValue(EGGS) + 1)))
                : super.getStateForPlacement(pContext);
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        boolean flag = this.isValidBlock(level, pos);
        if (!level.isClientSide() && flag) {
            level.levelEvent(3009, pos, 0);
        }
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(state));
    }


    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully()) {
            this.destroyEgg(pLevel, pState, pPos, pEntity, 100);
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!(pEntity instanceof Zombie)) {
            this.destroyEgg(pLevel, pState, pPos, pEntity, 3);
        }

        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.gameEvent(GameEvent.BLOCK_DESTROY, pPos, GameEvent.Context.of(pState));
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
        }

    }

    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        this.decreaseEggs(pLevel, pPos, pState);
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().is(this.asItem())
                && pState.getValue(EGGS) < 4 || super.canBeReplaced(pState, pUseContext);
    }

    private void destroyEgg(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int pChance) {
        if (this.canDestroyEgg(pLevel, pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(pChance) == 0 && pState.is(this)) {
                this.decreaseEggs(pLevel, pPos, pState);
            }

        }
    }

    private boolean canDestroyEgg(Level pLevel, Entity pEntity) {
        if ((pEntity.getType().is(MMTags.EntityTypes.CANT_TRAMPLE)) || pEntity.getType() == creature.get().create(pLevel).getType()) {
            return false;
        } else {
            if (!(pEntity instanceof LivingEntity)) {
                return false;
            } else {
                return pEntity instanceof Player || ForgeEventFactory.getMobGriefingEvent(pLevel, pEntity);
            }
        }
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}
