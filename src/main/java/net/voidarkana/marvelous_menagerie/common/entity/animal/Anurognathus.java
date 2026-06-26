package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.FlyAndAttachGoal;
import net.voidarkana.marvelous_menagerie.common.entity.ai.goals.RandomFlyGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.AbstractFlyingAnimal;
import org.jetbrains.annotations.Nullable;

public class Anurognathus extends AbstractFlyingAnimal {

    public final AnimationState idleLookState1 = new AnimationState();
    public final AnimationState idleLookState2 = new AnimationState();
    public final AnimationState idleAttachedState = new AnimationState();
    private int idleStateTimeout = this.random.nextInt(180) + 60;

    private float attachProgress;
    private float prevAttachProgress;

    private static final EntityDataAccessor<Boolean> WANTS_TO_ATTACH = SynchedEntityData.defineId(Anurognathus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Direction> ATTACHED_DIRECTION = SynchedEntityData.defineId(Anurognathus.class, EntityDataSerializers.DIRECTION);
    int prevTicksAttached;
    @Nullable
    BlockPos attachmentPos;

    public Anurognathus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.MAX_HEALTH, 3)
                .add(Attributes.FOLLOW_RANGE, 52);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return Ingredient.of(Items.SPIDER_EYE, Items.HONEY_BOTTLE).test(pStack);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.25f));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25f, Ingredient.of(Items.SPIDER_EYE, Items.HONEY_BOTTLE), false));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D, 15, false) {

            @Override
            public boolean canUse() {
                return !Anurognathus.this.isFlying() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !Anurognathus.this.isFlying() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(3, new FlyAndAttachGoal(this, 1.5F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

//        this.goalSelector.addGoal(5, new AttachToWallGoal(this));
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
//        System.out.println(direction);

        this.entityData.set(ATTACHED_DIRECTION, direction);
    }

    public boolean isAttached(){
        if (this.isFlying())
            return false;
        return this.getAttachedDirection()!=Direction.DOWN;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        float width = this.isAttached() ? 0.6F : 1F;
        float height = this.isAttached() ? 2.1F : 1F;
        return super.getDimensions(pPose).scale(width, height);
    }

    @Override
    public void tick() {
        if (this.isInWaterOrBubble() && !this.isFlying()) {
            this.setFlying(true);
        }

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

        if (this.isFlying() && this.getRandom().nextInt(250)==0 && !this.wantsToAttach()){
            this.setWantsToAttach(true);
        }

        if (!this.level().isClientSide()){
            if (this.isAttached() && this.wantsToAttach() && this.isInWater()){
                this.detach();
            }

            if (this.isAttached() && this.getRandom().nextInt(500)==0){
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
    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.idleStateTimeout <= 0 && !this.isFlying()) {
            this.idleStateTimeout = this.random.nextInt(180) + 60;
            if (this.onGround()){
                if (this.getRandom().nextBoolean())
                    this.idleLookState1.start(this.tickCount);
                else
                    this.idleLookState2.start(this.tickCount);
            }else if (this.isAttached()){
                this.idleAttachedState.start(this.tickCount);
            }
        } else {
            --this.idleStateTimeout;
        }
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
    public SoundEvent getFlapSound() {
        return SoundEvents.PARROT_FLY;
    }

    @Override
    public int getFlapRate() {
        return 10;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return MMEntities.ANUROGNATHUS.get().create(pLevel);
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (super.hurt(pSource, pAmount)){
            this.addDeltaMovement(new Vec3(0, 0.15, 0));
            this.setFlying(true);
            this.switchNavigator(false);
            return true;
        }
        return false;
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

    class AttachToWallGoal extends MoveToBlockGoal{

        static Anurognathus anurognathus;
        Direction direction;

        public AttachToWallGoal(Anurognathus pMob) {
            super(pMob, 1, 16, 8);
            anurognathus = pMob;
        }

        @Override
        public boolean canUse() {
            return anurognathus.wantsToAttach() && this.findNearestBlock();
        }

        @Override
        public void start() {
            anurognathus.setFlying(true);
            anurognathus.addDeltaMovement(new Vec3(0, 0.1, 0));
            anurognathus.switchNavigator(false);

            super.start();
        }

        @Override
        public boolean canContinueToUse() {
            return !anurognathus.isAttached() && super.canContinueToUse() && anurognathus.wantsToAttach();
        }

        protected int nextStartTick(PathfinderMob creature) {
            return 60;
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            Direction pDirection;
            BlockPos otherPos;
            for (int x = -1; x < 1; x++){
                for (int z = -1; z < 1; z++){
                    if (x == 0 || z == 0){
                        pDirection = Direction.fromDelta(x, 0, z);
                        if (pDirection != null){
                            otherPos = pPos.relative(pDirection);

                            BlockState blockstate = pLevel.getBlockState(otherPos);
                            if ((blockstate.isFaceSturdy(pLevel, otherPos, pDirection) || blockstate.is(Blocks.GLASS))
                                    && pLevel.getBlockState(pPos).getFluidState().isEmpty()){
                                this.direction = pDirection;
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        protected BlockPos getMoveToTarget() {
            return this.blockPos;
        }

        @Override
        public void tick() {

            if (this.shouldRecalculatePath()){
                this.mob.getNavigation().moveTo((double)((float)this.blockPos.getX()),
                        (double)this.blockPos.getY(),
                        (double)((float)this.blockPos.getZ()) + 0.5D, 1.25);

            }

            if (this.isReachedTarget() || anurognathus.horizontalCollision){
                anurognathus.setAttachedDirection(Direction.fromYRot(Math.round(anurognathus.yHeadRot / 90.0) * 90));
                anurognathus.setXRot(0);
                anurognathus.setDeltaMovement(Vec3.ZERO);
                this.stop();
            }else{
                this.mob.getLookControl().setLookAt((double)((float)this.blockPos.getX()) + 0.5D, (double)this.blockPos.getY(), (double)((float)this.blockPos.getZ()) + 0.5D);
            }

            super.tick();
        }

        @Override
        protected boolean findNearestBlock() {
            int i = 16;
            int j = 8;
            BlockPos blockpos = this.mob.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int k = j; k >= -j; k--) {
                for(int l = 0; l < i; ++l) {
                    for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                        for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                            blockpos$mutableblockpos.setWithOffset(blockpos, i1, k, j1);
                            if (this.mob.isWithinRestriction(blockpos$mutableblockpos) && this.isValidTarget(this.mob.level(), blockpos$mutableblockpos)) {
                                this.blockPos = blockpos$mutableblockpos;
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
