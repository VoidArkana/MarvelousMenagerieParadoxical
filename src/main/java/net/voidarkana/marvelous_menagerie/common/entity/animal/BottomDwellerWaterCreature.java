package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;


public abstract class BottomDwellerWaterCreature extends BreedableWaterAnimal {

    public boolean isLandNavigator;

    protected BottomDwellerWaterCreature(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MoveControl(this);
            PathNavigation prevNav = this.navigation;
            this.navigation = new GroundPathNavigation(this, level());
            this.lookControl = new LookControl(this);
            this.isLandNavigator = true;
        } else {
            this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
            this.jumpControl = new FishJumpControl(this);
            this.moveControl = new SmoothSwimmingMoveControl(this, 1, 1, 0.02F, 0.1F, true);
            this.setMaxUpStep(1.0F);
            this.navigation = new AmphibiousPathNavigation(this, level());
            this.isLandNavigator = false;
        }
    }

    @Override
    public boolean canFlop() {
        return false;
    }

    @Override
    public boolean hasNormalControls() {
        return false;
    }

    static class FishJumpControl extends JumpControl {

        BottomDwellerWaterCreature mob;
        public FishJumpControl(BottomDwellerWaterCreature fish) {
            super(fish);
            mob = fish;
        }

        @Override
        public void jump() {
            if (!mob.isInWater()){
                super.jump();
            }
        }
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    @Override
    public boolean canSwim() {
        return false;
    }

    @Override
    public void aiStep() {
        if (this.isInWater()) {
            if(!this.onGround()){
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.08, 0));
            }

            BlockPos pos = this.blockPosition();
            BlockState block = this.level().getBlockState(pos.above());
            if (this.getStepHeight() >= 1 && block.getFluidState().is(Fluids.EMPTY)){
                this.setMaxUpStep(0);
            }else if (this.isInWater() && block.getFluidState().is(Fluids.WATER)){
                this.setMaxUpStep(1);
            }
        }
        super.aiStep();
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getFluidState(pPos.above()).is(FluidTags.WATER) ? 0F : super.getWalkTargetValue(pPos, pLevel);
    }
}
