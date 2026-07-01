package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anurognathus;
import org.jetbrains.annotations.Nullable;

public abstract class FlyingAttachableAnimal extends AbstractFlyingAnimal{

    private float attachProgress;
    private float prevAttachProgress;

    private static final EntityDataAccessor<Boolean> WANTS_TO_ATTACH = SynchedEntityData.defineId(FlyingAttachableAnimal.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Direction> ATTACHED_DIRECTION = SynchedEntityData.defineId(FlyingAttachableAnimal.class, EntityDataSerializers.DIRECTION);
    int prevTicksAttached;
    @Nullable
    BlockPos attachmentPos;

    protected FlyingAttachableAnimal(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WANTS_TO_ATTACH, false);
        this.entityData.define(ATTACHED_DIRECTION, Direction.DOWN);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("WantsToAttach", this.wantsToAttach());
        compound.putInt("AttachedDirection", this.getAttachedDirection().get3DDataValue());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setWantsToAttach(compound.getBoolean("WantsToAttach"));
        this.setAttachedDirection(Direction.from3DDataValue(compound.getInt("AttachedDirection")));
    }

    public float getAttachedProgress(float partialTick) {
        return (prevAttachProgress + (attachProgress - prevAttachProgress) * partialTick) * 0.2F;
    }

    public boolean wantsToAttach() {
        return this.entityData.get(WANTS_TO_ATTACH);
    }

    public void setWantsToAttach(boolean wantsToAttach) {
        this.entityData.set(WANTS_TO_ATTACH, wantsToAttach);
    }

    public Direction getAttachedDirection() {
        return this.entityData.get(ATTACHED_DIRECTION);
    }

    public void setAttachedDirection(Direction direction) {
        this.entityData.set(ATTACHED_DIRECTION, direction);
    }

    public boolean isAttached(){
        if (this.isFlying())
            return false;
        return this.getAttachedDirection()!=Direction.DOWN;
    }

    @Override
    public void tick() {
        if (this.isAttached() && this.isFlying()){
            this.setFlying(false);
        }

        super.tick();

        prevAttachProgress = attachProgress;

        if (isFlying() && attachProgress < 5F) {
            attachProgress++;
            this.refreshDimensions();
        }
        if (!isFlying() && attachProgress > 0F) {
            attachProgress--;
            this.refreshDimensions();
        }

        if (this.isFlying() && this.getRandom().nextInt(this.attachChance())==0 && !this.wantsToAttach()){
            this.setWantsToAttach(true);
        }

        if (!this.level().isClientSide()){
            if (this.isAttached() && this.wantsToAttach() && this.isInWater()){
                this.detach();
            }

            if (this.isAttached() && this.getRandom().nextInt(this.detachChance())==0){
                this.detach();
            }
        }
    }

    public void detach(){
        if (this.isAttached())
            this.setDeltaMovement(new Vec3(this.getAttachedDirection().getOpposite().getStepX()/4D, 0, -this.getAttachedDirection().getOpposite().getStepZ()/4D));

        this.setWantsToAttach(false);
        this.setFlying(true);
        this.setAttachedDirection(Direction.DOWN);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if ((this.isFlying() || (!this.onGround())) && this.horizontalCollision){
            this.addDeltaMovement(new Vec3(0, -0.01, 0));
            if (this.horizontalCollision && this.tickCount % 20 == 0){
                BlockPos pos = this.blockPosition().offset(this.getDirection().getNormal());
                if (this.canAttachTo(pos, this.level().getBlockState(pos), this.getDirection().getOpposite())){
                    this.setAttachedDirection(this.getDirection());
                    this.setFlying(false);
                }
            }
        }

        if (this.isAttached() && !this.level().isClientSide){
            BlockPos blockStuckTo = this.blockPosition().relative(this.getAttachedDirection());
            BlockState stateStuckTo = this.level().getBlockState(blockStuckTo);
            if (!(stateStuckTo.isFaceSturdy(this.level(), blockStuckTo, this.getAttachedDirection()) || stateStuckTo.is(Blocks.GLASS))){
                Direction newDirection = this.getAttachedDirection();
                int counter = 0;
                for (int x = 0; x < 4; x++){
                    BlockPos newPos = this.blockPosition().relative(newDirection.getOpposite());

                    BlockState blockstate = this.level().getBlockState(newPos);
                    if (blockstate.isFaceSturdy(this.level(), newPos, newDirection) || blockstate.is(Blocks.GLASS)){

                        this.setAttachedDirection(newDirection.getOpposite());
                        break;
                    }else{
                        counter++;
                    }
                    newDirection = newDirection.getClockWise();
                }

                if (counter >= 4){
                    this.detach();
                }
            }
            this.yBodyRot = this.getAttachedDirection().toYRot();
            this.yHeadRot = this.getAttachedDirection().toYRot();

            this.setDeltaMovement(new Vec3(this.getAttachedDirection().getStepX()/16D, 0, this.getAttachedDirection().getStepZ()/16D));
        }


        if ((!this.wantsToAttach() || this.isInWater()) && this.isAttached() && !this.level().isClientSide){
            this.detach();
        }
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }


    @Override
    public boolean isImmobile() {
        return this.isAttached() || super.isImmobile();
    }

    @Override
    public boolean isPushable() {
        return super.isPushable() && !this.isAttached();
    }

    public boolean canAttachTo(BlockPos pos, BlockState state, Direction direction) {
        return state.isFaceSturdy(level(), pos, direction) && level().isEmptyBlock(pos.offset(direction.getNormal()));
    }

    public int attachChance(){
        return 250;
    }

    public int detachChance(){
        return 500;
    }
}
