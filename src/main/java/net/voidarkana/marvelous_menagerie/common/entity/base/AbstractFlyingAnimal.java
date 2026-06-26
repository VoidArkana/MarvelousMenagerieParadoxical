package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.entity.ai.movement.FlightPathNavigationNoSpin;
import net.voidarkana.marvelous_menagerie.common.entity.ai.movement.MarvelousFlyingMoveController;

public abstract class AbstractFlyingAnimal extends MarvelousAnimal{

    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(AbstractFlyingAnimal.class, EntityDataSerializers.BOOLEAN);
    private float flyProgress;
    private float prevFlyProgress;
    private float groundProgress = 5.0F;
    private float prevGroundProgress = 5.0F;
    public int timeFlying = 0;
    private float flightPitch = 0;
    private float prevFlightPitch = 0;
    private float flightRoll = 0;
    private float prevFlightRoll = 0;
    private int animationTick;
    public int groundedFor = 0;
    private boolean isLandNavigator;

    protected AbstractFlyingAnimal(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        switchNavigator(true);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLYING, false);
    }

    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    public float getFlightPitch(float partialTick) {
        return (prevFlightPitch + (flightPitch - prevFlightPitch) * partialTick);
    }

    public float getFlightRoll(float partialTick) {
        return (prevFlightRoll + (flightRoll - prevFlightRoll) * partialTick);
    }

    public float getFlyProgress(float partialTick) {
        return (prevFlyProgress + (flyProgress - prevFlyProgress) * partialTick) * 0.2F;
    }

    public float getGroundProgress(float partialTick) {
        return (prevGroundProgress + (groundProgress - prevGroundProgress) * partialTick) * 0.2F;
    }

    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    protected void switchNavigator(boolean onLand) {
        if (onLand) {
            this.moveControl = new MoveControl(this);
            this.navigation = new GroundPathNavigation(this, level());
            this.isLandNavigator = true;
        } else {
            this.moveControl = new MarvelousFlyingMoveController(this);
            this.navigation = new FlightPathNavigationNoSpin(this, level(), 1.0F);
            this.isLandNavigator = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        prevFlyProgress = flyProgress;
        prevGroundProgress = groundProgress;
        prevFlightPitch = flightPitch;
        prevFlightRoll = flightRoll;
        if (isFlying() && flyProgress < 5F) {
            flyProgress++;
        }
        if (!isFlying() && flyProgress > 0F) {
            flyProgress--;
        }
        if (onGround() && groundProgress < 5F) {
            groundProgress++;
        }
        if (!onGround() && groundProgress > 0F) {
            groundProgress--;
        }
        if (!level().isClientSide) {
            if (this.isFlying()) {
                if(timeFlying % this.getFlapRate() == 0 && this.getFlapSound() != null){
                    this.playSound(this.getFlapSound(), this.getSoundVolume(), this.getVoicePitch());
                }
                timeFlying++;
                this.setNoGravity(true);
                if (this.isLandNavigator) {
                    switchNavigator(false);
                }
                if (groundedFor > 0) {
                    this.setFlying(false);
                }
            } else {
                timeFlying = 0;
                this.setNoGravity(false);
                if (!this.isLandNavigator) {
                    switchNavigator(true);
                }
            }
        }
        if (groundedFor > 0) {
            groundedFor--;
        }

        tickRotation((float) this.getDeltaMovement().y * 2 * -(float) (180F / (float) Math.PI));
    }


    private void tickRotation(float yMov) {
        flightPitch = yMov;
        float threshold = 1F;
        boolean flag = false;
        if (isFlying() && this.yRotO - this.getYRot() > threshold) {
            flightRoll += 10;
            flag = true;
        }
        if (isFlying() && this.yRotO - this.getYRot() < -threshold) {
            flightRoll -= 10;
            flag = true;
        }
        if (!flag) {
            if (flightRoll > 0) {
                flightRoll = Math.max(flightRoll - 5, 0);
            }
            if (flightRoll < 0) {
                flightRoll = Math.min(flightRoll + 5, 0);
            }
        }
        flightRoll = Mth.clamp(flightRoll, -60, 60);
    }

    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 4.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);
    }

    public abstract SoundEvent getFlapSound();

    public abstract int getFlapRate();
}
