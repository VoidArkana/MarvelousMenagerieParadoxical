package net.voidarkana.marvelous_menagerie.common.entity.animal.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.IEggLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.MarvelousAnimal;

import java.util.function.Supplier;

public class LayEggGoal extends MoveToBlockGoal {
    private final MarvelousAnimal animal;
    private final TagKey<Block> nestBlock;
    private final Supplier<Block> eggBlock;
    private final double acceptedDistance;

    public LayEggGoal(MarvelousAnimal pEggLayer, double pSpeedModifier, TagKey<Block> pNestBlock, Supplier<Block> pEgg, double acceptedDistance) {
        super(pEggLayer, pSpeedModifier, 16);
        this.animal = pEggLayer;
        this.nestBlock = pNestBlock;
        this.eggBlock = pEgg;
        this.acceptedDistance = acceptedDistance;
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
        super.tick();
        BlockPos blockpos = this.animal.blockPosition();
        if (!this.animal.isInWater() && this.animal instanceof IEggLayer eggLayer) {

            if (eggLayer.getLayEggCounter() < 1) {
                eggLayer.setLayingEgg(true);
            } else if (eggLayer.getLayEggCounter() > this.adjustedTickDelay(100)) {
                Level level = this.animal.level();
                level.playSound(null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                BlockPos blockpos1 = this.blockPos.above();
                BlockState blockstate = eggBlock.get().defaultBlockState();
                level.setBlock(blockpos1, blockstate, 3);
                level.gameEvent(GameEvent.BLOCK_PLACE, blockpos1, GameEvent.Context.of(this.animal, blockstate));
                eggLayer.setPregnant(false);
                eggLayer.setLayingEgg(false);
                this.animal.setInLoveTime(600);
            }

            if (eggLayer.isLayingEgg()) {
                int prevLayEggCounter = eggLayer.getLayEggCounter();
                eggLayer.setLayEggCounter(++prevLayEggCounter);
            }
        }

    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        return !pLevel.isEmptyBlock(pPos.above()) ? false : pLevel.getBlockState(pPos).is(nestBlock);
    }

    public double acceptedDistance() {
        return acceptedDistance;
    }
}
