package net.voidarkana.marvelous_menagerie.common.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.block.custom.FourEggsBlock;
import net.voidarkana.marvelous_menagerie.common.entity.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;

import java.util.function.Supplier;

public class LayEggGoal extends MoveToBlockGoal {
    private final MarvelousAnimal animal;
    private final TagKey<Block> nestBlock;
    private final Supplier<Block> eggBlock;
    private final double acceptedDistance;
    boolean hasReachedGoal;

    public LayEggGoal(MarvelousAnimal pEggLayer, double pSpeedModifier, TagKey<Block> pNestBlock, Supplier<Block> pEgg, double acceptedDistance) {
        super(pEggLayer, pSpeedModifier, 16);
        this.animal = pEggLayer;
        this.nestBlock = pNestBlock;
        this.eggBlock = pEgg;
        this.acceptedDistance = acceptedDistance;
    }

    @Override
    public void start() {
        super.start();
        this.animal.level().broadcastEntityEvent(this.animal, (byte)10);
        this.hasReachedGoal = false;
    }

    public boolean canUse() {
        if (animal instanceof IEggLayer eggLayer)
            return eggLayer.isPregnant() && super.canUse();

        return false;
    }

    public boolean canContinueToUse() {
        if (animal instanceof IEggLayer eggLayer)
            return eggLayer.isPregnant() && super.canContinueToUse();

        return false;
    }

    public void tick() {
        if (this.animal.isSitting())
            this.animal.standUp();
        BlockPos position = this.animal.blockPosition();
        Level level = this.animal.level();
        BlockPos blockpos = this.getMoveToTarget();
        if ((blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance()) || this.isValidTarget(level, position.below()))
        && level.getBlockState(position).isAir()) {
            if (!this.animal.isInWater() && this.animal instanceof IEggLayer eggLayer) {
                this.hasReachedGoal = true;
                if (this.animal.getNavigation().getPath() != null) {
                    this.animal.getNavigation().stop();
                }

                if (eggLayer.getLayEggCounter() < 1) {
                    eggLayer.setLayingEgg(true);
                } else if (eggLayer.getLayEggCounter() > 50) {
                    level.playSound(null, position, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    BlockState blockstate = eggBlock.get().defaultBlockState();
                    if (level.getBlockState(position.below()).isAir()){
                        position = position.below();
                    }
                    if (blockstate.is(MMBlocks.LYSTRO_EGG.get())){
                        int eggAmount = this.mob.getRandom().nextInt(1, 5);
                        level.setBlock(position, blockstate.setValue(FourEggsBlock.EGGS, eggAmount), 3);
                    }else {
                        level.setBlock(position, blockstate, 3);
                    }
                    level.gameEvent(GameEvent.BLOCK_PLACE, position, GameEvent.Context.of(this.animal, blockstate));
                    eggLayer.setPregnant(false);
                    eggLayer.setLayingEgg(false);
                    eggLayer.onEggLaid();
                    this.animal.setInLoveTime(600);
                }

                if (eggLayer.isLayingEgg()) {
                    int prevLayEggCounter = eggLayer.getLayEggCounter();
                    eggLayer.setLayEggCounter(++prevLayEggCounter);
                }
            }

            --this.tryTicks;
        } else {
            if (this.hasReachedGoal)
                this.hasReachedGoal = false;
            ++this.tryTicks;
        }
        if (!this.hasReachedGoal) {
            this.mob.getNavigation().moveTo((double)((float)blockpos.getX()) + 0.5D, (double)blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5D, this.speedModifier);
        }
    }

    public boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return pLevel.isEmptyBlock(pPos.above()) && pLevel.getBlockState(pPos).is(nestBlock);
    }

    public double acceptedDistance() {
        return this.mob.getBbWidth() + 0.5D;
    }
}
