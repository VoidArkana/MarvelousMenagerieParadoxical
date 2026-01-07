package net.voidarkana.marvelous_menagerie.common.entity.animal;

import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.data.codec.PikaiaCoralManager;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Pikaia extends AbstractBasicFish {

    private static final EntityDataAccessor<Boolean> CAN_POLLINATE = SynchedEntityData.defineId(Pikaia.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_POLLINATING = SynchedEntityData.defineId(Pikaia.class, EntityDataSerializers.BOOLEAN);


    public Pikaia(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new Pikaia.ReviveCoralGoal(this));
    }

    @Override
    public Ingredient foodIngredients() {
        return Ingredient.of(Items.BONE_MEAL);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_POLLINATING, false);
        this.entityData.define(CAN_POLLINATE, true);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("CanPollinate", this.canPollinate());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setCanPollinate(pCompound.getBoolean("CanPollinate"));
    }

    @Override
    public @Nullable BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {

        Pikaia baby = MMEntities.PIKAIA.get().create(pLevel);
        baby.setFromBucket(true);
        return baby;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.PIKAIA_BUCKET.get());
    }

    public boolean isPikachu() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("pikachu");
    }

    public boolean canPollinate() {
        return this.entityData.get(CAN_POLLINATE);
    }

    public void setCanPollinate(boolean fromBook) {
        this.entityData.set(CAN_POLLINATE, fromBook);
    }

    public boolean isPollinating() {
        return this.entityData.get(IS_POLLINATING);
    }

    public void setPollinating(boolean fromBook) {
        this.entityData.set(IS_POLLINATING, fromBook);
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (itemStack.is(Items.DRIED_KELP) && this.canPollinate()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanPollinate(false);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        if (itemStack.is(Items.KELP) && !this.canPollinate()){
            this.usePlayerItem(pPlayer, pHand, itemStack);
            this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setCanPollinate(true);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 0.25, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }

        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }


    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        if (!pPlayer.getAbilities().instabuild) {
            pStack.shrink(1);
        }

    }

    class ReviveCoralGoal extends MoveToBlockGoal {
        private final Pikaia pikaiia;
        private int eatAnimationTick;

        public void start() {
            this.eatAnimationTick = 10;
            this.pikaiia.level().broadcastEntityEvent(this.pikaiia, (byte)10);
        }

        public ReviveCoralGoal(Pikaia pikaia) {
            super(pikaia, 1F, 16, 10);
            this.pikaiia = pikaia;
        }

        public boolean canUse() {
            if (!this.pikaiia.canPollinate()){
                return false;
            }
            return super.canUse();
        }

        @Override
        public double acceptedDistance() {
            return 1.5D;
        }

        public void tick() {
            super.tick();
            this.pikaiia.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.pikaiia.getMaxHeadXRot());
            if (this.isReachedTarget()) {

                if (!this.pikaiia.isPollinating()){
                    this.pikaiia.setPollinating(true);
                }

                if (this.pikaiia.getNavigation().getPath() != null) {
                    this.pikaiia.getNavigation().stop();
                }

                Level level = this.pikaiia.level();
                BlockPos blockpos = this.blockPos;
                BlockState blockstate = level.getBlockState(blockpos);

                Map<BlockPos, BlockState> posBlockStateMap = Maps.newHashMap();

                if (blockstate.is(MMTags.Blocks.PIKAIA_REVIVING_TARGET) && !this.pikaiia.level().isClientSide) {
                    for (PikaiaCoralManager.PikaiaCoralData data : PikaiaCoralManager.DATA) {

                        Block input = data.input();
                        Block output = data.output();

                        if (blockstate != null){
                            if (blockstate.is(input)) {
                                if (blockstate.getBlock() instanceof BaseCoralPlantTypeBlock){
                                    posBlockStateMap.put(blockpos, output.defaultBlockState().trySetValue(BaseCoralPlantBlock.WATERLOGGED, true));
                                }else {
                                    posBlockStateMap.put(blockpos, output.defaultBlockState());
                                }
                            }
                        }
                    }
                    for (BlockPos position : posBlockStateMap.keySet()) {
                        this.pikaiia.level().levelEvent(2005, position, 0);
                        BlockState output = posBlockStateMap.get(blockpos);
                        this.pikaiia.level().setBlock(blockpos, output, 2);
                    }
                }

                this.nextStartTick = 1000;
                this.eatAnimationTick--;

            } else if (this.eatAnimationTick == 0) {
                this.pikaiia.setPollinating(false);
            }

        }

        public void stop() {
            this.nextStartTick = 1000;
            this.eatAnimationTick = 0;
            this.pikaiia.setPollinating(false);
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return (blockstate.is(MMTags.Blocks.PIKAIA_REVIVING_TARGET) &&
                    (blockstate.getBlock() instanceof BaseCoralPlantTypeBlock == blockstate.getFluidState().is(FluidTags.WATER)));
        }

    }
}
